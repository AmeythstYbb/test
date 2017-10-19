<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=7"/>
    <title>24热线BOSS系统</title>
    <link rel="Shortcut Icon" href="/resources/dwz/themes/default/images/favicon.ico"/>

    <link href="/resources/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="/resources/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="/resources/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <link href="/resources/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
    <!--[if IE]>
    <link href="/resources/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
    <![endif]-->
    <style type="text/css">
        #header {
            height: 85px
        }

        #leftside, #container, #splitBar, #splitBarProxy {
            top: 90px
        }
    </style>

    <!--[if lt IE 9]>
    <script src="/resources/dwz/js/speedup.js" type="text/javascript"></script>
    <script src="/resources/dwz/js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
    <!--[if gte IE 9]><!-->
    <script src="/resources/dwz/js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->
    <link href="/resources/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet"/>
    <script src="/resources/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="/resources/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
    <script src="/resources/dwz/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="/resources/dwz/js/jquery.validate.js" type="text/javascript"></script>
    <script src="/resources/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
    <script src="/resources/dwz/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
    <script src="/resources/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
    <#--<script src="/resources/dwz/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>-->
    <script src="/resources/dwz/js/dwz.combox.js" type="text/javascript"></script>
    <script src="/resources/dwz/bin/dwz.min.js" type="text/javascript"></script>
    <script src="/resources/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<#--自定义js-->
    <script src="/resources/js/util.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function () {
            DWZ.init("/resources/dwz/dwz.frag.xml", {
                loginUrl: "/resources/dwz/login_dialog.html", loginTitle: "登录",	// 弹出登录对话框
                //loginUrl:"login.html",	// 跳到登录页面
                statusCode: {ok: 200, error: 300, timeout: 301}, //【可选】
                keys: {statusCode: "statusCode", message: "message"}, //【可选】
                pageInfo: {
                    pageNum: "pageNum",
                    numPerPage: "numPerPage",
                    orderField: "orderField",
                    orderDirection: "orderDirection"
                }, //【可选】
                debug: false,	// 调试模式 【true|false】
                callback: function () {
                    initEnv();
                    $("#themeList").theme({themeBase: "themes"});
                    setTimeout(function () {
                        // $("#sidebar .toggleCollapse div").trigger("click");
                        $("#navMenu ul li.selected a").click();// 点击第一个一级菜单
                    }, 10);
                }
            });
        });
    </script>
</head>

<body scroll="no">
<div id="layout">
    <div id="header">
        <div class="headerNav">
        <#--<a class="logo" href="http://j-ui.com">标志</a>-->
            <ul class="nav">
            <#--<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>-->
            <@shiro.user>
                <li>
                    <a href="javascript:void(0)" target="_blank" style="text-decoration:none;">
                        <@shiro.principal property="name"/>&nbsp;<@shiro.principal property="jobNumber"/>
                    </a>
                </li>
            </@shiro.user>
            <@shiro.user>
                <li><a href="/user/logout">退出</a></li>
            </@shiro.user>
            </ul>
            <ul class="themeList" id="themeList">
                <li theme="default">
                    <div class="selected">蓝色</div>
                </li>
                <li theme="green">
                    <div>绿色</div>
                </li>
                <li theme="purple">
                    <div>紫色</div>
                <#--缺一张图片-->
                </li>
                <li theme="silver">
                    <div>银色</div>
                </li>
                <li theme="azure">
                    <div>天蓝</div>
                </li>
            </ul>
        </div>

        <div id="navMenu">
            <ul>
            <#if menuBoList?? && (menuBoList?size > 0)>
                <#list menuBoList as menu>
                    <#if menu_index == 0>
                        <li class="selected">
                            <a id="menu_root_${(menu.id)!"-1"}"
                               href="/menu/tree?rootId=${(menu.id)!}"><span>${(menu.name)!}</span></a>
                        </li>
                    <#else>
                        <li>
                            <a id="menu_root_${(menu.id)!"-1"}" class=""
                            <#--onclick="start('${(menu.id)!"-1"}')"-->
                               href="/menu/tree?rootId=${(menu.id)!}"><span>${(menu.name)!}</span></a>
                        </li>
                    </#if>
                <#--<li><a id="menu_root_${(menu.id)!"-1"}" class=""-->
                <#--&lt;#&ndash;onclick="start('${(menu.id)!"-1"}')"&ndash;&gt;-->
                <#--href="/menu/tree?rootId=${(menu.id)}"><span>${(menu.name)!}</span></a>-->
                </#list>
            </#if>
            </ul>
        <#--model，菜单列表从数据库查询，默认select第一个&-->
            <script>
                //function start(id) {
                //    var l = $("#menu_root_" + id).parent();
                //    l.siblings().removeClass('selected select').end().addClass("selected select");
                //    $('#left_nav').load("/menu/tree", {rootId: id}, function () {
                //    });
                //}
                //$(function () {
                //console.log(1);
                //$("#navMenu ul li.selected a span").trigger("click");
                //});
            </script>

        </div>
    </div>

    <div id="leftside">
        <div id="sidebar_s">
            <div class="collapse">
                <div class="toggleCollapse">
                    <div></div>
                </div>
            </div>
        </div>

        <div id="sidebar">
            <div class="toggleCollapse"><h2>主菜单</h2>
                <div>收缩</div>
            </div>
            <div class="accordion" fillSpace="sidebar">
            <#--<div class="accordionHeader">-->
            <#--<h2><span>Folder</span>界面组件</h2>-->
            <#--</div>-->

            <#--<div class="accordionContent" id="left_nav">-->
            <#--&lt;#&ndash;model，默认到主页&ndash;&gt;-->
            <#--</div>-->

                <#--下边可以不出现-->
                <#--<div class="accordionContent">-->
                    <#--<ul class="tree treeFolder">-->
                        <#--<li><a href="tabsPage.html" target="navTab">主框架面板</a>-->
                            <#--<ul>-->
                                <#--<li><a href="main.html" target="navTab" rel="main">我的主页</a></li>-->
                                <#--<li><a href="demo/row-col.html" target="navTab" rel="row-col">栅格系统(Bootstrap)</a></li>-->
                            <#--</ul>-->
                        <#--</li>-->
                    <#--</ul>-->
                <#--</div>-->

            </div>
        </div>

    </div>
    <div id="container">
        <div id="navTab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                    <ul class="navTab-tab">
                        <li tabid="main" class="main"><a href="javascript:;"><span><span
                                class="home_icon">我的主页</span></span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                <div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                <div class="tabsMore">more</div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">我的主页</a></li>
            </ul>
            <div class="navTab-panel tabsPageContent layoutBox">
                <div class="page unitBox">
                    <div class="accountInfo">
                        <p><span>朱李叶(24热线)BOSS管理后台</span></p>
                    </div>
                    <div class="pageFormContent" layoutH="80">
                        <iframe width="100%" height="430" class="share_self" frameborder="0" scrolling="no"
                                src=""></iframe>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>

<div id="footer">Copyright &copy; 2017 <a href="demo_page2.html" target="dialog">ZLY团队</a></div>

</body>
</html>