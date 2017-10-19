<#--<div style="width: 100% !important; height:100% !important;;">-->
<#if phone?? && phone !="" && href?? && href != "">
<#--${(phone)!}-${(href)!}-${(md5)!}-->
<iframe src="${(href)!}?phone=${(phone)!}&auth=${(auth)!}" width="100%" height="100%" style="width: 100% !important;"
        frameborder="0"
<#--<iframe src="http://10.170.240.206:8080/user/login" width="100%" height="100%" style="width: 100% !important;" frameborder="0"-->
<#--<iframe src="http://localhost:8080" width="100%" height="100%" style="width: 100% !important;" frameborder="0"-->
        border="0" marginwidth="0"
        marginheight="0" layoutH="0"></iframe>
<#else>
请先在个人信息中将手机号填写正确！
</#if>
<#--</div>-->