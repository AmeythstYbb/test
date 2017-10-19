<div class="pageContent">
    <form method="post" action="/member/product/audit" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <input type="hidden" name="id" value="${(id)!}">
            <input type="hidden" name="status" value="1">

        <#--<input type="hidden" name="servicePeopleName" value="${(deafult.servicePeopleName)!}">-->
        <#--<input type="hidden" name="servicePeopleCall" value="${(deafult.servicePeopleCall)!}">-->
            <input type="hidden" name="servicePeopleImUserName" value="${(deafult.servicePeopleImUserName)!}">
            <input type="hidden" name="servicePeopleId" value="${(deafult.servicePeopleId)!}">
            <input type="hidden" name="servicePeopleDocChatNum" value="${(deafult.servicePeopleDocChatNum)!}">

            <input type="hidden" id="marketingPrice" name="marketingPrice"
                   value="${(memberProductBo.marketingPrice)!0}">
            <input type="hidden" id="zlyChannelPrice" name="zlyChannelPrice"
                   value="${(memberProductBo.zlyChannelPrice)!0}">
        <#--<dl>-->
        <#--<dt>类目：</dt>-->
        <#--<dd>-->
        <#--<select name="type" class="required" onchange="getSubType_u($(this).val())">-->
        <#--<option value="">--请选择--</option>-->
        <#--<#if typeList??>-->
        <#--<#list typeList as type>-->
        <#--<#if (memberProductBo.type)?? && type == (memberProductBo.type)!>-->
        <#--<option value="${type!}"-->
        <#--selected="selected">${type!}-->
        <#--</option>-->
        <#--<#else>-->
        <#--<option value="${type!}">${type!}</option>-->
        <#--</#if>-->
        <#--</#list>-->
        <#--<#else>-->
        <#--<option value="">--请选择--</option>-->
        <#--</#if>-->
        <#--</select>-->
        <#--</dd>-->
        <#--</dl>-->
        <#--<dl>-->
        <#--<dt>子类目：</dt>-->
        <#--<dd>-->
        <#--<select id="subType_a" name="subType" class="required">-->
        <#--<#if subTypeList??>-->
        <#--<#list subTypeList as subType>-->
        <#--<#if (memberProductBo.subType)?? && subType! ==(memberProductBo.subType)!>-->
        <#--<option value="${subType}"-->
        <#--selected="selected">${subType!}-->
        <#--</option>-->
        <#--<#else>-->
        <#--<option value="${subType}">${subType!}</option>-->
        <#--</#if>-->
        <#--</#list>-->
        <#--<#else>-->
        <#--<option value="">--请选择--</option>-->
        <#--</#if>-->
        <#--</select>-->
        <#--</dd>-->
        <#--</dl>-->
        <#--<dl>-->
        <#--<dt>产品名称：</dt>-->
        <#--<dd>-->
        <#--<input type="text" name="productName" class="required" alt="" maxlength="20" size="20"-->
        <#--value="${(memberProductBo.productName)!""}"/>-->
        <#--&lt;#&ndash;<span class="info">class="required email"</span>&ndash;&gt;-->
        <#--</dd>-->
        <#--</dl>-->
            <dl>
                <dt>建议可报销金额：</dt>
                <dd>
                    <label>${(memberProductBo.adviseRealPrice)!"无"}</label>
                </dd>
            </dl>
            <dl>
                <dt>可报销金额：</dt>
                <dd>
                <#--<input type="text" id="realPrice" name="realPrice" class="required textInput number price_validate_可报销金额"-->
                    <input type="text" id="realPrice" name="realPrice" class="required textInput number"
                           alt="" maxlength="20"
                           size="20"
                           value=""/>
                <#--可报销金额 <  市场价格 -  渠道价格-->
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
        <#--<dl>-->
        <#--<dt>清单类型：</dt>-->
        <#--<dd>-->
        <#--<select name="vipType" class="required">-->
        <#--<#if vipType??>-->
        <#--<#list vipType?keys as itemKey>-->
        <#--&lt;#&ndash;<#if (staff.gender)?? && itemKey==(staff.gender)?string>&ndash;&gt;-->
        <#--&lt;#&ndash;<option value="${itemKey}" selected="selected">${ageMap[itemKey]}</option>&ndash;&gt;-->
        <#--&lt;#&ndash;<#else>&ndash;&gt;-->
        <#--&lt;#&ndash;<option value="${itemKey}">${ageMap[itemKey]}</option>&ndash;&gt;-->
        <#--&lt;#&ndash;</#if>&ndash;&gt;-->
        <#--<option value="${itemKey}" selected="selected">${vipType[itemKey]}</option>-->
        <#--</#list>-->
        <#--</#if>-->
        <#--</select>-->
        <#--</dd>-->
        <#--</dl>-->
            <dl>
                <dt>咨询联系人：</dt>
                <dd>
                    <select name="servicePeopleName" class="required">
                        <option value="${(deafult.servicePeopleName)!}">${(deafult.servicePeopleName)!}</option>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>联系电话：</dt>
                <dd>
                    <select name="servicePeopleCall" class="required">
                        <option value="${(deafult.servicePeopleCall)!}">${(deafult.servicePeopleCall)!}</option>
                    </select>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">保存</button>
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
<#--<script>-->
<#--//类目级联js-->
<#--function getSubType_u(type) {-->
<#--var subType = $("#subType_a");-->
<#--var initStr = "<option value=''>--请选择二级类目--</option>";-->
<#--if (!type) {-->
<#--console.log("级联参数为空");-->
<#--subType.html(initStr);-->
<#--}-->
<#--$.ajax({-->
<#--url: "/member/product/subType/get",-->
<#--type: "post",-->
<#--dataType: "json",-->
<#--data: {type: type},-->
<#--async: false,//同步-->
<#--success: function (text) {-->
<#--//console.log(text);-->
<#--var str = "";-->
<#--for (var i = 0; i < text.length; i++) {-->
<#--var array_element = text[i];-->
<#--str += "<option value='" + array_element + "'>" + array_element + "</option>";-->
<#--//console.debug(str);-->
<#--}-->
<#--subType.html(initStr + str);-->
<#--}-->
<#--});-->
<#--}-->
<#--</script>-->
