<form id="pagerForm" class="pagerForm" method="post" action="/version/manage/list/get">
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
</form>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
        <@shiro.hasPermission name="version:manage:all">
            <li><a class="add" href="/version/update/view" target="navTab"
                   rel="updateVersion"><span>添加版本</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="version:manage:all">
            <li><a class="delete" href="/version/delete?id={sid_user}" target="ajaxTodo"
                   title="要删除该版本吗?"><span>删除版本</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="version:manage:all">
            <li><a class="edit" href="/version/update/view?id={sid_user}" rel="updateVersion"
                   target="navTab"><span>修改版本</span></a></li>
        </@shiro.hasPermission>
        </ul>
    </div>

    <table class="table" width="100%" style="table-layout:fixed;word-break:break-all;" layoutH="76">
        <thead>
        <tr>
            <th width="35">序号</th>
            <th width="88">创建时间</th>
            <th width="40">版本名称</th>
            <th width="85">类型</th>
            <th width="40">code</th>
            <th width="40">minCode</th>
            <th width="40">badCode</th>
            <th width="210">更新链接</th>
            <th width="210">版本描述</th>
        </tr>
        </thead>
    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as version>
                <tr id="${(version.id)!}" target="sid_user"
                    rel="${(version.id)!}">
                    <td>${(page.pageNo-1)*page.size+version_index+1}</td>
                    <td>${(version.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(version.name)!}</td>
                    <td>${(version.type)!}</td>
                    <td>${(version.code)!}</td>
                    <td>${(version.minCode)!}</td>
                    <td>${(version.badCode)!}</td>
                    <td>${(version.url)!}</td>
                    <td>${(version.desc)!}</td>
                </tr>
            </#list>
        </#if>
    </#if>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select id="" class="combox" name="numPerPage"
                    onchange="navTabPageBreak({numPerPage:this.value})">
            <#--<#assign configArr = [1,10,20,50,100]>-->
            <#assign configArr_2 = [25,50,100]>
            <#list configArr_2 as item_2>
                <option value="${item_2}" <#if (item_2==page.size)>selected="selected"</#if>>${item_2}</option>
            </#list>
            </select>
            <span>条，共${(page.total)!}条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="${page.total!}" numPerPage="${page.size!}"
             pageNumShown="10"
             currentPage="${page.pageNo!}"></div>
    </div>
</div>
