<!--#include virtual="list1.html" -->
<script>
    /* 重置表单 */
    function resetForm() {
        var deptId = $("#deptId").val();
        var deptChildrenId = $("#deptChildrenId").val();
        //重置非隐藏域
        $('#pagerForm input').val("");
        $("#kind option:first").attr("selected", "selected");
        //console.log(1);
        // 隐藏域 重置
        //$("#pagerForm input").val("");
        //$("#pagerForm").submit();
        $("#deptId").val(deptId);
        $("#deptChildrenId").val(deptChildrenId);
    }
</script>
<div class="pageHeader" style="border:1px #B8D0D6 solid">
    <form id="pagerForm" onsubmit="return divSearch(this, 'jbsxBox');"
          action="/staff/list/new" method="post">
        <input name="pageNum" value="${result.pageNo!}" type="hidden"/>
        <input name="numPerPage" value="${(result.size)!}" type="hidden"/>
        <input name="deptId" id="deptId" value="${(deptId)!}" type="hidden"/>
        <input name="deptChildrenId" id="deptChildrenId" value="${(deptChildrenId)!}" type="hidden"/>
    <#--<input type="hidden" name="userName" value="${userName!}"/>-->
    <#--<input type="hidden" name="kind" value="${kind!}"/>-->
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>员工姓名：</td>
                    <td>
                        <input name="userName" class="textInput" value="${userName!}" type="text">
                    </td>
                    <td>
                        员工性质:
                    </td>
                    <td>
                        <select class="" name="kind" id="kind">
                            <option value="0">所有</option>
                            <option value="1">正式工</option>
                            <option value="2">实习生</option>
                            <option value="3">临时工</option>
                        </select>
                        <script>
                            $("#kind option").each(function () {
                            <#if kind?? && kind !="">
                                if (($(this).val()) && $(this).val() == '${(kind)!}') {
                                    $(this).attr("selected", "selected");
                                }
                            </#if>
                            });
                        </script>
                    </td>
                    <td width="100%">
                        <div class="buttonActive" style="float: right;">
                            <div class="buttonContent">
                                <button type="submit">检索</button>
                            </div>
                        </div>
                        <div class="button" style="float: right;">
                            <div class="buttonContent">
                                <button type="button" onclick="resetForm()">重置</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
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
    <table class="table" width="99%" layoutH="127" rel="jbsxBox">
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

    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select id="combox_shop_staff" class="combox" name="numPerPage"
                    onchange="navTabPageBreak({numPerPage:this.value},'jbsxBox')">
            <#assign configArr_4 = [10,20,50,100]>
            <#list configArr_4 as item_4>
                <option value="${item_4}" <#if (item_4==result.size)>selected="selected"</#if>>${item_4}</option>
            </#list>
            </select>
            <span>条，共${(result.total)}条</span>
        </div>
        <div class="pagination" totalCount="${(result.total)!}" numPerPage="${(result.size)!}"
             pageNumShown="10" currentPage="${(result.pageNo)}" rel="jbsxBox">
        </div>

    </div>
</div>