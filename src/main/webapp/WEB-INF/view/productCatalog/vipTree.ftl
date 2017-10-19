<link href="/resources/mztreeview/images/mztreeview.min.css" rel="stylesheet" type="text/css"/>
<script src="/resources/mztreeview/mzcommon.min.js" type="text/javascript"></script>
<script src="/resources/mztreeview/mztreedata.min.js" type="text/javascript"></script>
<script src="/resources/mztreeview/mztreeview.min.js" type="text/javascript"></script>
<script src="/resources/mztreeview/jquery-1.9.1.min.js" type="text/javascript"></script>

<div id="vipTree" style="padding:15px 20px;"></div>
<script type="text/javascript">
    var newData = {};
    <#if data??>
        <#list data as da>
        newData['${(da.name)!}'] = '${(da.value)!}';
        </#list>
    </#if>
    //console.log(newData);
    var tree = new MzTreeView(newData);
    $("#vipTree").html(tree.render());
    tree.expandAll("1");
    tree.focusNodeByPath('${(parentIds)!}');

    $("#vipTree").bind("dblclick", function () {
        top.$.jBox.getBox().find("button[value='ok']").trigger("click");
    });
</script>