<h2 class="contentTitle">修改用户</h2>

<script>
    function initArea() {
        var str = ","; // 第一级选中项集合，以逗号相隔。
        $("#area input[name='area']").each(function (i, e) {
            //alert(a.value); //alert(a.name);
            if ($(e).attr("checked") == "checked") {
                str += e.value + ",";
            }
        });
        $("#areaId").val(str ? str : "-1");
        //console.log($("#areaId").val());
        //return false;
    }
</script>

<div class="pageContent">

<#if userBo??>
    <form method="post" action="/user/update" class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <input id="areaId" name="areaId" type="hidden" value="-1">
            <input id="id" name="id" type="hidden" value="${(userBo.id)!""}">
            <dl>
                <dt>工号：</dt>
                <dd>
                    <input type="text" readonly="readonly" name="jobNumber"
                           value="${userBo.jobNumber!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>用户名：</dt>
                <dd>
                    <input type="text" readonly="readonly" value="${(userBo.name)!""}"/>
                </dd>
            </dl>

            <dl>
                <dt>密码：</dt>
                <dd>
                    <input id="w_validation_pwd" type="password" name="password" class="alphanumeric"
                           minlength="5" maxlength="20" alt="字母、数字、下划线 5-20位"/>
                    <span class="info">若不修改密码，请留空。</span>
                <#--<span class="info">class="required alphanumeric" minlength="6" maxlength="20"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>确认密码：</dt>
                <dd>
                    <input type="password" name="repassword" equalto="#w_validation_pwd"/>
                <#--<span class="info">class="required" equalto="#xxxId"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>用户角色：</dt>
                <dd>
                    <#if dimission?? && dimission == 2><#--离职不可赋角色-->
                        离职员工，不可赋予角色
                    <#else>
                        <#if roleBoList??>
                            <#list roleBoList as role>
                                <#if userRole??>
                                    <#if (","+userRole+",")?index_of(","+role.id+",") != -1>
                                        <label><input type="checkbox" checked="checked" name="roleList"
                                                      value="${(role.id)!}"/>${(role.name)!}
                                        </label>

                                    <#else>
                                        <label><input type="checkbox" name="roleList"
                                                      value="${(role.id)!}"/>${(role.name)!}
                                        </label>
                                    </#if>
                                <#else>
                                    <label><input type="checkbox" name="roleList" value="${(role.id)!}"/>${(role.name)!}
                                    </label>
                                </#if>
                            </#list>
                        </#if>
                    </#if>
                </dd>
            </dl>
            <dl>
                <dt>业务区域：</dt>
                <dd>
                    <ul class="tree treeCheck expand" id="area">
                        <li><a>地域</a>
                            <ul>
                            <#--用户拥有的选中-->
                                <#if areaBoList??>
                                    <#list areaBoList as area>
                                        <#if userBo?? && userBo.area??>
                                            <#if (","+userBo.area+",")?index_of(","+area.name+",") != -1>
                                                <li><a tname="area" checked="true"
                                                       tvalue="${(area.name)!}">${(area.name)!}</a></li>
                                            <#else>
                                                <li><a tname="area" tvalue="${(area.name)!}">${(area.name)!}</a></li>
                                            </#if>
                                        <#else>
                                            <li><a tname="area" tvalue="${(area.name)!}">${(area.name)!}</a></li>
                                        </#if>
                                    </#list>
                                </#if>
                            </ul>
                        </li>
                    </ul>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit" onclick="initArea()">提交</button>
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

</#if>
</div>

