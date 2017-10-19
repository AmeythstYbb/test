<h2 class="contentTitle">修改VIP会员目录</h2>

<script>
    $("#vipUpdateBtn").click(function () {
        $.jBox.open("iframe:/product/catalog/vip/tree/get?id=${(productCatalogBo.id)!}", "选择", 300, 420, {
            buttons: {"确定": "ok", "关闭": true}, submit: function (v, h, f) {
                if (v == "ok") {
                    var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
                    if (tree.selectedNode) {
                        $("#parent_id_hidden_vip_update").val(tree.selectedNode.id);
                        $("#parent_id_vip_update").val(tree.selectedNode.text);
                    <#--${id}ParentIds = tree.selectedNode.path;-->
                    }
                }
            }, loaded: function (h) {
                $(".jbox-content", top.document).css("overflow-y", "hidden");
            }
        });
    });
</script>

<div class="pageContent">

<#if productCatalogBo??>
    <form method="post" action="/product/catalog/vip/update" class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>上级目录：</dt>
                <dd>
                    <input id="id_hidden_vip_update" name="id_name_hidden" class="required" type="hidden"
                           value="${(productCatalogBo.id)!""}">
                    <input id="parent_id_hidden_vip_update" name="parent_name_hidden" class="required"
                           type="hidden"
                           value="${(parentVipId)!""}">
                    <input type="text" id="parent_id_vip_update" name="parent_name" maxlength="20" class="required"
                           alt=""
                           value="${(parentVipName)!""}"
                           readonly="readonly"/>
                    <a id="vipUpdateBtn" class="button" href="javascript:void(0)"
                       rel="dlg_page8"><span>选择</span></a>
                </dd>
            </dl>
            <dl>
                <dt>名称：</dt>
                <dd>
                    <input type="text" name="name" class="required" alt="请输入名称" maxlength="20"
                           value="${productCatalogBo.name!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
        <#--<dl>-->
        <#--<dt>图标：</dt>-->
        <#--<dd>-->
        <#--<input type="text" name="icon" class="" alt="" maxlength="90" value="${(menuBo.icon)!""}"/>-->
        <#--</dd>-->
        <#--</dl>-->
            <dl>
                <dt>排序：</dt>
                <dd>
                    <input type="text" name="sort" class="digits" alt="" maxlength="10" class="required"
                           value="${(productCatalogBo.sort)!""}"/>
                </dd>
            </dl>
            <dl>
                <dt>可见：</dt>
                <dd>
                    <#if productCatalogBo.show == 1>
                        <label><input type="radio" name="show" class="required" checked="checked"
                                      value="1"/>显示</label>
                        <label><input type="radio" name="show" class="required"
                                      value="0"/>隐藏</label>
                    <#else>
                        <label><input type="radio" name="show" class="required"
                                      value="1"/>显示</label>
                        <label><input type="radio" name="show" class="required" checked="checked"
                                      value="0"/>隐藏</label>
                    </#if>
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

</#if>
</div>

