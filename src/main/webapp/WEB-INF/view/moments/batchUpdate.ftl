<h2 class="contentTitle">修改动态</h2>

<div class="pageContent">
    <form method="post" action="/moments/batch/upload/update/" class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDoneSB)" enctype="multipart/form-data">

        <input type="hidden" value="${(momentsTemporaryBasicBo.id)!}" name="id">
        <input type="hidden" value="${(momentsTemporaryBasicBo.batch)!}" name="batch">
        <input type="hidden" value="${(momentsTemporaryBasicBo.uploadFlag)!}" name="uploadFlag">
        <input type="hidden" value="${(momentsTemporaryBasicBo.excelId)!}" name="excelId">

        <div class="pageFormContent nowrap" layoutH="97">
        <#--805开头9位数字，必填-->
            <dl>
                <dt>热线号：</dt>
                <dd>
                    <input id="docChatNum" type="text" name="docChatNum" class="required number docChatNum_validate"
                           alt=""
                           maxlength="9" size="50" minlength="9"
                           value="${(momentsTemporaryBasicBo.docChatNum)!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>

        <#--小于800字，必填-->
            <dl>
                <dt>动态内容：</dt>
                <dd><textarea name="content" class="required" alt="" maxlength="799" rows="7"
                              style="width: 306px">${(momentsTemporaryBasicBo.content)!}</textarea>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>

        <#--图片命名由英文字母、数字、下划线、":"、"/"、"."组成，图片间用英文逗号隔开，非必填，若带有http://或https://则可以有"/"，否则只能有英文字母、数字、下划线、"."-->
            <dl>
                <dt>图片：</dt>
                <dd><textarea name="pics" class="pics_validate" alt="" maxlength="1000" rows="8"
                              style="width: 306px">${(momentsTemporaryBasicBo.picsValue)!}</textarea>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>

        <#--话题间用逗号隔开，不超过50字，非必填-->
            <dl>
                <dt>话题：</dt>
                <dd>
                    <input type="text" name="topics" class="" alt="" maxlength="50" size="50"
                           value="${(momentsTemporaryBasicBo.topicsValue)!""}"/>
                    <span class="info">话题间用英文逗号隔开</span>
                </dd>
            </dl>

        <#--标签间用逗号隔开，不超过50字，非必填-->
            <dl>
                <dt>标签：</dt>
                <dd>
                    <input type="text" name="tags" class="" alt="" maxlength="50" size="50"
                           value="${(momentsTemporaryBasicBo.tagsValue)!""}"/>
                    <span class="info">标签间用英文逗号隔开</span>
                </dd>
            </dl>

        <#---180～180，必填浮点型-->
            <dl>
                <dt>经度：</dt>
                <dd>
                    <input id="lon" type="text" name="lon" class="required number" alt=""
                           maxlength="20" size="50"
                           min="-180" max="180"
                           value="${(momentsTemporaryBasicBo.lon?string("0.###########"))!""}"/>
                <#--<span class="info">class="required email"</span>-->
                </dd>
            </dl>

        <#---90～90，必填浮点型-->
            <dl>
                <dt>纬度：</dt>
                <dd>
                    <input id="lat" type="text" name="lat" class="required number" alt=""
                           maxlength="20" size="50"
                           min="-90" max="90"
                           value="${(momentsTemporaryBasicBo.lat?string("0.###########"))!""}"/>
                <#--<span class="info">class="required email"</span>-->
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
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
