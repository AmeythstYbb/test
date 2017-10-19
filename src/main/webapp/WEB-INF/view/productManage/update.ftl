<div class="pageContent" >
    <form method="post" action="/feedBack/update" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone);">


        <div class="pageFormContent" layoutH="56">
            <input type="hidden" name="id" value="${(feedBackBo.id)!}">
            <#--<table class="searchContent">-->
                <dl>
                    <dt>状态：</dt>
                    <dd>
                        <select name="status" class="required">
                        <#if s_status??>
                            <#list s_status?keys as itemKey>
                                <#if (feedBackBo.status)?? && s_status[itemKey]==feedBackBo.status>
                                    <option value="${s_status[itemKey]}" selected="selected">${itemKey}</option>
                                <#else>
                                    <option value="${s_status[itemKey]}">${itemKey}</option>
                                </#if>
                            </#list>
                        </#if>
                        </select>
                    </dd>
                </dl>
            <#--</table>-->
            <dl>
                <dt>备注：</dt>
                    <#--<dd><input type="text" name="remarks" alt="请输入备注（选填）" value="${(feedBackBo.remarks)!''}" maxlength="50" size="50"></dd>-->
                    <dd><textarea name="remarks" class="" placeholder="请输入备注（选填）" maxlength="50" rows="3"
                              style="width: 320px">${(feedBackBo.remarks)!}</textarea>
                    </dd>
            </dl>
        </div>
            <div class="formBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="button" class="close">取消</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">确定</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
    </form>
</div>


<#--<div class="formBar">-->
<#--<ul>-->

<#--<li>-->
<#--<div class="button">-->
<#--<div class="buttonContent">-->
<#--<button type="button" class="close">取消</button>-->
<#--</div>-->
<#--</div>-->
<#--</li>-->
<#--<li>-->
<#--<div class="buttonActive">-->
<#--<div class="buttonContent">-->
<#--<button type="submit">确定</button>-->
<#--</div>-->
<#--</div>-->
<#--</li>-->
<#--</ul>-->
<#--</div>-->