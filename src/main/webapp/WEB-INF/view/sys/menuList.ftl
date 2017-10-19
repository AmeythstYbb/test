<link href="/resources/treeTable/themes/vsStyle/treeTable.css" rel="stylesheet" type="text/css"/>
<script src="/resources/treeTable/jquery.treeTable.js"></script>

<form id="pagerForm" method="post" action="demo_page1.html">
<#--<input type="hidden" name="status" value="${param.status}">-->
<#--<input type="hidden" name="keywords" value="${param.keywords}" />-->
    <input type="hidden" name="pageNum" value="1"/>
<#--<input type="hidden" name="numPerPage" value="${model.numPerPage}" />-->
<#--<input type="hidden" name="orderField" value="${param.orderField}" />-->
</form>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">

        <@shiro.hasPermission name="sys:menu:insert">
            <li><a class="add" href="/menu/insert/view" target="navTab" rel="updateMenu"><span>添加菜单</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="sys:menu:delete">
            <li><a class="delete" href="/menu/delete?id={sid_user}" target="ajaxTodo"
                   title="要删除该菜单及所有子菜单项吗?"><span>删除菜单</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="sys:menu:edit">
            <li><a class="edit" href="/menu/update/view?id={sid_user}" rel="updateMenu"
                   target="navTab"><span>修改菜单</span></a></li>
        </@shiro.hasPermission>
        <#--<li class="line">line</li>-->
        <#--<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
        </ul>
    </div>

<#--calss = "table" 使用dwz的table初始化，和treeTable插件的初始化冲突，放弃-->
<#--使用dwz的list样式-->
<#--layoutH="80" 底部高度-->
    <table id="treeTable" class="list" width="100%" layoutH="60">
        <thead>
        <tr>
            <th width="250px">名称</th>
            <th width="220px">链接</th>
            <th>目标</th>
            <th width="80px">排序</th>
            <th width="80px">可见</th>
            <th>权限标识</th>
            <th>操作</th>
        </tr>
        </thead>

    <#if menuBoList?? && (menuBoList?size > 0)>
        <#list menuBoList as menu>
            <#if menu.parentId??><#--不用去除ID为1的顶级目录，在controller中已经去掉-->
                <tr id="${(menu.id)!}" pId="${(menu.parentId != 1)?string(menu.parentId,'0')}" target="sid_user"
                    rel="${(menu.id)!}">
                    <td><a rel="updateMenu" title="修改菜单" target="navTab"
                           href="/menu/update/view?id=${(menu.id)!}">${(menu.name)!}</a></td>
                    <td>${(menu.href)!}</td>
                    <td>${(menu.target)!}</td>
                    <td>${(menu.sort)!}</td>
                    <td>
                        <#if menu.show =='1' >
                            显示
                        <#else>
                            隐藏
                        </#if>
                    </td>
                    <td>${(menu.permission)!}</td>
                    <td>
                        无
                    </td>
                </tr>
            </#if>
        </#list>
    </#if>
    </table>
</div>
<script>
    $(document).ready(function () {
        $("#treeTable").treeTable({expandLevel: 5});
    });
</script>
