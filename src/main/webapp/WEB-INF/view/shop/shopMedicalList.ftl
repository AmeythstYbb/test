<form id="pagerForm" class="pagerForm_m" method="post" action="/shop/medical/list/get">
<#--<input type="hidden" name="status" value="${param.status}">-->
    <input type="hidden" name="status" value="${searchParam_status!}"/>
    <input type="hidden" name="startDate" value="${searchParam_startDate!}"/>
    <input type="hidden" name="endDate" value="${searchParam_endDate!}"/>
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
<#--<input type="hidden" name="orderField" value="${param.orderField}" />-->
</form>
<script>
    /* 重置表单 */
    function resetForm_m() {
        //重置非隐藏域
        $('#shop_medical input').val("");
        $("#status_m option:first").attr("selected", "selected");
        // 隐藏域 重置
        $(".pagerForm_m input").val("");
        //$("#pagerForm").submit();
    }
</script>

<div class="pageHeader">
    <form id="shop_medical" onsubmit="return navTabSearch(this);" action="/shop/medical/list/get" method="post"
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
                    <td>状态：</td>
                    <td>
                    <#--<select class="combox" name="status" id="status">-->
                        <select class="" name="status" id="status_m">
                            <option value="">请选择</option>
                            <option value="1,0,-1">&nbsp;全部&nbsp;&nbsp;</option>
                            <option value="0">&nbsp;待审核&nbsp;&nbsp;</option>
                            <option value="1">&nbsp;通过&nbsp;&nbsp;</option>
                            <option value="-1">&nbsp;拒绝&nbsp;&nbsp;</option>
                        </select>

                        <script>
                            <#--<#if searchParam_status?? && searchParam_status !="">-->
                            <#--var z = ${(searchParam_status)!""};-->
                            <#--console.log(z);-->
                            <#--</#if>-->

                            $("#status_m option").each(function () {
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
                                <button type="button" onclick="resetForm_m()">重置</button>
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
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="60">序号</th>
            <th width="130">申请时间</th>
            <th>地区</th>
            <th width="180px">商户名称</th>
            <th>地址</th>
            <th>商户类型</th>
            <th>子类型</th>
            <th width="100px">电话</th>
            <th>热线号</th>
            <th width="60px">商户头像</th>
            <th width="60px">营业执照</th>
            <th width="65px">状态</th>
            <th>备注</th>
        </tr>
        </thead>
    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as shop>
                <tr id="${(shop.id)!}" target="sid_user"
                    rel="${(shop.id)!}">
                    <td>${(page.pageNo-1)*page.size+shop_index+1}</td>
                    <td>${(shop.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(shop.shopCity)!}</td>
                    <td>${(shop.shopName)!}</td>
                    <td>
                        <a href="http://www.gpsspg.com/maps.htm?maps=5&s=${shop.shopAddressMapLat!},${shop.shopAddressMapLon!}"
                           target="_BLANK">
                        ${(shop.shopAddress)!}</a></td>
                    <td>${(shop.shopType)!}</td>
                    <td>${(shop.shopSubType)!}</td>
                    <td>${(shop.shopPhoneNum)!}</td>
                    <td>${(shop.applicantDocChatNum)!"暂无"}</td>
                    <td style="text-align: center !important;">
                        <a class="button" href="/shop/medical/avatar?avatar=${shop.shopAvatar!}" target="dialog" rel="dlg_shop"
                           width="800" height="600"
                           fresh="true"><span>查看</span></a>
                    </td>
                    <td><a class="button" href="/shop/medical/avatar?avatar=${shop.shopLicense!}" target="dialog" rel="dlg_shop"
                           width="800" height="600"
                           fresh="true"><span>查看</span></a>
                    </td>
                    <td>
                        <#if shop?? && shop.status??>
                            <#if shop.status == 1>通过
                            <#elseif shop.status == -1>
                                拒绝
                            <#elseif shop.status ==0>
                            <#--<a title="通过" target="navTab" href="/shop/pass?id=${(shop.id)!}" class="btnEdit">通过</a>-->
                            <#--<a title="通过" target="navTab" href="/shop/pass?id=${(shop.id)!}" class="btnAdd">通过</a>-->
                            <#--<a title="通过" target="navTab" href="/shop/pass?id=${(shop.id)!}" class="btnDel">通过</a>-->
                            <#--<a title="通过" target="navTab" href="/shop/pass?id=${(shop.id)!}" class="btnSelect">通过</a>-->
                            <#--<a title="通过" target="navTab" href="/shop/pass?id=${(shop.id)!}" class="btnInfo">通过</a>-->
                            <#--<a title="通过" target="navTab" href="/shop/pass?id=${(shop.id)!}" class="btnAssign">通过</a>-->
                            <#--<a title="通过" target="navTab" href="/shop/pass?id=${(shop.id)!}" class="btnLook">通过</a>-->
                            <#--<a title="通过" target="navTab" href="/shop/pass?id=${(shop.id)!}" class="btnAttach">通过</a>-->
                                <a title="确认要审核通过?" target="ajaxTodo"
                                   href="/shop/medical/audit?id=${(shop.id)!}&status=1"
                                   class="btnSelect">通过</a>
                                <a title="拒绝" target="dialog" href="/shop/medical/refuse/view?id=${(shop.id)!}"
                                   width="400" height="300" fresh="false"
                                   class="btnDel">拒绝</a>
                            </#if>

                        </#if>
                    </td>
                    <td>
                        <#if shop??&& shop.reason??>
                        ${(shop.reason)!}
                        <#else>
                        <#--无-->
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
            <select id="combox_shop_medical" class="combox" name="numPerPage"
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
