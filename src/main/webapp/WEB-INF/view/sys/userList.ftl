<form id="pagerForm" method="post" action="/user/search">
<#--<input type="hidden" name="status" value="${param.status}">-->
    <input type="hidden" name="number" value="${searchParam!}"/>
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
<#--<input type="hidden" name="orderField" value="${param.orderField}" />-->
</form>
<script>
    //    function resetParam(){
    //        $("#param").val("22");
    //    }
</script>

<div class="pageHeader">
    <form id="user_list" onsubmit="return navTabSearch(this);" action="/user/search" method="post"
    <#--onreset="resetParam()"-->
    >
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        工号：<input id="param" type="text" name="number" value="${searchParam!}"/>
                    </td>
                <#--<td>-->
                <#--建档日期：<input type="text" class="date" readonly="true"/>-->
                <#--</td>-->
                </tr>
            </table>
            <div class="subBar">
                <ul>
                <#--<li>-->
                <#--<div class="button">-->
                <#--<div class="buttonContent">-->
                <#--<button type="reset">重置</button>-->
                <#--</div>-->
                <#--</div>-->
                <#--</li>-->
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">检索</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
        <#--<@shiro.hasPermission name="sys:menu:delete">-->
        <#--<li><a class="delete" href="/user/delete?id={sid_user}" target="ajaxTodo"-->
        <#--title="确定要删除该用户吗?"><span>删除</span></a></li>-->
        <#--</@shiro.hasPermission>-->
        <@shiro.hasPermission name="sys:user:edit">
            <li><a class="edit" href="/user/update/view?id={sid_user}" rel="updateUser"
                   target="navTab"><span>修改用户</span></a>
            </li>
        </@shiro.hasPermission>

        <#--<li class="line">line</li>-->
        <#--<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab"-->
        <#--title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="75">序号</th>
            <th width="150">登录名(工号)</th>
            <th>部门</th>
            <th>子部门</th>
            <th>姓名</th>
            <th>角色</th>
            <th width="80">员工状态</th>
            <th>区域</th>
        <#--<th>业务板块</th>-->
            <th>操作</th>
            <th>备注</th>
        </tr>
        </thead>
    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as user>
                <tr id="${(user.id)!}" target="sid_user"
                    rel="${(user.id)!}">
                <#--<td>${(user.id)!}</td>-->
                    <td>${(page.pageNo-1)*page.size+user_index+1}</td>
                    <td><a rel="updateUser" title="修改用户" target="navTab"
                           href="/user/update/view?id=${(user.id)!}">${(user.jobNumber)!}</a></td>
                    <td>
                        <div>
                            <#if user?? && user.deptBo?? && user.deptBo.name??>
                            ${(user.deptBo.name)!}
                            <#else>
                                无
                            </#if>
                        </div>
                    </td>
                    <td>
                        <div>
                            <#if user?? && user.deptChildrenBo?? && user.deptChildrenBo.name??>
                            ${(user.deptChildrenBo.name)!}
                            <#else>
                                无
                            </#if>
                        </div>
                    </td>
                    <td>${(user.name)!}</td>
                    <td>${(user.roleNames)!}</td>
                    <td>
                    <#--style="text-align: center"-->
                        <#if user.status??>
                        ${statusMap[user.status?string]}
                        <#else>
                            无
                        </#if>
                    </td>
                    <td>
                    <#--<#if user.area?? && user.area.name??>-->
                    <#--${(user.area.name)!}-->
                    <#--<#else>-->
                    <#--无-->
                    <#--</#if>-->
                        无
                    </td>
                <#--<td>业务板块</td>-->
                <#--<td>-->
                <#--<#if user.business?? && user.business.name??>-->
                <#--${(user.business.name)!}-->
                <#--<#else>-->
                <#--无-->
                <#--</#if>-->
                <#--无-->
                <#--</td>-->

                    <td>无</td>
                    <td>
                        无
                    </td>
                </tr>
            </#list>
        </#if>
    </#if>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select id="combox_shop_user" class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
            <#--<#assign configArr = [1,10,20,50,100]>-->
            <#assign configArr_3 = [20,50,100]>
            <#list configArr_3 as item_3>
                <option value="${item_3}" <#if (item_3==page.size)>selected="selected"</#if>>${item_3}</option>
            </#list>
            </select>
            <span>条，共${(page.total)!}条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="${page.total!}" numPerPage="${page.size!}"
             pageNumShown="10"
             currentPage="${page.pageNo!}"></div>

    </div>
</div>
