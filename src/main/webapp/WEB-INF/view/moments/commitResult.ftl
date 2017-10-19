<#if flag?? && flag == "1">
<div style="text-align: center;padding-top:60px">
    <span style="font-size:30px">数据上传成功</span>
</div>
<#elseif flag?? && flag == "0">
<div style="text-align: center;padding-top:60px">
    <span style="font-size:30px">数据上传失败</span><br>
    <span style="color: blue">成功:${successCount!}条,失败:${filedCount!}条,未上传:${notCount!}条</span><br>
    <#if failedList??>
        失败数据序号：<span>
        <#list failedList as fail>
        ${(fail.excelId)!}、
        </#list></span>
    </#if>
</div>
<#else>
<div style="text-align: center;padding-top:60px">
    <span style="font-size:30px">操作异常</span>
</div>
</#if>
<br>

