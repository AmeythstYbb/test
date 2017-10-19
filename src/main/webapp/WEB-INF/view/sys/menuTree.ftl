<link href="/resources/mztreeview/images/mztreeview.min.css" rel="stylesheet" type="text/css"/>
<script src="/resources/mztreeview/mzcommon.min.js" type="text/javascript"></script>
<script src="/resources/mztreeview/mztreedata.min.js" type="text/javascript"></script>
<script src="/resources/mztreeview/mztreeview.min.js" type="text/javascript"></script>
<script src="/resources/mztreeview/jquery-1.9.1.min.js" type="text/javascript"></script>

<div id="tree" style="padding:15px 20px;"></div>
<script type="text/javascript">
    //    var data={};data['-1_1']='text: 顶级菜单';
    //    data['5_17']='text: 用户增加';
    //    data['6_8']='text: 角色修改';
    //    data['5_15']='text: 用户查看';
    //    data['4_13']='text: 菜单增加';
    //    data['4_11']='text: 菜单查看';
    //    data['5_18']='text: 用户删除';
    //    data['6_9']='text: 角色增加';
    //    data['5_16']='text: 用户修改';
    //    data['6_7']='text: 角色查看';
    //    data['4_14']='text: 菜单删除';
    //    data['4_12']='text: 菜单修改';
    //    data['6_10']='text: 角色删除';
    //    data['3_4']='text: 菜单管理';
    //    data['3_5']='text: 用户管理';
    //    data['3_6']='text: 角色管理';
    //    data['2_3']='text: 系统管理';
    //    data['1_2']='text: 系统设置';

    //    var tree = new MzTreeView(data);
    <#--console.log(${(data)!});-->
    <#--var tree = new MzTreeView(${(data)!});-->
    <#--if ("${checked}" == "true") {-->
    <#--tree.useCheckbox = true;-->
    <#--tree.linkFocus = false;-->
    <#--tree.linkCheckbox = true;-->
    <#--tree.isParentCheckbox = true;-->
    <#--}-->
    <#--tree.loadJsData("${(url)!}");-->
    var newData = {};
    <#if data??>
        <#list data as da>
        newData['${(da.name)!}'] = '${(da.value)!}';
        </#list>
    </#if>
    var tree = new MzTreeView(newData);
    $("#tree").html(tree.render());
    tree.expandAll("1");
    <#--console.log('${(parentIds)!?replace(",","_")}');-->
    <#--tree.focusNodeByPath('${(parentIds)!}');-->
    tree.focusNodeByPath('${(parentIds)!}');
    <#--tree.focus('${(parentIds)!}');-->

    $("#tree").bind("dblclick", function () {
        //console.log(tree.selectedNode.path);
        top.$.jBox.getBox().find("button[value='ok']").trigger("click");
        //alert($("input[type='text']", top.mainFrame.document).val());
        //$("input[type='text']", top.mainFrame.document).focus();
    });
</script>