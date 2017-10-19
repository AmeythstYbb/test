<#if version?? && version.id??>
<h2 class="contentTitle">修改版本</h2>
<#else>
<h2 class="contentTitle">添加新版本</h2>
</#if>
<div class="pageContent">

    <form method="post" action="/version/update" class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone)" enctype="multipart/form-data">
        <input type="hidden" value="${(version.id)!}" name="id">
        <div class="pageFormContent nowrap fuck_css" layoutH="97">
            <dl>
                <dt>版本名称：</dt>
                <dd>
                    <input type="text" name="name" class="required version_name" alt="" maxlength="19" size="40"
                           value="${(version.name)!""}"/>
                </dd>
            </dl>
            <dl>
                <dt>类型：</dt>
                <dd>
                    <select name="type" class="required">
                    <#if apps??>
                        <#list apps as app>
                            <#if (version.type)?? && app==(version.type)?string>
                                <option value="${app}" selected="selected">${app!}</option>
                            <#else>
                                <option value="${app}">${app!}</option>
                            </#if>
                        </#list>
                    </#if>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>code：</dt>
                <dd>
                    <input type="text" name="code" class="required digits" alt="" maxlength="40" size="40" max="9999"
                           value="${(version.code)!""}"/>
                </dd>
            </dl>
            <dl>
                <dt>minCode：</dt>
                <dd>
                    <input type="text" name="minCode" class="digits" alt="" maxlength="40" size="40" max="9999"
                           value="${(version.minCode)!""}"/>
                </dd>
            </dl>
            <dl>
                <dt>badCode：</dt>
                <dd>
                    <input type="text" name="badCode" class="digits" alt="" maxlength="40" size="40" max="9999"
                           value="${(version.badCode)!""}"/>
                </dd>
            </dl>
            <dl>
                <dt>更新链接：</dt>
                <dd>
                    <input type="text" name="url" class="required" alt="" maxlength="100" size="60"
                           value="${(version.url)!""}"/>
                </dd>
            </dl>
            <dl>
                <dt>版本描述：</dt>
                <dd><textarea name="desc" class="required" alt="" maxlength="1000" rows="7"
                              style="width: 366px">${(version.desc)!}</textarea>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">提交</button>
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