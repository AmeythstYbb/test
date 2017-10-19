<div class="pageContent">
    <form method="post" action="/member/product/audit" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <input type="hidden" name="id" value="${(id)!}">
            <input type="hidden" name="status" value="-1">
            <dl class="nowrap">
                <#--<dt>必填：</dt>-->
                <dd><textarea name="reason" class="required" cols="59" rows="6" style="width: auto" maxlength="200"></textarea></dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">保存</button>
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
