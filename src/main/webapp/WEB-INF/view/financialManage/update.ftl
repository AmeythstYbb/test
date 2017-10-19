<div class="pageContent" >
    <form method="post" action="/FinancialManage/update" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone);">


        <div class="pageFormContent" layoutH="56">
            <input type="hidden" name="id" value="${(financialManageBo.id)!}">
            <input type="hidden" name="status" value="${(financialManageBo.status)!}">
            <input type="hidden" name="applicantPhone" value="${(financialManageBo.applicantPhone)!}">


            <dl>
                <dt>备注：</dt>
                    <dd><input type="text" class="required" name="reason" alt="请输入备注（必填）" value="${(financialManageBo.reason)!''}" maxlength="50" size="50"></dd>
            </dl>
        </div>
            <div class="formBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="button" class="close">取消</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">确定</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
    </form>
</div>

