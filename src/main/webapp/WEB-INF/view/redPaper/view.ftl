<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="/redPaper/update/view" target="navTab" title="红包规则设置"
                   rel="redPaper_manage"><span>修改配置</span></a></li>
        </ul>
    </div>
    <div class="pageFormContent nowrap fuck_css" layoutH="82">
        <dl>
            <dt>用户每日可抢红包数：</dt>
            <dd>
            <#if redPaper?? && redPaper.field??>
                 ${(redPaper.field.timesPerPeoplePerDay)!"0"}
            </#if>
            </dd>
        </dl>
        <dl>
            <dt>每个动态红包总金额：</dt>
            <dd>
            <#if redPaper?? && redPaper.field??>
                 ${(redPaper.field.hongbaoValue)!"0"}
            </#if>
            </dd>
        </dl>
        <dl>
            <dt>每个动态红包总个数：</dt>
            <dd>
            <#if redPaper?? && redPaper.field??>
                 ${(redPaper.field.hongbaoCount)!"0"}
            </#if>
            </dd>
        </dl>
    </div>
</div>