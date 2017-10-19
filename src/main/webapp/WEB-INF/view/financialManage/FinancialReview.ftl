<form id="pagerForm" method="post" action="/FinancialReview/findAll">
    <input type="hidden" name="status" value="${searchParam_status!''}">
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
<#--<input type="hidden" name="status2" value="${(financialReviewBo.status)!}">-->
    <input type="hidden" name="applicantPhone" value="${(applicantPhone)!}">
    <input type="hidden" name="alipayNum" value="${(alipayNum)!}">
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
    <form onsubmit="return navTabSearch(this);" action="/FinancialReview/findAll" method="post"
    <#--onreset="$(this).find('select.combox').comboxReset()"-->
    <#--onreset="resetForm();"-->
    <#--onreset="resetParam()"-->
    >
        <div class="searchBar">

            <table class="searchContent">
                <tr>
                    <td>手机号：</td>
                    <td>
                        <input name="applicantPhone" class="textInput" value="${applicantPhone!}" type="text">
                    </td>
                    <td>
                    <td>状态：</td>
                    <td>
                        <select class="" name="status" id="status">
                            <option value="2,3,-3">请选择</option>
                            <option value="2">&nbsp;待财务审核&nbsp;&nbsp;</option>
                            <option value="-3">&nbsp;财务拒绝&nbsp;&nbsp;</option>
                            <option value="3">&nbsp;已完成&nbsp;&nbsp;</option>
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
                    <td>支付方式：</td>
                    <td>
                        <select class="" name="alipayNum">
                        <#if alipayNum?? && alipayNum == "0">
                            <option value="0" selected="selected">请选择</option>
                        <#else>
                            <option value="0">请选择</option>
                        </#if>

                        <#if alipayNum?? && alipayNum =="1">
                            <option value="1" selected="selected">支付宝</option>
                        <#else>
                            <option value="1">支付宝</option>
                        </#if>

                        <#if alipayNum?? && alipayNum == "2">
                            <option value="2" selected="selected">銀行卡</option>
                        <#else>
                            <option value="2">银行卡</option>
                        </#if>
                        </select>
                    </td>
                    <td>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                <li>
                <#--<div class="button">-->
                <div class="buttonContent">
                <#--<button type="reset">重置</button>-->
                <#--<button type="button" onclick="resetForm()">结果回填</button>-->
                <#--<a class="button" href="/FinancialManage/update/view?id=${(financialManage.id)!''}"-->
                    <#--href  是访问 Controller中 方法的 路径-->
                    <a class="button" href="/FinancialReview/upload/view?id=${(financialReview.id)!''}"
                       target="dialog"
                    <#--rel="updateFinancialReview"-->
                       width="500" height="200"
                       fresh="true"><span>结果回填</span></a>
                </div>

                </li>

                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">查询</button>
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
    <table class="table"width="100%" style="table-layout:fixed;word-break:break-all;"  layoutH="138">
        <thead>
        <tr>
            <th width="30">序号</th>
            <th width="160">申请时间</th>
            <th width="60">申请人</th>
            <th width="60">手机号</th>
            <th width="150">身份证</th>
            <th width="60">支付宝姓名</th>
            <th width="150">支付宝账号</th>
            <th width="90">银行账户名</th>
            <th width="100">地区</th>
            <th width="120">银行</th>
            <th width="130">支行</th>
            <th width="150">银行卡号</th>
            <th width="50">金额</th>
            <th width="130">状态</th>
            <th width="120">备注</th>
            <th width="120">操作</th>
        </tr>
        </thead>


    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as financialReview>
                <tr id="${(financialReview.id)!}" target="sid_user" rel="${(financialReview.id)!}">
                    <td>${(page.pageNo-1)*page.size+financialReview_index+1}</td>
                    <td>${(financialReview.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(financialReview.applicantName)!}</td>
                    <td>${(financialReview.applicantPhone)!}</td>
                    <td>${(financialReview.sid)!}</td>
                    <td>${(financialReview.alipayName)!}</td>
                    <td>${(financialReview.alipayNum)!}</td>
                    <td>${(financialReview.bankCardName)!}</td>
                    <td>${(financialReview.area)!}</td>
                    <td>${(financialReview.bankName)!}</td>
                    <td>${(financialReview.subBankName)!}</td>
                    <td>${(financialReview.bankCardNum)!}</td>
                    <td>${(financialReview.cash)!}</td>
                <#--<td title="${(financialReview.status)!}">${(financialReview.status)!}</td>-->
                    <td> <#if financialReview?? && financialReview.status??>
                        <#if financialReview.status == 2>待财务审核
                        <#elseif financialReview.status == -3>财务拒绝
                        <#elseif financialReview.status == 3>已完成
                        </#if>
                    </#if>
                    </td>
                <#--<td title=" ${(feedBack.remarks)!}">-->
                <#--<#if feedBack??&& feedBack.remarks??>-->
                <#--${(feedBack.remarks)!}-->
                <#--<#else>-->
                <#--&lt;#&ndash;无&ndash;&gt;-->
                <#--</#if></td>-->
                    <td>${(financialReview.reason)!}</td>
                    <td> </td>

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
