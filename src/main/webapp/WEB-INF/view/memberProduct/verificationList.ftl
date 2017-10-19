<form id="pagerForm" class="pagerForm_verification" method="post" action="/member/product/verification/list/get">
    <input type="hidden" name="productName" value="${(verificationRecordBean.productName)!}"/>
    <input type="hidden" name="docChatNum" value="${(verificationRecordBean.docChatNum)!}"/>
    <input type="hidden" name="checkVenderNum" value="${(verificationRecordBean.checkVenderNum)!}"/>
    <input type="hidden" name="checked_status" value="${(verificationRecordBean.checked_status)!}"/>
    <input type="hidden" name="startDate" value="${(pageUtilBean.startDate)!}"/>
    <input type="hidden" name="endDate" value="${(pageUtilBean.endDate)!}"/>
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
</form>

<script>
    /* 重置表单 */
    function resetForm_verification() {
        //重置非隐藏域
        $('#member_product_verification input').val("");
        $("#status_verification option:first").attr("selected", "selected");
        // 隐藏域 重置
        $(".pagerForm_verification input").val("");
    }
</script>

<style>
    /*.searchBar .searchContent .xxxx {*/
    /*height: 25px;*/
    /*padding-right: 18px;*/
    /*float: left;*/
    /*}*/
</style>
<div class="pageHeader">
    <form id="member_product_verification" onsubmit="return navTabSearch(this);"
          action="/member/product/verification/list/get"
          method="post"
          onreset="$(this).find('select.combox').comboxReset()">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td class="xxxx">
                        下单时间：
                        <input type="text" name="startDate" class="date" value="${(pageUtilBean.startDate)!}"
                               readonly="true"/>
                        <span class="limit">-</span>
                        <input type="text" name="endDate" class="date" value="${(pageUtilBean.endDate)!}"
                               readonly="true"/>
                    </td>
                    <td class="xxxx">用户热线号：<input type="text" name="docChatNum"
                                                  value="${(verificationRecordBean.docChatNum)!}"/>
                    </td>

                    <td class="xxxx">核销商户热线号：<input type="text" name="checkVenderNum"
                                                   value="${(verificationRecordBean.checkVenderNum)!}"/></td>

                    <td class="xxxx" style="float: none">产品名称：<input type="text" name="productName"
                                                                     value="${(verificationRecordBean.productName)!}"/>
                    </td>
                    <td style="padding-right: 1px">核销状态：</td>
                    <td class="xxxx">
                        <select class="" name="checked_status" id="status_verification">
                            <option value="">请选择</option>
                            <option value="0">&nbsp;待核销&nbsp;&nbsp;</option>
                            <option value="1">&nbsp;已核销&nbsp;&nbsp;</option>
                        </select>

                        <script>
                            $("#status_verification option").each(function () {
                            <#if verificationRecordBean?? && verificationRecordBean.checked_status?? && verificationRecordBean.checked_status !="">
                                if (($(this).val()) && $(this).val() == '${(verificationRecordBean.checked_status)!}') {
                                    $(this).attr("selected", "selected");
                                }
                            </#if>
                            });
                        </script>
                    </td>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                            <#--<button type="reset">重置</button>-->
                                <button type="button" onclick="resetForm_verification()">重置</button>
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
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">

    </div>

    <table class="table" width="100%" style="table-layout:fixed;word-break:break-all;" layoutH="138">
        <thead>
        <tr>
            <th width="60">序号</th>
            <th width="135">下单时间</th>
            <th width="100">用户姓名</th>
            <th width="100">用户热线号</th>
            <th width="95">用户手机号</th>
            <th width="160">产品名称</th>
            <th width="105">产品市场价</th>
            <th width="105">产品报销金额</th>
            <th width="80">核销状态</th>
            <th width="100">核销商户</th>
            <th width="100">核销商户热线号</th>
            <th width="95">核销商户手机号</th>
        </tr>
        </thead>
    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as verification_product>
                <tr id="${(verification_product.id)!}" target="sid_user"
                    rel="${(verification_product.id)!}">
                    <td>${(page.pageNo-1)*page.size+verification_product_index+1}</td>
                    <td>${(verification_product.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(verification_product.name)!}</td>
                    <td>${(verification_product.docChatNum)!}</td>
                    <td>${(verification_product.phoneNum)!}</td>
                    <td>${(verification_product.productName)!}</td>
                    <td>${(verification_product.marketingPrice)!}</td>
                    <td>${(verification_product.value)!}</td>
                    <td>
                        <#if verification_product?? && verification_product.checked_status?? && verification_product.checked_status != ""
                        && verification_product.checked_status == "1">
                            已核销
                        <#else>
                            待核销
                        </#if>
                    </td>
                    <td>${(verification_product.checkVenderName)!}</td>
                    <td>${(verification_product.checkVenderNum)!}</td>
                    <td>${(verification_product.checkVenderPhone)!}</td>
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
            <#assign configArr_2 = [20,50,100]>
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
