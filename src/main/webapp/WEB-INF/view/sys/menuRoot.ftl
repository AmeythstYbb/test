<ul>
<#if menuBoList?? && (menuBoList?size > 0)>
    <#list menuBoList as menu>
        <#if menu_index == 0>
            <li class="selected"><a id="menu_root_${(menu.id)!"-1"}"
                                    href="/menu/tree?rootId=${(menu.id)}"><span>${(menu.name)!}</span></a></li>
        <#else >
            <li><a id="menu_root_${(menu.id)!"-1"}"
                   href="/menu/tree?rootId=${(menu.id)}"><span>${(menu.name)!}</span></a></li>
        </#if>
    </#list>
</#if>
</ul>
