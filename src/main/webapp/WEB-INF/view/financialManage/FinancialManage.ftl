<form id="pagerForm" method="post" action="/FinancialManage/findAll">
    <input type="hidden" name="status" value="${searchParam_status!''}">
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
<#--<input type="hidden" name="status2" value="${(financialManageBo.status)!}">-->
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
    <form onsubmit="return navTabSearch(this);" action="/FinancialManage/findAll" method="post"
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
                            <option value="1,-2,2,3,-3">请选择</option>
                            <option value="1">&nbsp;待运营审核&nbsp;&nbsp;</option>
                            <option value="-2">&nbsp;运营拒绝&nbsp;&nbsp;</option>
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
            <#list page.data as financialManage>
                <tr id="${(financialManage.id)!}" target="sid_user" rel="${(financialManage.id)!}">
                    <td>${(page.pageNo-1)*page.size+financialManage_index+1}</td>
                    <td>${(financialManage.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(financialManage.applicantName)!}</td>
                    <td>${(financialManage.applicantPhone)!}</td>
                    <td>${(financialManage.sid)!}</td>
                    <td>${(financialManage.alipayName)!}</td>
                    <td>${(financialManage.alipayNum)!}</td>
                    <td>${(financialManage.bankCardName)!}</td>
                    <td>${(financialManage.area)!}</td>
                    <td>${(financialManage.bankName)!}</td>
                    <td>${(financialManage.subBankName)!}</td>
                    <td>${(financialManage.bankCardNum)!}</td>
                    <td>${(financialManage.cash)!}</td>
                <#--<td title="${(financialManage.status)!}">${(financialManage.status)!}</td>-->
                    <td> <#if financialManage?? && financialManage.status??>
                        <#if financialManage.status == 1>待运营审核
                        <#elseif financialManage.status == -2>运营拒绝
                        <#elseif financialManage.status == 2>待财务审核
                        <#elseif financialManage.status == -3>财务拒绝
                        <#elseif financialManage.status == 3>已完成
                        </#if>
                    </#if>
                    </td>

                    <td>${(financialManage.reason)!}</td>
                    <td>
                        <#if financialManage?? && financialManage.status??>
                            <#if financialManage.status == 2>待财务审核
                            <#elseif financialManage.status ==3>已完成
                            <#elseif financialManage.status ==-3>财务拒绝
                            <#elseif financialManage.status ==-2>运营拒绝
                            <#elseif financialManage.status == 1>
                                <a class="button" name="status2"
                                   href="/FinancialManage/agree/update?id=${(financialManage.id)!''}" target="ajaxTodo"><span>同意</span></a>
                                <a class="button" href="/FinancialManage/update/view?id=${(financialManage.id)!''}"
                                   target="dialog"
                                <#--rel="updateFinancialManage"-->
                                   width="500" height="200"
                                   fresh="true"><span>拒绝</span></a>
                            </#if>
                        </#if>
                    </td>
                <#--<td>sulong</td>-->
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
