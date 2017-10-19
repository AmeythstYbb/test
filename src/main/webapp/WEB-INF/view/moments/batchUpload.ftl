<div class="pageContent">
    <form id="form_upload_excel" method="post" action="/moments/batch/upload" enctype="multipart/form-data"
          class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);">
        <div class="pageFormContent" layoutH="515">
            <p>
                <label>上传EXCEL：</label>
                <input name="excel" type="file" id="excel" onchange="checkFileExt(this.value)"/>
                <button id="upload" type="button" onclick="uploadExcel()">上传</button>
            </p>
        </div>
    <#--<div class="formBar">-->
    <#--<ul>-->
    <#--<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>-->
    <#--<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>-->
    <#--</ul>-->
    <#--</div>-->
    </form>

    <div class="panelBar">
        <ul class="toolBar">
        <#--<li><a class="icon"-->
        <#--title="实要导出这些记录吗?"><span>导入EXCEL</span></a></li>-->
        <#--判断是否有错误字段-->
        <#if commitFlag?? && (commitFlag == 1) && batch??>
        <#--<li><a class="icon" href="/moments/batch/upload/commit?batch=${batch}" width="475" height="305"-->
        <#--fresh="false"-->
        <#--title="确认上传" target="dialog"><span>确认上传</span></a></li>-->
        <#--rel="moments_batch_upload"-->
        <#--target="navTab"-->
            <li><a class="add" href="/moments/batch/upload/commit?batch=${(batch)!}" target="dialog"
                   rel="commit_result" mask="true" minable="false" maxable="false" resizable="false" drawable="false"
                   close="closeDialog" param="{batch:${(batch)!}}"
                   title="上传结果"><span>确认上传</span></a><br/><br/>
            </li>
        <#else>
            <li><a class="add" href="#"><span>等待上传</span></a></li>
        </#if>
        </ul>
    </div>

    <table class="table" width="100%" style="table-layout:fixed;word-break:break-all;" layoutH="138">
        <thead>
        <tr>
            <th width="40">序号</th>
            <th width="70">热线号</th>
            <th width="300">动态内容</th>
            <th width="240">图片</th>
            <th width="140">话题</th>
            <th width="120">标签</th>
            <th width="90">经度</th>
            <th width="90">纬度</th>
            <th width="50">操作</th>
        </tr>
        </thead>
    <#if list?? && (list?size > 0)>
        <#list list as moments_temporary>
            <tr id="${(moments_temporary.id)!}" target="sid_user"
                rel="${(moments_temporary.id)!}">
                <td>${(moments_temporary.excelId)!}</td>
            <#--热线号-->
                <#if moments_temporary.docChatNumValidate??><#--红色背景,白色字体,title显示异常信息-->
                    <td style="background-color: #FF0050;color: #FFFFFF"
                        title="${(moments_temporary.docChatNumValidate)!}">${(moments_temporary.docChatNum)!}</td>
                <#else> <#--正常显示-->
                    <td>${(moments_temporary.docChatNum)!}</td>
                </#if>
            <#--动态内容-->
                <#if moments_temporary.contentValidate??><#--红色背景,白色字体,title显示异常信息-->
                    <td style="background-color: #FF0050;color: #FFFFFF"
                        title="${(moments_temporary.contentValidate)!}">${(moments_temporary.content)!}</td>
                <#else> <#--正常显示-->
                    <td>${(moments_temporary.content)!}</td>
                </#if>
            <#--图片-->
                <#if moments_temporary.picsValidate??><#--红色背景,白色字体,title显示异常信息-->
                    <td style="background-color: #FF0050;color: #FFFFFF"
                        title="${(moments_temporary.picsValidate)!}">${(moments_temporary.picsValue)!}</td>
                <#else> <#--正常显示-->
                    <td>${(moments_temporary.picsValue)!}</td>
                </#if>
            <#--话题-->
                <#if moments_temporary.topicsValidate??><#--红色背景,白色字体,title显示异常信息-->
                    <td style="background-color: #FF0050;color: #FFFFFF"
                        title="${(moments_temporary.topicsValidate)!}">${(moments_temporary.topicsValue)!}</td>
                <#else> <#--正常显示-->
                    <td>${(moments_temporary.topicsValue)!}</td>
                </#if>
            <#--标签-->
                <#if moments_temporary.tagsValidate??><#--红色背景,白色字体,title显示异常信息-->
                    <td style="background-color: #FF0050;color: #FFFFFF"
                        title="${(moments_temporary.tagsValidate)!}">${(moments_temporary.tagsValue)!}</td>
                <#else> <#--正常显示-->
                    <td>${(moments_temporary.tagsValue)!}</td>
                </#if>
            <#--经度-->
                <#if moments_temporary.lonValidate??><#--红色背景,白色字体,title显示异常信息-->
                    <td style="background-color: #FF0050;color: #FFFFFF"
                        title="${(moments_temporary.lonValidate)!}">${(moments_temporary.lon?string("0.###########"))!}</td>
                <#else> <#--正常显示-->
                    <td>${(moments_temporary.lon?string("0.###########"))!}</td>
                </#if>
            <#--纬度-->
                <#if moments_temporary.latValidate??><#--红色背景,白色字体,title显示异常信息-->
                    <td style="background-color: #FF0050;color: #FFFFFF"
                        title="${(moments_temporary.latValidate)!}">${(moments_temporary.lat?string("0.###########"))!}</td>
                <#else> <#--正常显示-->
                    <td>${(moments_temporary.lat?string("0.###########"))!}</td>
                </#if>
            <#--修改操作-->
                <td>
                    <#if commitFlag?? && batch??> <#--存在批次ID和上传标识,说明是从数据库返回的列表-->
                        <@shiro.hasPermission name="moments:batch:upload">
                            <a class="button" href="/moments/batch/upload/update/view?id=${(moments_temporary.id)!}"
                               rel="updateMomentsBatchUpload" title="修改动态"
                               target="navTab"><span>修改</span></a>
                        </@shiro.hasPermission>
                    <#else>
                        无
                    </#if>
                </td>
            </tr>
        </#list>
    </#if>
    </table>
</div>


<script>
//    $(function () {
//        //var file = $("#excel");
//        //file.after(file.clone().val(""));
//        //file.remove();
//        var sb = $("#sb");
//        if (sb != "" && sb != "undefined" && sb != null) {
//            alert("格式错误");
//        }
//    });


    /*关闭弹窗时刷新列表(展示失败列表) or 直接回到上传界面*/
    function closeDialog(param) {
        //return true;
        //$.pdialog.closeCurrent();//关闭当前活动层。
        navTab.reload("/moments/batch/upload/view?flag=1&batch=" + param.batch, {}, "moments_batch_upload");
        return true;
    }

    /*选择文件时校验*/
    function checkFileExt(filename) {
        var flag = false; //状态
        var arr = ["xls", "xlsx"];
        //取出上传文件的扩展名
        var index = filename.lastIndexOf(".");
        var ext = filename.substr(index + 1);
        //循环比较
        for (var i = 0; i < arr.length; i++) {
            if (ext == arr[i]) {
                flag = true; //一旦找到合适的，立即退出循环
                break;
            }
        }
        //条件判断
        if (flag) {
            //document.write("文件名合法");
        } else {
            //document.write("文件名不合法");
            alert("文件格式错误,请选择EXCEL文件");
            //$("#excel").val("");// 清空值
            var file = $("#excel");
            file.after(file.clone().val(""));
            file.remove();
            return false;
        }
    }

    /*点击上传时校验*/
    function uploadExcel() {
        // 改为 button 成功 submit 失败 alert
        var file = $("#excel").val();
        if (file == "" || file == "undefined" || file == null) {
            alert("请选择EXCEL文件");
            return false;
        } else {
            // alert("文件存在");
            // 发起表单提交,无法获取新页面,改为ajax
            $('#form_upload_excel').submit()

//            var form = $("#form_upload_excel");
//            var formData = new FormData($("#excel").prop("files")[0]);
//            $.ajax({
//                type: form.attr('method'),
//                url: form.attr('action'),
////                data: {"excel": formData},
////                data: formData,
//                data: form.serialize(),
//                dataType: "JSON",
//                enctype:"multipart/form-data",
//                //mimeType: "multipart/form-data",
//                contentType: false,
//                cache: false,
//                processData: false,
//                error: function (XHR, textStatus, errorThrown) {
//                    console.log(errorThrown);
////                    alert("网络错误！XHR=" + XHR + "\ntextStatus=" + textStatus
////                            + "\nerrorThrown=" + errorThrown);
//                },
//                success: function (data) {
//                    console.log(data);
////                    alert(data[0].message);
//                }
//            });
        }
    }
</script>

<#--<div class="pageContent">-->
<#--<p style="margin: 10px">-->
<#--<label>上传EXCEL：</label>-->
<#--<input type="file" name="excel"/>-->
<#--<button id="upload">上传</button>-->
<#--</p>-->
<#--<div class="panelBar">-->
<#--<ul class="toolBar">-->
<#--<li><a class="icon"-->
<#--title="实要导出这些记录吗?"><span>导入EXCEL</span></a></li>-->
<#--</ul>-->
<#--</div>-->

<#--<table class="table" width="100%" layoutH="138">-->
<#--<thead>-->
<#--<tr>-->
<#--<th width="80"></th>-->
<#--<th width="120">客户号</th>-->
<#--<th>客户名称</th>-->
<#--<th width="100">客户类型</th>-->
<#--<th width="150">证件号码</th>-->
<#--<th width="80" align="center">信用等级</th>-->
<#--<th width="80">所属行业</th>-->
<#--<th width="80">建档日期</th>-->
<#--</tr>-->
<#--</thead>-->
<#--<tbody>-->
<#--<tr target="sid_user" rel="1">-->
<#--<td>天津农信社</td>-->
<#--<td>A120113196309052434</td>-->
<#--<td>天津市华建装饰工程有限公司</td>-->
<#--<td>联社营业部</td>-->
<#--<td>29385739203816293</td>-->
<#--<td>5级</td>-->
<#--<td>政府机构</td>-->
<#--<td>2009-05-21</td>-->
<#--</tr>-->
<#--<tr target="sid_user" rel="10">-->
<#--<td>天津农信社</td>-->
<#--<td>A120113196309052434</td>-->
<#--<td>天津市华建装饰工程有限公司</td>-->
<#--<td>联社营业部</td>-->
<#--<td>29385739203816293</td>-->
<#--<td>5级</td>-->
<#--<td>政府机构</td>-->
<#--<td>2009-05-21</td>-->
<#--</tr>-->
<#--</tbody>-->
<#--</table>-->
<#--</div>-->

<#--表单上传过程中，整个页面就刷新了！ajax异步上传就可以达到只刷新局部位置-->
<#--<script>-->
<#--$(function () {-->
<#--$("#upload").click(function () {-->
<#--$("#imgWait").show();-->
<#--var formData = new FormData();-->
<#--formData.append("myfile", document.getElementById("file1").files[0]);-->
<#--$.ajax({-->
<#--url: "upload.ashx",-->
<#--type: "POST",-->
<#--data: formData,-->
<#--/**-->
<#--*必须false才会自动加上正确的Content-Type-->
<#--*/-->
<#--contentType: false,-->
<#--/**-->
<#--* 必须false才会避开jQuery对 formdata 的默认处理-->
<#--* XMLHttpRequest会对 formdata 进行正确的处理-->
<#--*/-->
<#--processData: false,-->
<#--success: function (data) {-->
<#--if (data.status == "true") {-->
<#--alert("上传成功！");-->
<#--}-->
<#--if (data.status == "error") {-->
<#--alert(data.msg);-->
<#--}-->
<#--$("#imgWait").hide();-->
<#--},-->
<#--error: function () {-->
<#--alert("上传失败！");-->
<#--$("#imgWait").hide();-->
<#--}-->
<#--});-->
<#--});-->
<#--});-->
<#--</script>-->