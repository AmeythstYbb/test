<link href="/resources/mztreeview/images/mztreeview.min.css" rel="stylesheet" type="text/css"/>
<script src="/resources/mztreeview/mzcommon.min.js" type="text/javascript"></script>
<script src="/resources/mztreeview/mztreedata.min.js" type="text/javascript"></script>
<script src="/resources/mztreeview/mztreeview.min.js" type="text/javascript"></script>
<div class="pageContent">
    <form method="post" action="/role/update" class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent" nowrap layoutH="56">
            <input id="menu_check" name="menu_check" type="hidden" value="-1">
            <input id="old_name" name="old_name" type="hidden" value="${(roleBo.name)!""}">
            <input id="old_name" name="roleId" type="hidden" value="${(roleBo.id)!""}">
            <dl>
                <dt>名称：</dt>
                <dd>
                    <input type="text" id="roleName" name="name" class="required" alt="请输入名称" maxlength="30"
                           value="${(roleBo.name)!}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>
            <div class="control-group">
                <label class="control-label">授权:</label>
                <div class="controls">
                    <div id="menuTree" style="margin-top:5px;float:left;"></div>
                    <form:hidden path="menuIds"/>
                </div>
            </div>
        </div>

        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
            <@shiro.hasPermission name="sys:role:insert">
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit" onclick="menu_check_()">保存</button>
                        </div>
                    </div>
                </li>
            </@shiro.hasPermission>
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
<script type="text/javascript">
    var menuTree;

    function menu_check_() {
        var tree_node = menuTree.getChecked();// 获取选中节点，逗号分隔
        if (!tree_node || typeof(tree_node) == "undefined" || null == tree_node)
            tree_node = "-1";// 未选择具体权限时默认参数
        $("#menu_check").val(tree_node);//将值赋给隐藏域，表单提交的时候传给后台
        //alert(tree_node);
    }

    $(document).ready(function () {
        //$("#menu_check").val("共和国");//初始化隐藏域的值,此处无效
        $("#name").focus();
        var menuData = {};
    <#if menuList?? && (menuList?size > 0)>
        <#list menuList as menu>
            var checkValue = false;
            <#if select??>
                <#if (","+select+",")?index_of(","+menu.idString+",") != -1>
                    checkValue = true;
                </#if>
            </#if>
            menuData['${(menu.parentId?? && menu.parentId == 0)?string('-1',menu.parentId)}_${(menu.id)!}'] = "text:${(menu.parentId?? && menu.parentId == 0)?string('权限列表',menu.name)};checked:" + checkValue;
        </#list>
    </#if>
//        checked:true;
//        console.log(menuData);
        menuTree = new MzTreeView(menuData);
        menuTree.useCheckbox = true;
        menuTree.linkFocus = false;
        menuTree.linkCheckbox = true;
        menuTree.isParentCheckbox = true;
        $("#menuTree").html(menuTree.render());
        menuTree.expandAll("1");

    });

</script>
