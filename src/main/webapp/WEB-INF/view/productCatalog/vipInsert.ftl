<h2 class="contentTitle">添加VIP会员目录</h2>

<script>
    $("#productCatalogVipBtn").click(function () {
        $.jBox.open("iframe:/product/catalog/vip/tree/get", "选择", 300, 420, {
            buttons: {"确定": "ok", "关闭": true}, submit: function (v, h, f) {
                if (v == "ok") {
                    var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
                    if (tree.selectedNode) {
                        //console.log(tree.selectedNode.id);
                        $("#parent_id_hidden_vip_insert").val(tree.selectedNode.id);
                        $("#parent_id_vip_insert").val(tree.selectedNode.text);
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
    <form method="post" action="/product/catalog/vip/insert" class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>上级目录：</dt>
                <dd>
                    <input id="parent_id_hidden_vip_insert" name="parent_name_hidden" class="required" type="hidden" value="-1">
                    <input type="text" id="parent_id_vip_insert" name="parent_name" maxlength="20" class="required" alt=""
                           readonly="readonly"/>
                    <a id="productCatalogVipBtn" class="button" href="javascript:void(0)"
                       rel="dlg_page8"><span>选择</span></a>
                <#--target="dialog"-->
                <#--<span class="info">class="required"</span>-->
                </dd>
            </dl>
            <dl>
                <dt>名称：</dt>
                <dd>
                    <input type="text" name="name" class="required" alt="请输入名称" maxlength="30"/>
                </dd>
            </dl>
        <#--<dl>-->
        <#--<dt>图标：</dt>-->
        <#--<dd>-->
        <#--<input type="text" name="icon" class="" alt="" maxlength="40"/>-->
        <#--</dd>-->
        <#--</dl>-->
            <dl>
                <dt>排序：</dt>
                <dd>
                    <input type="text" name="sort" class="digits" alt="" maxlength="10" class="required"/>
                </dd>
            </dl>
            <dl>
                <dt>可见：</dt>
                <dd>
                    <label><input type="radio" name="show" class="required" value="1"/>显示</label>
                    <label><input type="radio" name="show" class="required" value="0"/>隐藏</label>
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