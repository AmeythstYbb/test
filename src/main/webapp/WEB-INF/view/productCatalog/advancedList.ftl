<link href="/resources/treeTable/themes/vsStyle/treeTable.css" rel="stylesheet" type="text/css"/>
<script src="/resources/treeTable/jquery.treeTable.js"></script>

<form id="pagerForm" method="post" action="demo_page1.html">
</form>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">

        <@shiro.hasPermission name="product:catalog:advanced:all">
            <li><a class="add" href="/product/catalog/advanced/insert/view" target="navTab"
                   rel="updateProductCatalog"><span>添加目录</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="product:catalog:advanced:all">
            <li><a class="delete" href="/product/catalog/advanced/delete?id={sid_user}" target="ajaxTodo"
                   title="要删除该目录及所有子目录项吗?"><span>删除目录</span></a></li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="product:catalog:advanced:all">
            <li><a class="edit" href="/product/catalog/advanced/update/view?id={sid_user}" rel="updateProductCatalog"
                   target="navTab"><span>修改目录</span></a></li>
        </@shiro.hasPermission>
        </ul>
    </div>

    <table id="treeAdvancedTable" class="list" width="100%" layoutH="40">
        <thead>
        <tr>
            <th width="60">名称</th>
            <th width="80">排序</th>
            <th width="80">可见</th>
        </tr>
        </thead>

    <#if productCatalogAdvancedList?? && (productCatalogAdvancedList?size > 0)>
        <#list productCatalogAdvancedList as productCatalogAdvanced>
            <#if productCatalogAdvanced.parentId??><#--不用去除ID为1的顶级目录，在controller中已经去掉-->
                <tr id="${(productCatalogAdvanced.id)!}"
                    pId="${(productCatalogAdvanced.parentId != "1")?string(productCatalogAdvanced.parentId,'0')}"
                    target="sid_user"
                    rel="${(productCatalogAdvanced.id)!}">
                    <td><a rel="updateProductCatalog" title="修改目录" target="navTab"
                           href="/product/catalog/advanced/update/view?id=${(productCatalogAdvanced.id)!}">${(productCatalogAdvanced.name)!}</a>
                    </td>
                    <td>${(productCatalogAdvanced.sort)!}</td>
                    <td>
                        <#if productCatalogAdvanced.show ==1 >
                            显示
                        <#else>
                            隐藏
                        </#if>
                    </td>
                </tr>
            </#if>
        </#list>
    </#if>
    </table>
</div>
<script>
    $(document).ready(function () {
        $("#treeAdvancedTable").treeTable({expandLevel: 5});
    });
</script>
