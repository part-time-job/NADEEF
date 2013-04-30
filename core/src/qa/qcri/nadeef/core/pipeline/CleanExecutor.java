/*
 * Copyright (C) Qatar Computing Research Institute, 2013.
 * All rights reserved.
 */

package qa.qcri.nadeef.core.pipeline;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import qa.qcri.nadeef.core.datamodel.*;
import qa.qcri.nadeef.core.operator.*;
import qa.qcri.nadeef.tools.Tracer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * CleanPlan execution logic. It assembles the right pipeline based on the clean plan and
 * start the execution.
 */
public class CleanExecutor {
    private static CleanPlan cleanPlan;
    private static Tracer tracer = Tracer.getTracer(CleanExecutor.class);

    /**
     * Constructor.
     * @param cleanPlan clean plan.
     */
    public CleanExecutor(CleanPlan cleanPlan) {
        Preconditions.checkNotNull(cleanPlan);
        this.cleanPlan = cleanPlan;
    }

    /**
     * Runs the violation repair execution.
     */
    public void repair() {
        List<Rule> rules = cleanPlan.getRules();
        Flow[] flows = new Flow[rules.size()];
        NodeCacheManager cacheManager = NodeCacheManager.getInstance();

        try {
            // assemble the flow.
            for (int i = 0; i < flows.length; i ++)  {
                flows[i] = new Flow();
                Rule rule = rules.get(i);
                String inputKey = cacheManager.put(rule);
                flows[i].setInputKey(inputKey);
                flows[i].addNode(new Node(new ViolationDeserializer(), "deserializer"));
                flows[i].addNode(new Node(new ViolationRepair(rule), "query"));
            }
        } catch (Exception ex) {
            Tracer tracer = Tracer.getTracer(CleanExecutor.class);
            tracer.err("Exception happens during assembling the pipeline " + ex.getMessage());
            if (Tracer.isInfoOn()) {
                ex.printStackTrace();
            }
            return;
        }

        // TODO: run multiple rules in parallel in process / thread.
        Stopwatch stopwatch = new Stopwatch().start();
        for (Flow flow : flows) {
            flow.start();
        }
        tracer.info(
            "Cleaning finished in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms."
        );
    }

    /**
     * Runs the violation detection execution.
     */
    public void detect() {
        List<Rule> rules = cleanPlan.getRules();
        Flow[] flows = new Flow[rules.size()];
        NodeCacheManager cacheManager = NodeCacheManager.getInstance();

        try {
            // assemble the flow.
            for (int i = 0; i < flows.length; i ++)  {
                flows[i] = new Flow();
                Rule rule = rules.get(i);
                String inputKey = cacheManager.put(rule);
                flows[i].setInputKey(inputKey);
                flows[i].addNode(new Node(new SourceDeserializer(cleanPlan), "deserializer"));
                flows[i].addNode(new Node(new QueryEngine(rule), "query"));
                if (rule.supportTwoInputs()) {
                    // the case where the rule is working on multiple tables (2).
                    flows[i].addNode(new Node(new ViolationDetector<TuplePair>(rule), "detector"));
                } else {
                    flows[i].addNode(
                        new Node(new ViolationDetector<TupleCollection>(rule), "detector")
                    );
                }

                flows[i].addNode(new Node(new ViolationExport(cleanPlan), "export"));
            }
        } catch (Exception ex) {
            Tracer tracer = Tracer.getTracer(CleanExecutor.class);
            tracer.err("Exception happens during assembling the pipeline " + ex.getMessage());
            if (Tracer.isInfoOn()) {
                ex.printStackTrace();
            }
            return;
        }

        // TODO: run multiple rules in parallel in process / thread.
        Stopwatch stopwatch = new Stopwatch().start();
        for (Flow flow : flows) {
            flow.start();
        }
        tracer.info(
            "Cleaning finished in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms."
        );
    }

    /**
     * Gets the <code>CleanPlan</code>.
     * @return the <code>CleanPlan</code>.
     */
    public static CleanPlan getCurrentCleanPlan() {
        return cleanPlan;
    }
}
