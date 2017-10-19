<form id="pagerForm" method="post" action="/feedBack/findAll">
    <input type="hidden" name="status" value="${searchParam_status!''}">
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>

</form>
<script>
    /* 重置表单 */
    function resetForm() {
        //重置非隐藏域
        $('#shop_all input').val("");
        $("#status option:first").attr("selected", "selected");
        // 隐藏域 重置
        $("#pagerForm input").val("");
        //$("#pagerForm").submit();
    }
</script>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="/feedBack/findAll" method="post"
    <#--onreset="$(this).find('select.combox').comboxReset()"-->
    <#--onreset="resetForm();"-->
    <#--onreset="resetParam()"-->
    >
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>状态：</td>
                    <td>
                        <select class="" name="status" id="status">
                            <option value="1,0,2">请选择</option>
                            <option value="0">&nbsp;待处理&nbsp;&nbsp;</option>
                            <option value="1">&nbsp;忽略&nbsp;&nbsp;</option>
                            <option value="2">&nbsp;已处理&nbsp;&nbsp;</option>

                        </select>

                        <script>

                            $("#status option").each(function () {
                            <#if searchParam_status?? && searchParam_status !="">
                                if (($(this).val()) && $(this).val() == '${(searchParam_status)!}') {
                                    $(this).attr("selected", "selected");
                                <#--console.log($(this).val() + ":" + ${(searchParam_status)})!;-->
                                }
                            </#if>
                            });
                        </script>
                    </td>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <#--<li>-->
                        <#--<div class="button">-->
                            <#--<div class="buttonContent">-->
                            <#--&lt;#&ndash;<button type="reset">重置</button>&ndash;&gt;-->
                                <#--<button type="button" onclick="resetForm()">重置</button>-->
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
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="30">序号</th>
            <th width="130">反馈时间</th>
            <th width="60">热线号</th>
            <th width="100">手机号</th>
            <th width="60">姓名</th>
            <th width="140">反馈内容</th>
            <th width="30">状态</th>
            <th width="100">备注</th>
            <th width="60">操作</th>
        </tr>
        </thead>


    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as feedBack>
                <tr id="${(feedBack.id)!}" target="sid_user" rel="${(feedBack.id)!}">
                    <td>${(page.pageNo-1)*page.size+feedBack_index+1}</td>
                    <td>${(feedBack.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(feedBack.docChatNum)!}</td>
                    <td>${(feedBack.phoneNum)!}</td>
                    <td>${(feedBack.name)!}</td>
                    <#if (feedBack.content)?? && (feedBack.content)!="">
                        <td title="${(feedBack.content)!}">${(feedBack.content)!}</td>
                        <#else>
                            <td>${(feedBack.content)!}</td>
                    </#if>
                    <td> <#if feedBack?? && feedBack.status??>
                        <#if feedBack.status == 0>待处22222理
                        <#elseif feedBack.status == 2>已处理
                        <#elseif feedBack.status ==1>忽略
                        </#if>
                    </#if>
                    </td>
                    <#if (feedBack.remarks)?? && (feedBack.remarks)!="">
                        <td title="${(feedBack.remarks)!}">
                        ${(feedBack.remarks)!}
                       </td>
                    <#else>
                        <td>
                        ${(feedBack.remarks)!}
                        </td>
                    </#if>
                    <td style="text-align: center !important;">
                        <a class="button" href="/feedBack/update/view?id=${(feedBack.id)!''}" target="dialog"
                           <#--rel="updatefeedBack"-->
                           width="500" height="200"
                           fresh="true"><span>修改状态</span></a>
                    </td>
                </tr>
            </#list>
        </#if>
    </#if>
    </table>

    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select id="combox_feedBack_findAll" class="combox" name="numPerPage"
                    onchange="navTabPageBreak({numPerPage:this.value})">
            <#--<#assign configArr = [1,10,20,50,100]>-->
            <#assign configArr_1 = [20,50,100]>
            <#list configArr_1 as item_1>
                <option value="${item_1}" <#if (item_1==page.size)>selected="selected"</#if>>${item_1}</option>
            </#list>
            </select>
            <span>条，共${(page.total)!}条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="${page.total!}" numPerPage="${page.size!}"
             pageNumShown="10"
             currentPage="${page.pageNo!}"></div>
    </div>
</div>
