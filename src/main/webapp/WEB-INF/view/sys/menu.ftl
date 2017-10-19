<#--<link href="/resources/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>-->
<#--<link href="/resources/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>-->
<#--<link href="/resources/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>-->
<#--<link href="/resources/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>-->
<#--<!--[if IE]>-->
<#--<link href="/resources/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>-->
<#--<![endif]&ndash;&gt;-->
<#--<style type="text/css">-->
<#--#header {-->
<#--height: 85px-->
<#--}-->

<#--#leftside, #container, #splitBar, #splitBarProxy {-->
<#--top: 90px-->
<#--}-->
<#--</style>-->
<#--<!--[if lt IE 9]>-->
<#--<script src="/resources/dwz/js/speedup.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]&ndash;&gt;-->
<#--<!--[if gte IE 9]><!&ndash;&gt;-->
<#--<script src="/resources/dwz/js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]&ndash;&gt;-->
<#--<link href="/resources/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet"/>-->
<#--<script src="/resources/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>-->
<#--<script src="/resources/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/js/jquery.cookie.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/js/jquery.validate.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/bin/dwz.min.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>-->
<#--<script src="/resources/dwz/js/dwz.combox.js" type="text/javascript"></script>-->

<#--<script type="text/javascript">-->
<#--$(function () {-->
<#--DWZ.init("/resources/dwz/dwz.frag.xml", {-->
<#--loginUrl: "/resources/dwz/login_dialog.html", loginTitle: "登录",	// 弹出登录对话框-->
<#--//		loginUrl:"login.html",	// 跳到登录页面-->
<#--statusCode: {ok: 200, error: 300, timeout: 301}, //【可选】-->
<#--keys: {statusCode: "statusCode", message: "message"}, //【可选】-->
<#--pageInfo: {-->
<#--pageNum: "pageNum",-->
<#--numPerPage: "numPerPage",-->
<#--orderField: "orderField",-->
<#--orderDirection: "orderDirection"-->
<#--}, //【可选】-->
<#--debug: false,	// 调试模式 【true|false】-->
<#--callback: function () {-->
<#--initEnv();-->
<#--$("#themeList").theme({themeBase: "themes"});-->
<#--setTimeout(function () {-->
<#--//                    $("#sidebar .toggleCollapse div").trigger("click");-->
<#--}, 10);-->
<#--}-->
<#--});-->
<#--});-->
<#--</script>-->

<#--<div class="toggleCollapse"><h2>主菜单</h2>-->
<#--<div>收缩</div>-->
<#--</div>-->
<#--<div class="accordion" fillspace="sidebar">-->
<#--<div class="accordionContent">-->

<div class="accordion" fillSpace="sidebar">
<#--&lt;#&ndash;<div class="accordionHeader">&ndash;&gt;-->
<#--&lt;#&ndash;<h2><span>Folder</span>界面组件</h2>&ndash;&gt;-->
<#--&lt;#&ndash;</div>&ndash;&gt;-->
    <div class="accordionContent">
        <ul class="tree treeFolder">
        <#if menuBoList?? && (menuBoList?size > 0)>
            <#list menuBoList as menu>
                <#if rootId?? && menu.parentId?? && menu.id?? && menu.show??>
                    <#if (menu.parentId == rootId) && (menu.show == "1")>
                        <li>
                        <#--文件夹不能点击跳转-->
                            <a href="javascript:void(0)" target=""
                               id="menu_left_${(menu.id)!}">${(menu.name)!}</a>
                            <ul>
                                <#list menuBoList as menuChildren>
                                    <#if menuChildren.parentId == menu.id && menuChildren.show == "1">
                                        <li>
                                            <a href="${(menuChildren.href)!}" target="navTab"
                                               rel="${(menuChildren.target)!}"
                                               id="menu_left_${(menu.id)!}_${(menuChildren.id)!}">${(menuChildren.name)!}</a>
                                        <#--<ul>-->
                                        <#--<li> <a href="javascript:void(0)" target="" id="menu_left_${(menu.id)!}">${(menu.name)!}</a></li>-->
                                        <#--<li> <a href="javascript:void(0)" target="" id="menu_left_${(menu.id)!}">${(menu.name)!}</a></li>-->
                                        <#--</ul>-->
                                        </li>
                                    </#if>
                                </#list>
                            </ul>
                        </li>
                    </#if>
                </#if>
            </#list>
        </#if>
        </ul>
    </div>
<#--</div>-->
<#--</div>-->
</div>



