
<div class="pageContent">
    <form id="form_upload_excel" method="post" action="/FinancialReview/financialReview/upload" enctype="multipart/form-data"
          class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <p>
                <label>上传EXCEL：</label>
                <#--<input name="excel" type="file" id="excel" onchange="checkFileExt(this.value)"/>-->
                <input name="excel" type="file" id="excel" />
                        <#--<button id="upload" type="button" onclick="uploadExcel()">上传</button>-->
                <input type="submit" value="上传">
            </p >
        </div>

    </form>


</div>
