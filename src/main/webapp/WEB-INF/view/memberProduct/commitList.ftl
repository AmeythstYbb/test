<form id="pagerForm" class="pagerForm_c" method="post" action="/member/product/commit/view">
<#--<input type="hidden" name="status" value="${param.status}">-->
    <input type="hidden" name="status" value="${searchParam_status!}"/>
    <input type="hidden" name="productName" value="${searchParam_productName!}"/>
    <input type="hidden" name="startDate" value="${searchParam_startDate!}"/>
    <input type="hidden" name="endDate" value="${searchParam_endDate!}"/>
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
<#--<input type="hidden" name="orderField" value="${param.orderField}" />-->
</form>

<script>
    /* 重置表单 */
    function resetForm_c() {
        //重置非隐藏域
        $('#member_product_commit input').val("");
        $("#status option:first").attr("selected", "selected");
        // 隐藏域 重置
        $(".pagerForm_c input").val("");
        //$("#pagerForm").submit();
    }
</script>

<div class="pageHeader">
    <form id="member_product_commit" onsubmit="return navTabSearch(this);" action="/member/product/commit/view"
          method="post"
          onreset="$(this).find('select.combox').comboxReset()"
    <#--onreset="resetParam()"-->
    >
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        起始日期：<input type="text" name="startDate" class="date" value="${(searchParam_startDate)!}"
                                    readonly="true"/>
                    </td>
                    <td>
                        截止日期：<input type="text" name="endDate" class="date" value="${(searchParam_endDate)!}"
                                    readonly="true"/>
                    </td>
                    <td>产品名称：<input type="text" name="productName" value="${searchParam_productName!}"/></td>
                    <td>状态：</td>
                    <td>
                    <#--<select class="combox" name="status" id="status">-->
                        <select class="" name="status" id="status">
                            <option value="">请选择</option>
                            <option value="1,0,-1">&nbsp;全部&nbsp;&nbsp;</option>
                            <option value="0">&nbsp;待审核&nbsp;&nbsp;</option>
                            <option value="1">&nbsp;通过&nbsp;&nbsp;</option>
                            <option value="-1">&nbsp;拒绝&nbsp;&nbsp;</option>
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
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                            <#--<button type="reset">重置</button>-->
                                <button type="button" onclick="resetForm_c()">重置</button>
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
        <ul class="toolBar">
            <li><a class="add" href="/member/product/commit/update/view" target="navTab"
                   rel="updateProductCommit"><span>添加产品</span></a></li>
        </ul>
    </div>

    <table class="table" width="100%" style="table-layout:fixed;word-break:break-all;" layoutH="138">
        <thead>
        <tr>
            <th width="55">序号</th>
            <th width="125">添加日期</th>
            <th width="130">产品厂家名称</th>
            <th width="260">产品名称</th>
            <th width="100">产品所属专区</th>
            <th width="60">类目</th>
            <th width="140">子类目</th>
            <th width="110">一级类目</th>
            <th width="110">二级类目</th>
            <th width="110">三级类目</th>
            <th width="130">产品可销售区域</th>
            <th width="180">描述</th>
            <th width="390">说明</th>
            <th width="260">产品字号</th>
            <th width="49">图片</th>
            <th width="80">厂家对接人</th>
            <th width="120">厂家对接人联系方式</th>
            <th width="75">产品市场价</th>
            <th width="110">朱李叶渠道价</th>
            <th width="120">建议可报销金额</th>
            <th width="80">状态</th>
            <th width="190">拒绝原因</th>
            <th width="80">操作</th>
        </tr>
        </thead>
    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as commit_product>
                <tr id="${(commit_product.id)!}" target="sid_user"
                    rel="${(commit_product.id)!}">
                    <td>${(page.pageNo-1)*page.size+commit_product_index+1}</td>
                    <td>${(commit_product.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(commit_product.productCompanyName)!}</td>
                    <td>${(commit_product.productName)!}</td>
                    <td>${(commit_product.vipTypeName)!}</td>
                    <td>${(commit_product.type)!}</td>
                    <td>${(commit_product.subType)!}</td>
                    <td>${(commit_product.firstTypeName)!}</td>
                    <td>${(commit_product.secondTypeName)!}</td>
                    <td>${(commit_product.thirdTypeName)!}</td>
                    <td>${(commit_product.productSalesAreaText)!}</td>
                    <td>${(commit_product.productDescription)!}</td>
                    <td>${(commit_product.productDetail)!}</td>
                    <td>${(commit_product.productCode)!}</td>
                    <td style="text-align: center !important;">
                        <#if commit_product?? && commit_product.productPics?? && commit_product.productPics?size != 0>
                            <a class="button" href="/member/product/pic?id=${commit_product.id!}"
                               target="dialog" rel="dlg_member_product"
                               width="800" height="600"
                               fresh="true"><span>查看</span></a>
                        <#else>
                            无
                        </#if>
                    </td>
                    <td>${(commit_product.broker)!}</td>
                    <td>${(commit_product.brokerPhone)!}</td>
                    <td>${(commit_product.marketingPrice)!}</td>
                    <td>${(commit_product.zlyChannelPrice)!}</td>
                    <td>${(commit_product.adviseRealPrice)!}</td>
                    <td>
                        <#if commit_product?? && commit_product.status??>
                            <#if commit_product.status == 1>审核通过
                            <#elseif commit_product.status == -1>
                                审核被拒
                            <#elseif commit_product.status ==0>
                                审核中
                            </#if>
                        </#if>
                    </td>
                    <td>
                        <#if commit_product??&& commit_product.reason??>
                        ${(commit_product.reason)!}
                        <#else>
                        <#--无-->
                        </#if>
                    </td>
                    <td>
                        <#if commit_product?? && commit_product.status?? && commit_product.status == -1>
                        <@shiro.hasPermission name="product:commit:all">
                            <a class="btnEdit" href="/member/product/commit/update/view?id=${(commit_product.id)!}"
                               rel="updateProductCommit" title="修改"
                               target="navTab"><span>修改产品</span></a>
                        </@shiro.hasPermission>
                        </#if>
                    </td>
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
