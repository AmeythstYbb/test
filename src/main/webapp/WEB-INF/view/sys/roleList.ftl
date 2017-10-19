<form id="pagerForm" method="post" action="demo_page1.html">
<#--<input type="hidden" name="status" value="${param.status}">-->
<#--<input type="hidden" name="keywords" value="${param.keywords}" />-->
    <input type="hidden" name="pageNum" value="1"/>
<#--<input type="hidden" name="numPerPage" value="${model.numPerPage}" />-->
<#--<input type="hidden" name="orderField" value="${param.orderField}" />-->
</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post"
          onreset="$(this).find('select.combox').comboxReset()">
        <div class="searchBar">

            <div class="subBar">
                <ul>
                <#--<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>-->
                    <#--<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>-->
                    <#--<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>-->
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">

        <@shiro.hasPermission name="sys:role:insert">
            <li><a class="add" href="/role/insert/view" target="navTab" rel="updateRole"><span>添加角色</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="sys:role:delete">
            <li><a class="delete" href="/role/delete?id={sid_user}" target="ajaxTodo"
                   title="确认要删除该角色吗?"><span>删除角色</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="sys:role:edit">
            <li><a class="edit" href="/role/update/view?id={sid_user}" rel="updateMenu"
                   target="navTab"><span>修改角色</span></a></li>
        </@shiro.hasPermission>
        <#--<li class="line">line</li>-->
        <#--<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
        </ul>
    </div>
    <table class="table" width="100%" layoutH="60">
        <thead>
        <tr>
            <th width="80">ID</th>
            <th>名称</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <#if roleList?? && (roleList?size > 0)>
            <#list roleList as role>
            <tr target="sid_user" rel="${(role.id)!"-1"}" id="${(role.id)!}">
                <td>${(role.id)!}</td>
                <td><a rel="updateRole" title="修改角色" target="navTab"
                       href="/role/update/view?id=${(role.id)!}">${(role.name)!}</a></td>
                <td>无</td>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
<#--<div class="panelBar">-->
<#--<div class="pages">-->
<#--<span>显示</span>-->
<#--<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">-->
<#--<option value="20">20</option>-->
<#--<option value="50">50</option>-->
<#--<option value="100">100</option>-->
<#--<option value="200">200</option>-->
<#--</select>-->
<#--<span>条，共$1{totalCount}条</span>-->
<#--</div>-->

<#--<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10"-->
<#--currentPage="1"></div>-->

<#--</div>-->
</div>
