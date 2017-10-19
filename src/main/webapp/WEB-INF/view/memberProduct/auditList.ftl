<form id="pagerForm" class="pagerForm__" method="post" action="/member/product/audit/view">
<#--<input type="hidden" name="status" value="${param.status}">-->
    <input type="hidden" name="status" value="${searchParam_status!}"/>
    <input type="hidden" name="vipType" value="${searchParam_vipType!}"/>
    <input type="hidden" name="firstType" value="${searchParam_firstType!}"/>
    <input type="hidden" name="secondType" value="${searchParam_secondType!}"/>
    <input type="hidden" name="thirdType" value="${searchParam_thirdType!}"/>
    <input type="hidden" name="startDate" value="${searchParam_startDate!}"/>
    <input type="hidden" name="productName" value="${searchParam_productName!}"/>
    <input type="hidden" name="endDate" value="${searchParam_endDate!}"/>
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
<#--<input type="hidden" name="orderField" value="${param.orderField}" />-->
</form>

<script>
    /* 重置表单 */
    function resetForm_a() {
        //重置非隐藏域
        $('#member_product_audit input').val("");
        $("#status__ option:first").attr("selected", "selected");
        $("#auditSearchVipType option:first").attr("selected", "selected");
        $("#auditSearchFirstType option:first").attr("selected", "selected");
        $("#auditSearchSecondType option:first").attr("selected", "selected");
        $("#auditSearchThirdType option:first").attr("selected", "selected");
        // 隐藏域 重置
        $(".pagerForm__ input").val("");
        //$("#pagerForm").submit();
    }

    //类目级联js
    //    function getSubType(type) {
    //        var subType = $("#subType_");
    //        var initStr = "<option value=''>请选择</option>";
    //        if (!type) {
    //            console.log("级联参数为空");
    //            subType.html(initStr);
    //        }
    //        $.ajax({
    //            url: "/member/product/subType/get",
    //            type: "post",
    //            dataType: "json",
    //            data: {type: type},
    //            async: false,//同步
    //            success: function (text) {
    //                //console.log(text);
    //                var str = "";
    //                for (var i = 0; i < text.length; i++) {
    //                    var array_element = text[i];
    //                    str += "<option value='" + array_element + "'>" + array_element + "</option>";
    //                    //console.debug(str);
    //                }
    //                subType.html(initStr + str);
    //            }
    //        });
    //    }

    // 产品专区 获取 一级类目列表
    function getAuditSearchFirstType(vipType) {
        var firstType = $("#auditSearchFirstType");
        var initStr = "<option value=''>--请选择--</option>";
        if (!vipType) {
            console.log("级联参数为空");
            firstType.html(initStr);
        }
        $.ajax({
            url: "/member/product/type/get",
            type: "post",
            dataType: "json",
            data: {type: vipType},
            async: false,//同步
            success: function (text) {
                //console.log(text);
                var str = "";
                for (var i = 0; i < text.length; i++) {
                    var array_element = text[i];
                    str += "<option value='" + array_element.id + "'>" + array_element.name + "</option>";
                    //console.debug(str);
                }
                firstType.html("").html(initStr + str);
            }
        });
        // 初始化产品(一)二三级目录
        $("#auditSearchSecondType").html("<option value=''>--请选择--</option>");
        $("#auditSearchThirdType").html("<option value=''>--请选择--</option>");
    }

    // 一级类目 获取 二级类目列表
    function getAuditSearchSecondType(id) {
        var secondType = $("#auditSearchSecondType");
        var initStr = "<option value=''>--请选择--</option>";
        if (!id) {
            console.log("级联参数为空");
            secondType.html(initStr);
        }
        $.ajax({
            url: "/member/product/subType/get",
            type: "post",
            dataType: "json",
            data: {id: id},
            async: false,//同步
            success: function (text) {
                var str = "";
                for (var i = 0; i < text.length; i++) {
                    var array_element = text[i];
                    str += "<option value='" + array_element.id + "'>" + array_element.name + "</option>";
                    //console.debug(str);
                }
                secondType.html("").html(initStr + str);
            }
        });
        // 初始化产品(二)三级目录
        $("#auditSearchThirdType").html("<option value=''>--请选择--</option>");
    }

    // 二级类目 获取 三级类目列表
    function getAuditSearchThirdType(id) {
        var thirdType = $("#auditSearchThirdType");
        var initStr = "<option value=''>--请选择--</option>";
        if (!id) {
            console.log("级联参数为空");
            thirdType.html(initStr);
        }
        $.ajax({
            url: "/member/product/thirdType/get",
            type: "post",
            dataType: "json",
            data: {id: id},
            async: false,//同步
            success: function (text) {
                //console.log(text);
                var str = "";
                for (var i = 0; i < text.length; i++) {
                    var array_element = text[i];
                    str += "<option value='" + array_element.id + "'>" + array_element.name + "</option>";
                    //console.debug(str);
                }
                thirdType.html("").html(initStr + str);
            }
        });
        // 初始化产品(三)级目录
    }
</script>

<div class="pageHeader">
    <form id="member_product_audit" onsubmit="return navTabSearch(this);" action="/member/product/audit/view"
          method="post"
          onreset="$(this).find('select.combox').comboxReset()"
    <#--onreset="resetParam()"-->
    >
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        起始日期：
                    </td>
                    <td><input type="text" name="startDate" class="date" value="${(searchParam_startDate)!}"
                               readonly="true"/>
                    </td>
                    <td>
                        截止日期：
                    </td>
                    <td><input type="text" name="endDate" class="date" value="${(searchParam_endDate)!}"
                               readonly="true"/>
                    </td>
                    <td>产品名称：</td>
                    <td><input type="text" name="productName" value="${searchParam_productName!}"/></td>

                    <td>状态：</td>
                    <td>
                    <#--<select class="combox" name="status" id="status">-->
                        <select class="" name="status" id="status__">
                            <option value="">--请选择--</option>
                            <option value="1,0,-1">&nbsp;全部&nbsp;&nbsp;</option>
                            <option value="0">&nbsp;待审核&nbsp;&nbsp;</option>
                            <option value="1">&nbsp;通过&nbsp;&nbsp;</option>
                            <option value="-1">&nbsp;拒绝&nbsp;&nbsp;</option>
                        </select>

                        <script>
                            $("#status__ option").each(function () {
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
                <tr>
                    <td>产品所属专区：</td>
                    <td>
                        <select id="auditSearchVipType" name="vipType" class="required"
                                onchange="getAuditSearchFirstType($(this).val())">
                            <option value="">--请选择--</option>
                        <#if vipType??>
                            <#list vipType?keys as itemKey>
                                <#if (searchParam_vipType)?? && itemKey==(searchParam_vipType)!>
                                    <option value="${itemKey}" selected="selected">${vipType[itemKey]}</option>
                                <#else>
                                    <option value="${itemKey}">${vipType[itemKey]}</option>
                                </#if>
                            </#list>
                        <#else>
                            <option value="">--请选择--</option>
                        </#if>
                        </select>
                    </td>
                    <td>一级类目：</td>
                    <td>
                        <select id="auditSearchFirstType" name="firstType" class=""
                                onchange="getAuditSearchSecondType($(this).val())">
                            <option value="">--请选择--</option>
                        <#if firstTypeList??>
                            <#list firstTypeList as firstType>
                                <#if searchParam_firstType?? && firstType.id == searchParam_firstType!>
                                    <option value="${firstType.id!}"
                                            selected="selected">${firstType.name!}
                                    </option>
                                <#else>
                                    <option value="${firstType.id!}">${firstType.name!}</option>
                                </#if>
                            </#list>
                        <#else>
                            <option value="">--请选择--</option>
                        </#if>
                        </select>
                    </td>
                    <td>二级类目：</td>
                    <td>
                        <select id="auditSearchSecondType" name="secondType" class=""
                                onchange="getAuditSearchThirdType($(this).val())">
                            <option value="">--请选择--</option>
                        <#if secondTypeList??>
                            <#list secondTypeList as secondType>
                                <#if searchParam_secondType?? && secondType.id! == searchParam_secondType!>
                                    <option value="${secondType.id}"
                                            selected="selected">${secondType.name!}
                                    </option>
                                <#else>
                                    <option value="${secondType.id}">${secondType.name!}</option>
                                </#if>
                            </#list>
                        <#else>
                            <option value="">--请选择--</option>
                        </#if>
                        </select>
                    </td>
                    <td>三级类目：</td>
                    <td>
                        <select id="auditSearchThirdType" name="thirdType" class="">
                            <option value="">--请选择--</option>
                        <#if thirdTypeList??>
                            <#list thirdTypeList as thirdType>
                                <#if searchParam_thirdType?? && thirdType.id! == searchParam_thirdType!>
                                    <option value="${thirdType.id}"
                                            selected="selected">${thirdType.name!}
                                    </option>
                                <#else>
                                    <option value="${thirdType.id}">${thirdType.name!}</option>
                                </#if>
                            </#list>
                        <#else>
                            <option value="">--请选择--</option>
                        </#if>
                        </select>
                    </td>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                            <#--<button type="reset">重置</button>-->
                                <button type="button" onclick="resetForm_a()">重置</button>
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
        <#--<li><a class="add" href="/member/product/commit/update/view" target="navTab"-->
                   <#--rel="updateProductCommit"><span>添加产品</span></a></li>-->
        </ul>
    </div>

    <table class="table" width="100%" style="table-layout:fixed;word-break:break-all;" layoutH="163">
        <thead>
        <tr>
            <th width="55">序号</th>
            <th width="70">提交人</th>
            <th width="125">提交日期</th>
            <th width="125">修改时间</th>
            <th width="130">产品厂家名称</th>
            <th width="260">产品名称</th>
            <th width="95">产品所属专区</th>

            <th width="49">图片</th>
            <th width="95">审核状态</th>
            <th width="80">上线</th>
            <th width="95">操作</th>
            <th width="95">上/下线备注</th>
            <th width="190">拒绝原因</th>

            <th width="60">类目</th>
            <th width="160">子类目</th>
            <th width="110">一级类目</th>
            <th width="110">二级类目</th>
            <th width="110">三级类目</th>
            <th width="130">产品可销售区域</th>
            <th width="180">描述</th>
            <th width="390">说明</th>
            <th width="260">产品字号</th>
        <#--<th width="49">图片</th>-->
            <th width="80">厂家对接人</th>
            <th width="120">厂家对接人联系方式</th>
            <th width="75">产品市场价</th>
            <th width="110">朱李叶渠道价</th>
        <#--<th width="95">审核状态</th>-->
        <#--<th width="190">拒绝原因</th>-->
            <th width="95">建议可报销金额</th>
            <th width="75">可报销金额</th>
            <th width="100">咨询联系人</th>
            <th width="90">联系电话</th>
        <#--<th width="80">上线</th>-->
        <#--<th width="80">操作</th>-->
        </tr>
        </thead>
    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as commit_product>
                <tr id="${(commit_product.id)!}" target="sid_user"
                    rel="${(commit_product.id)!}">
                    <td>${(page.pageNo-1)*page.size+commit_product_index+1}</td>
                    <td>${(commit_product.creator)!}</td>
                    <td>${(commit_product.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(commit_product.updateTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                    <td>${(commit_product.productCompanyName)!}</td>
                    <td>${(commit_product.productName)!}</td>
                    <td>${(commit_product.vipTypeName)!}</td>
                <#--修改按钮操作相关位置,,,,,,,-->
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
                    <td>
                        <#if commit_product?? && commit_product.status??>
                            <#if commit_product.status == 1>通过
                            <#elseif commit_product.status == -1>
                                拒绝
                            <#elseif commit_product.status ==0>
                            <#--<a title="确认要审核通过?" target="ajaxTodo"-->
                            <#--href="/member/product/audit?id=${(commit_product.id)!}&status=1"-->
                            <#--class="button"><span>通过</span></a>-->
                            <#--回显并修改四个字段-->
                                <a title="通过" target="dialog"
                                   href="/member/product/audit/dialog/view?id=${(commit_product.id)!}"
                                   width="475" height="305" fresh="false"
                                   class="button"><span>通过</span></a>
                                <a title="拒绝" target="dialog"
                                   href="/member/product/refuse/dialog/view?id=${(commit_product.id)!}"
                                   width="400" height="300" fresh="false"
                                   class="button"><span>拒绝</span></a>
                            </#if>
                        </#if>
                    </td>
                    <td>
                    <#--审核通过才出现上下线操作-->
                        <#if commit_product?? && commit_product.status?? && commit_product.status == 1 && commit_product.online??>
                            <#if commit_product.vipType?? && commit_product.realPrice?? && commit_product.servicePeopleName??
                            && commit_product.servicePeopleCall?? && commit_product.online == 1>
                                <#--<a title="确认要下线?" target="ajaxTodo"-->
                                   <#--href="/member/product/online?id=${(commit_product.id)!}&online=0"-->
                                   <#--class="button"><span>下线</span></a>-->
                                <a title="下线" target="dialog"
                                   href="/member/product/online/dialog/view?id=${(commit_product.id)!}&online=0"
                                   width="400" height="300" fresh="false"
                                   class="button"><span>下线</span></a>
                            </#if>
                            <#if commit_product.online == 0>
                                <#--<a title="确认要上线?" target="ajaxTodo"-->
                                   <#--href="/member/product/online?id=${(commit_product.id)!}&online=1"-->
                                   <#--class="button"><span>上线</span></a>-->
                                <a title="上线" target="dialog"
                                   href="/member/product/online/dialog/view?id=${(commit_product.id)!}&online=1"
                                   width="400" height="300" fresh="false"
                                   class="button"><span>上线</span></a>
                            </#if>
                        <#else>
                            <#if commit_product.online??>
                                未审核通过
                            <#else>

                            </#if>
                        </#if>
                    </td>
                    <td>
                        <#if commit_product?? && commit_product.status?? && commit_product.status == 1 && commit_product.online??>
                        <#--<a title="修改" target="dialog"-->
                        <#--href="/member/product/audit/dialog/view/another?id=${(commit_product.id)!}"-->
                        <#--width="490" height="345" fresh="false"-->
                        <#--class="button"><span>修改</span></a>-->
                            <@shiro.hasPermission name="product:audit:all">
                                <a class="button" href="/member/product/audit/update/view?id=${(commit_product.id)!}"
                                   rel="updateProductAudit" title="修改产品"
                                   target="navTab"><span>修改</span></a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="product:audit:all">
                                <a title="确认要删除" target="ajaxTodo"
                                   href="/member/product/delete?id=${(commit_product.id)!}"
                                   class="button"><span>删除</span></a>
                            </@shiro.hasPermission>
                        <#else>
                            <#if commit_product.online??>
                                未审核通过
                            <#else>

                            </#if>
                        </#if>
                    </td>
                    <td>${(commit_product.onlineRemark)!}</td>
                    <td>
                        <#if commit_product??&& commit_product.reason??>
                        ${(commit_product.reason)!}
                        <#else>
                        <#--无-->
                        </#if>
                    </td>

                    <td>${(commit_product.type)!}</td>
                    <td>${(commit_product.subType)!}</td>
                    <td>${(commit_product.firstTypeName)!}</td>
                    <td>${(commit_product.secondTypeName)!}</td>
                    <td>${(commit_product.thirdTypeName)!}</td>
                    <td>${(commit_product.productSalesAreaText)!}</td>
                    <td>${(commit_product.productDescription)!}</td>
                    <td>${(commit_product.productDetail)!}</td>
                    <td>${(commit_product.productCode)!}</td>
                    <td>${(commit_product.broker)!}</td>
                    <td>${(commit_product.brokerPhone)!}</td>
                    <td>${(commit_product.marketingPrice)!}</td>
                    <td>${(commit_product.zlyChannelPrice)!}</td>
                    <td>${(commit_product.adviseRealPrice)!}</td>
                    <td>${(commit_product.realPrice)!}</td>
                    <td>${(commit_product.servicePeopleName)!}</td>
                    <td>${(commit_product.servicePeopleCall)!}</td>
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
