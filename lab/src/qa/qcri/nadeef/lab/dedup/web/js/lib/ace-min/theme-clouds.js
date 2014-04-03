/*
 * QCRI, NADEEF LICENSE
 * NADEEF is an extensible, generalized and easy-to-deploy data cleaning platform built at QCRI.
 * NADEEF means "Clean" in Arabic
 *
 * Copyright (c) 2011-2013, Qatar Foundation for Education, Science and Community Development (on
 * behalf of Qatar Computing Research Institute) having its principle place of business in Doha,
 * Qatar with the registered address P.O box 5825 Doha, Qatar (hereinafter referred to as "QCRI")
 *
 * NADEEF has patent pending nevertheless the following is granted.
 * NADEEF is released under the terms of the MIT License, (http://opensource.org/licenses/MIT).
 */

ace.define("ace/theme/clouds",["require","exports","module","ace/lib/dom"],function(e,t,n){t.isDark=!1,t.cssClass="ace-clouds",t.cssText='.ace-clouds .ace_gutter {background: #ebebeb;color: #333}.ace-clouds .ace_print-margin {width: 1px;background: #e8e8e8}.ace-clouds {background-color: #FFFFFF;color: #000000}.ace-clouds .ace_cursor {color: #000000}.ace-clouds .ace_marker-layer .ace_selection {background: #BDD5FC}.ace-clouds.ace_multiselect .ace_selection.ace_start {box-shadow: 0 0 3px 0px #FFFFFF;border-radius: 2px}.ace-clouds .ace_marker-layer .ace_step {background: rgb(255, 255, 0)}.ace-clouds .ace_marker-layer .ace_bracket {margin: -1px 0 0 -1px;border: 1px solid #BFBFBF}.ace-clouds .ace_marker-layer .ace_active-line {background: #FFFBD1}.ace-clouds .ace_gutter-active-line {background-color : #dcdcdc}.ace-clouds .ace_marker-layer .ace_selected-word {border: 1px solid #BDD5FC}.ace-clouds .ace_invisible {color: #BFBFBF}.ace-clouds .ace_keyword,.ace-clouds .ace_meta,.ace-clouds .ace_support.ace_constant.ace_property-value {color: #AF956F}.ace-clouds .ace_keyword.ace_operator {color: #484848}.ace-clouds .ace_keyword.ace_other.ace_unit {color: #96DC5F}.ace-clouds .ace_constant.ace_language {color: #39946A}.ace-clouds .ace_constant.ace_numeric {color: #46A609}.ace-clouds .ace_constant.ace_character.ace_entity {color: #BF78CC}.ace-clouds .ace_invalid {background-color: #FF002A}.ace-clouds .ace_fold {background-color: #AF956F;border-color: #000000}.ace-clouds .ace_storage,.ace-clouds .ace_support.ace_class,.ace-clouds .ace_support.ace_function,.ace-clouds .ace_support.ace_other,.ace-clouds .ace_support.ace_type {color: #C52727}.ace-clouds .ace_string {color: #5D90CD}.ace-clouds .ace_comment {color: #BCC8BA}.ace-clouds .ace_entity.ace_name.ace_tag,.ace-clouds .ace_entity.ace_other.ace_attribute-name {color: #606060}.ace-clouds .ace_indent-guide {background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAACCAYAAACZgbYnAAAAE0lEQVQImWP4////f4bLly//BwAmVgd1/w11/gAAAABJRU5ErkJggg==") right repeat-y}';var r=e("../lib/dom");r.importCssString(t.cssText,t.cssClass)})