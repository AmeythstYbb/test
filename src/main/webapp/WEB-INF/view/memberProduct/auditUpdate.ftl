<#--<link rel="stylesheet" type="text/css" href="/resources/diyUpload/css/webuploader.css">-->
<#--<link rel="stylesheet" type="text/css" href="/resources/diyUpload/css/diyUpload.css">-->
<#--<script type="text/javascript" src="/resources/diyUpload/js/webuploader.html5only.min.js"></script>-->
<#--<script type="text/javascript" src="/resources/diyUpload/js/diyUpload.js"></script>-->

<#--<style>-->
<#--#box {-->
<#--/*margin: 50px auto;*/-->
<#--border: 0.5px solid #cecece;-->
<#--border-radius: 4px;-->
<#--width: 750px;-->
<#--min-height: 180px;-->
<#--background: #f5f5f5-->
<#--}-->
<#--</style>-->

<#--直接引入css文件对其他页面造成样式冲突，直接将css文件中对数据复制到本页面-->
<link href="/resources/imgUp/css/common.css" type="text/css" rel="stylesheet"/>
<link href="/resources/imgUp/css/index.css" type="text/css" rel="stylesheet"/>
<script src="/resources/imgUp/js/imgPlugin.js"></script>
<#--<script src="/resources/imgUp/js/imgUp.js"></script>-->

<style>
    <#-- 分割线 -->

    .img-box {
        margin-top: 0px !important;
    }

    .full {
        width: 1091px;
        min-width: 1091px;
        margin: 0 auto;
    }

    .img-name-p {
        display: none !important;
    }

    .del-p {
        padding: 0 !important;
        width: auto !important;
        float: none !important;
    }

    .progressBar {
        width: auto;
        height: auto;
    }
</style>

<h2 class="contentTitle">产品信息修改(审核员)</h2>

<#--产品说明1000字内，其余正常50字内，一级目录4个字内，二级目录12字内，产品名称20字内，图片最多9张，最大5M，全必填-->
<div class="pageContent">

    <form method="post" action="/member/product/audit/update" class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone)" enctype="multipart/form-data">
        <input type="hidden" value="${(memberProductBo.id)!}" name="id">

    <#--<input type="hidden" name="servicePeopleName" value="${(default.servicePeopleName)!}">-->
    <#--<input type="hidden" name="servicePeopleCall" value="${(default.servicePeopleCall)!}">-->
        <input type="hidden" name="servicePeopleImUserName" value="${(default.servicePeopleImUserName)!}">
        <input type="hidden" name="servicePeopleId" value="${(default.servicePeopleId)!}">
        <input type="hidden" name="servicePeopleDocChatNum" value="${(default.servicePeopleDocChatNum)!}">
        <div class="pageFormContent nowrap fuck_css" layoutH="97">
            <dl>
                <dt>产品厂家名称：</dt>
                <dd>
                    <input type="text" name="productCompanyName" class="required" alt="" maxlength="50" size="50"
                           value="${(memberProductBo.productCompanyName)!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>产品名称：</dt>
                <dd>
                    <input type="text" name="productName" class="required" alt="" maxlength="20" size="50"
                           value="${(memberProductBo.productName)!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>产品所属专区：</dt>
                <dd>
                    <select name="vipType" class="required" onchange="getAuditType($(this).val())">
                        <option value="">--请选择--</option>
                    <#if vipType??>
                        <#list vipType?keys as itemKey>
                            <#if (memberProductBo.vipType)?? && itemKey==(memberProductBo.vipType)?string>
                                <option value="${itemKey}" selected="selected">${vipType[itemKey]}</option>
                            <#else>
                                <option value="${itemKey}">${vipType[itemKey]}</option>
                            </#if>
                        </#list>
                    </#if>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>产品一级类目：</dt>
                <dd>
                    <select id="auditType" name="auditType" class="required" onchange="getSubType($(this).val())">
                        <option value="">--请选择一级类目--</option>
                    <#if firstTypeList??>
                        <#list firstTypeList as firstType>
                            <#if (memberProductBo.firstType)?? && firstType.id == (memberProductBo.firstType)!>
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
                </dd>
            </dl>
            <dl>
                <dt>产品二级类目：</dt>
                <dd>
                    <select id="auditSubType" name="auditSubType" class="required"
                            onchange="getThirdType($(this).val())">
                    <#if secondTypeList??>
                        <#list secondTypeList as secondType>
                            <#if (memberProductBo.secondType)?? && secondType.id! ==(memberProductBo.secondType)!>
                                <option value="${secondType.id}"
                                        selected="selected">${secondType.name!}
                                </option>
                            <#else>
                                <option value="${secondType.id}">${secondType.name!}</option>
                            </#if>
                        </#list>
                    <#else>
                        <option value="">--请选择二级类目--</option>
                    </#if>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>产品三级类目：</dt>
                <dd>
                    <select id="auditThirdType" name="thirdType" class="required">
                    <#if thirdTypeList??>
                        <#list thirdTypeList as thirdType>
                            <#if (memberProductBo.thirdType)?? && thirdType.id! ==(memberProductBo.thirdType)!>
                                <option value="${thirdType.id}"
                                        selected="selected">${thirdType.name!}
                                </option>
                            <#else>
                                <option value="${thirdType.id}">${thirdType.name!}</option>
                            </#if>
                        </#list>
                    <#else>
                        <option value="">--请选择三级类目--</option>
                    </#if>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>一句话描述产品：</dt>
                <dd>
                    <input type="text" name="productDescription" class="required" alt="" maxlength="50" size="50"
                           value="${(memberProductBo.productDescription)!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>产品详细说明：</dt>
                <dd><textarea name="productDetail" class="required" alt="" maxlength="1000" rows="6"
                              style="width: 306px">${(memberProductBo.productDetail)!}</textarea>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>产品字号（药字、消字、健字、其他）：</dt>
                <dd>
                    <input type="text" name="productCode" class="" alt="" maxlength="50" size="50"
                           value="${(memberProductBo.productCode)!""}"/>
                </dd>
            </dl>
            <dl>
                <dt>产品可销售区域：</dt>
                <dd>
                <#if productSalesAreaList??>
                    <label><input type="checkbox" class="checkboxCtrl" group="productSalesArea">全选</label></br>
                    <#list productSalesAreaList as productSalesArea>
                        <#if productSalesArea?? && productSalesArea != "" && (memberProductBo.productSalesAreaText)??>
                            <#if (","+memberProductBo.productSalesAreaText+",")?index_of(","+productSalesArea+",") != -1>
                                <label><input type="checkbox" checked="checked" name="productSalesArea" class="required"
                                              value="${(productSalesArea)!}"/>${(productSalesArea)!}
                                </label>
                            <#else>
                                <label><input type="checkbox" name="productSalesArea" class="required"
                                              value="${(productSalesArea)!}"/>${(productSalesArea)!}
                                </label>
                            </#if>
                        <#else>
                            <label><input type="checkbox" name="productSalesArea" class="required"
                                          value="${(productSalesArea)!}"/>${(productSalesArea)!}
                            </label>
                        </#if>
                    </#list>
                </#if>
                </dd>
            </dl>
            <dl>
                <dt>产品市场价：</dt>
                <dd>
                    <input id="marketingPrice" type="text" name="marketingPrice" class="required number" alt=""
                           maxlength="15" size="50"
                           value="${(memberProductBo.marketingPrice)!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>朱李叶渠道价：</dt>
                <dd>
                    <#--<input id="zlyChannelPrice" type="text" name="zlyChannelPrice" class="required number price_渠道价" alt=""-->
                    <input id="zlyChannelPrice" type="text" name="zlyChannelPrice" class="required number" alt=""
                           maxlength="15" size="50"
                           value="${(memberProductBo.zlyChannelPrice)!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>建议可报销金额：</dt>
                <dd>
                    <label>${(memberProductBo.adviseRealPrice)!"无"}</label>
                </dd>
            </dl>
            <dl>
                <dt>厂家对接人：</dt>
                <dd>
                    <input type="text" name="broker" class="required" alt="" maxlength="50" size="50"
                           value="${(memberProductBo.broker)!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>对接人联系方式：</dt>
                <dd>
                    <input type="text" name="brokerPhone" class="required phone" alt="" maxlength="50" size="50"
                           value="${(memberProductBo.brokerPhone)!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>可报销金额：</dt>
                <dd>
                    <#--<input type="text" id="realPrice" name="realPrice" class="required textInput number price_validate_可报销金额"-->
                    <input type="text" id="realPrice" name="realPrice" class="required textInput number"
                           alt="" maxlength="12"
                           size="20"
                           value="${(memberProductBo.realPrice)!0}"/>
                </dd>
            </dl>
            <dl>
                <dt>咨询联系人：</dt>
                <dd>
                    <select name="servicePeopleName" class="required">
                        <option value="${(memberProductBo.servicePeopleName)!}">${(memberProductBo.servicePeopleName)!}</option>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>联系电话：</dt>
                <dd>
                    <select name="servicePeopleCall" class="required">
                        <option value="${(memberProductBo.servicePeopleCall)!}">${(memberProductBo.servicePeopleCall)!}</option>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>产品说明配图(没有可不上传)：</dt>
                <dd>
                    <input type="hidden" id="url_list" name="productPics">
                    <input type="hidden" id="url_list_delete" name="productDeletePics">
                    <div class="img-box full">
                        <section class=" img-section">
                            <p class="up-p"><#--作品图片：-->
                                <span class="up-span">最多可以上传9张图片，单张大小不超过5M，马上上传</span>
                            </p>
                            <div class="z_photo upimg-div clear">
                                <section class="z_file fl">
                                    <img src="/resources/imgUp/img/a11.png" class="add-img">
                                    <input type="file" name="file" id="file" class="file" value=""
                                           accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" multiple/>
                                </section>
                            </div>
                        </section>
                    </div>
                    <aside class="mask works-mask">
                        <div class="mask-content">
                            <p class="del-p">您确定要删除作品图片吗？</p>
                            <p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span>
                            </p>
                        </div>
                    </aside>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit" onclick="initPics();">提交</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
<script>
    $(function () {
        //var qn = "http://7j1ztl.com1.z0.glb.clouddn.com/";
        var qn = "http://dn-juliye.qbox.me/";
    <#if memberProductBo?? && memberProductBo.productPics??>
        var pic_size = '${(memberProductBo.productPics)!?size}';
        if (pic_size != 0) {
            <#list memberProductBo.productPics as pic>
                var pic = '${pic}';
                var pic_part = pic.split(qn)[1];
                initPic(pic, pic_part);
            </#list>
        }
    </#if>
    });

    $("#file").takungaeImgup({
        formData: {
            "path": "artScene/",
            "name": "this_pic"
        },
        url: "/member/product/audit/upload",
        success: function (data) {
            // console.log(data);
            if (data && data.name && data.value) {
                var url = data.value.toString();
                //console.log(url_list.indexOf(url));
                //从url_list_delete删除
                url_list_delete.remove(url);
                // 添加到url_list
                // if (url_list.indexOf(url) == -1)
                url_list.push(url);// 允许重复文件
                console.log("url_list:");
                console.log(url_list);
                console.log("url_list_delete:");
                console.log(url_list_delete);
            }
        },
        error: function (err) {
            alert(err);
        }
    });

    function initPics() {
        $("#url_list").val(url_list);
        $("#url_list_delete").val(url_list_delete);
    }

    //根据 产品专区类型 获取一级类目 列表
    function getAuditType(type) {
        var auditType = $("#auditType");
        var initStr = "<option value=''>--请选择一级类目--</option>";
        if (!type) {
            console.log("级联参数为空");
            auditType.html(initStr);
        }
        $.ajax({
            url: "/member/product/type/get",
            type: "post",
            dataType: "json",
            data: {type: type},
            async: false,//同步
            success: function (text) {
                //console.log(text);
                var str = "";
                for (var i = 0; i < text.length; i++) {
                    var array_element = text[i];
                    str += "<option value='" + array_element.id + "'>" + array_element.name + "</option>";
                }
                auditType.html(initStr + str);
            }
        });
        // 初始化产品(一)二三级目录
        $("#auditSubType").html("<option value=''>--请选择二级类目--</option>");
        $("#auditThirdType").html("<option value=''>--请选择三级类目--</option>");
    }

    //根据 一级类目ID 获取二级类目 列表
    function getSubType(id) {
        var auditSubType = $("#auditSubType");
        var initStr = "<option value=''>--请选择二级类目--</option>";
        if (!id) {
            console.log("级联参数为空");
            auditSubType.html(initStr);
        }
        $.ajax({
            url: "/member/product/subType/get",
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
                }
                auditSubType.html(initStr + str);
            }
        });
        // 初始化产品(二)三级目录
        $("#auditThirdType").html("<option value=''>--请选择三级类目--</option>");
    }

    //根据 二级类目ID 获取三级类目 列表
    function getThirdType(id) {
        var auditThirdType = $("#auditThirdType");
        var initStr = "<option value=''>--请选择三级类目--</option>";
        if (!id) {
            console.log("级联参数为空");
            auditThirdType.html(initStr);
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
                }
                auditThirdType.html(initStr + str);
            }
        });
        // 初始化产品(三)级目录
    }
</script>