<#--<div class="accordion" style="width:180px;float:left;margin:0px;" >-->
    <#--<div class="accordionHeader">-->
        <#--<h2><span>Folder</span>界面组件</h2>-->
    <#--</div>-->
    <#--<div class="accordionContent" layoutH="20">-->
        <#--<ul class="tree treeFolder">-->
            <#--<li><a href="tabsPage.html" target="navTab">框架面板</a>-->
                <#--<ul>-->
                    <#--<li><a href="main.html" target="navTab" rel="main">我的主页</a></li>-->
                    <#--<li><a href="demo_page1.html" target="navTab" rel="page1">页面一</a></li>-->
                    <#--<li><a href="demo_page1.html" target="navTab" rel="page1">替换页面一</a></li>-->
                    <#--<li><a href="demo_page2.html" target="navTab" rel="page2">页面二</a></li>-->
                    <#--<li><a href="demo_page4.html" target="navTab" rel="page3" title="页面三（自定义标签名）">页面三</a></li>-->
                <#--</ul>-->
            <#--</li>-->

            <#--<li><a href="w_panel.html" target="navTab" rel="w_panel">面板</a></li>-->
            <#--<li><a href="w_tabs.html" target="navTab" rel="w_tabs">选项卡面板</a></li>-->
            <#--<li><a href="w_dialog.html" target="navTab" rel="w_dialog">弹出窗口</a></li>-->
            <#--<li><a href="w_alert.html" target="navTab" rel="w_alert">提示窗口</a></li>-->
            <#--<li><a href="w_list.html" target="navTab" rel="w_list">CSS表格容器</a></li>-->
            <#--<li><a href="demo_page1.html" target="navTab" rel="w_table">表格容器</a></li>-->
            <#--<li><a href="w_tree.html" target="navTab" rel="w_tree">树形菜单</a></li>-->
            <#--<li><a href="w_editor.html" target="navTab" rel="w_editor">编辑器</a></li>-->
        <#--</ul>-->
    <#--</div>-->
<#--</div>-->


<form id="pagerForm" method="post" action="/staff/search">
    <input name="pageNum" value="1" type="hidden"/>
    <input name="numPerPage" value="${(result.size)!}" type="hidden"/>
</form>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="/staff/search" method="post"
          onreset="$(this).find('select.combox').comboxReset()">
    <#--<input name="pageNum" value="1" type="hidden"/>-->
    <#--<input name="numPerPage" value="${(result.size)!}" type="hidden"/>-->
        <div class="searchBar">
            <table class="searchContent">
                <tbody>
                <tr>
                    <td>员工姓名：</td>
                    <td>
                        <input name="userName" class="textInput" type="text">
                    </td>
                    <td>
                        员工性质:
                    </td>
                    <td>
                        <select class="combox" name="kind">
                            <option value="0">所有</option>
                            <option value="1">正式工</option>
                            <option value="2">实习生</option>
                            <option value="3">临时工</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="reset">重置</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">检索</button>
                            </div>
                        </div>
                    </li>
                <#--<li><a class="button" href="/staff/search" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>-->
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
        <@shiro.hasPermission name="sys:staff:insert">
            <li class="add"><a class="add" href="/staff/detail" target="navTab" rel="updateStaff"><span>员工添加</span></a>
            </li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="sys:staff:delete">
            <li class="delete"><a class="delete" href="/staff/delete?staffId={sid_user}" target="ajaxTodo"
                                  title="确定要删除吗?"><span>员工删除</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="sys:staff:edit">
            <li><a class="edit" href="/staff/detail?staffId={sid_user}" target="navTab"
                   rel="updateStaff"><span>员工修改</span></a></li>
        </@shiro.hasPermission>

        <#--<li class="line">line</li>-->
        <#--<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targettype="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="30">
                序号
                <#--不是id-->
            </th>
            <th with="38">
                员工号
            </th>
            <th with="46">
                员工姓名
            </th>
            <th with="20">
                性别
            </th>
            <th with="89">
                一级部门
            </th>
            <th with="89">
                二级部门
            </th>
            <th with="50">
                级别
            </th>
            <th with="34">
                员工状态
            </th>
            <th with="71">
                员工性质
            </th>
            <th with="71">
                出生日期
            </th>
            <th with="71">
                入职日期
            </th>
            <th with="71">
                转正日期
            </th>
        </tr>
        </thead>
        <tbody>
        <#if result?? && result.data?? && (result.data?size > 0) >
            <#list result.data as staff>
            <tr target="sid_user" rel="${(staff.id)!}">
                <td>
                    <div>${(result.pageNo-1)*result.size+staff_index+1}</div>
                </td>
                <td>
                    <div>${(staff.jobNumber)!}</div>
                </td>
                <td>
                    <div>${(staff.userName)!}</div>
                </td>
                <td>
                    <div>
                        <#if staff.gender??>
                            ${ageMap[staff.gender?string]}
                        </#if>
                    </div>
                </td>
                <td>
                    <div>
                        <#if staff?? && staff.deptBo?? && staff.deptBo.name??>
                        ${(staff.deptBo.name)!}
                        <#else>
                            无
                        </#if>
                    </div>
                </td>
                <td>
                    <div>
                        <#if staff?? && staff.deptChildrenBo?? && staff.deptChildrenBo.name??>
                        ${(staff.deptChildrenBo.name)!}
                        <#else>
                            无
                        </#if>
                    </div>
                </td>
                <td>
                    <div>${(staff.staffLevel)!}</div>
                </td>
                <td>
                    <div>
                        <#if staff.status??>
                            ${statusMap[staff.status?string]}
                        </#if>
                    </div>
                </td>
                <td>
                    <div>
                        <#if staff.kind??>
                            ${kindMap[staff.kind?string]}
                        </#if>
                    </div>
                </td>
                <td>
                    <div>${(staff.birthday?string('yyyy-MM-dd'))!}</div>
                </td>
                <td>
                    <div>${(staff.entryDate?string('yyyy-MM-dd'))!}</div>
                </td>
                <td>
                    <div>${(staff.postDate?string('yyyy-MM-dd'))!}</div>
                </td>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<div class="panelBar">
    <div class="pages">
        <span>显示</span>
        <select id="combox_shop_staff" class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
        <#assign configArr_4 = [5,10,20,50,100]>
        <#list configArr_4 as item_4>
            <option value="${item_4}" <#if (item_4==result.size)>selected="selected"</#if>>${item_4}</option>
        </#list>
        </select>
        <span>条，共${(result.total)}条</span>
    </div>
    <div class="pagination" targetType="navTab" totalCount="${(result.total)!}" numPerPage="${(result.size)!}"
         pageNumShown="10" currentPage="${(result.pageNo)}">
    </div>
</div>