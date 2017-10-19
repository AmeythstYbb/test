<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="/redPaper/update/view" target="navTab" title="红包规则设置"
                   rel="redPaper_manage"><span>修改配置</span></a></li>
        </ul>
    </div>
    <a title="红包规则设置" target="navTab" style="display: none" id="cancelMenu"
       href="/redPaper/get" rel="redPaper_manage"
       class="button"><span>取消</span></a>

    <form method="post" action="/redPaper/update/" class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap fuck_css" layoutH="82">
            <dl>
                <dt>用户每日可抢红包数：</dt>
                <dd>
                    <input type="text" name="timesPerPeoplePerDay" class="required digits" alt="" maxlength="5"
                           size="15" max="99"
                           value="${(redPaper.field.timesPerPeoplePerDay)!"0"}"/>
                </dd>
            </dl>
            <dl>
                <dt>每个动态红包总金额：</dt>
                <dd>
                    <input type="text" name="hongbaoValue" class="required digits" alt="" maxlength="5" size="15"
                           max="99"
                           value="${(redPaper.field.hongbaoValue)!"0"}"/>
                </dd>
            </dl>
            <dl>
                <dt>每个动态红包总个数：</dt>
                <dd>
                    <input type="text" name="hongbaoCount" class="required digits" alt=""
                           maxlength="5" size="15" max="99"
                           value="${(redPaper.field.hongbaoCount)!"0"}"/>
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
                        <#--<button type="button" class="close">取消</button>-->
                            <button type="button" onclick="cancelMenu()">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
<script>
    function cancelMenu() {
        $("#cancelMenu").click();
    }
</script>