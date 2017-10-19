;
var url_list = [];
var url_list_delete = [];
var delParent;
var imageNum = 9;
;(function ($) {
    if ($("#maxImageNum").val() != null) {
        imageNum = $("#maxImageNum").val();
    }

    $.fn.extend({
        takungaeImgup: function (opt, serverCallBack) {
            //console.log(1);
            if (typeof opt != "object") {
                alert('参数错误!');
                return;
            }
            //console.log(1);
            var $fileInput = $(this);
            var $fileInputId = $fileInput.attr('id');

            var defaults = {
                fileType: ["jpg", "png", "bmp", "jpeg", "JPG", "PNG", "JPEG", "BMP", "gif"], // 上传文件的类型
                fileSize: 1024 * 1024 * 5, // 上传文件的大小 5M
                count: 0
                // 计数器
            };

            // 组装参数;
            //console.log(2);

            if (opt.success) {
                var successCallBack = opt.success;
                delete opt.success;
            }

            if (opt.error) {
                var errorCallBack = opt.error;
                delete opt.error;
            }

            /* 点击图片的文本框 */
            $(this).change(function () {
                var reader = new FileReader(); // 新建一个FileReader();
                var idFile = $(this).attr("id");
                var file = document.getElementById(idFile);
                var imgContainer = $(this).parents(".z_photo"); // 存放图片的父亲元素
                var fileList = file.files; // 获取的图片文件
                var input = $(this).parent();// 文本框的父亲元素
                var imgArr = [];
                // 遍历得到的图片文件
                var numUp = imgContainer.find(".up-section").length;
                var totalNum = numUp + fileList.length; // 总的数量
                if (fileList.length > imageNum || totalNum > imageNum) {
                    alert("上传图片数目不可以超过9个，请重新选择"); // 一次选择上传超过9个
                    // 或者是已经上传和这次上传的到的总数也不可以超过9个
                } else if (numUp < imageNum) {
                    fileList = validateUp(fileList, defaults);
                    for (var i = 0; i < fileList.length; i++) {
                        var imgUrl = window.URL.createObjectURL(fileList[i]);
                        imgArr.push(imgUrl);
                        var $section = $("<section class='up-section fl loading'>");
                        imgContainer.children(".z_file").before($section);
                        var $span = $("<span class='up-span'>");
                        $span.appendTo($section);
                        var $img0 = $("<img class='close-upimg'>").on("click", function (event) {
                            event.preventDefault();
                            event.stopPropagation();
                            $(".works-mask").show();
                            delParent = $(this).parent();
                        });
                        $img0.attr("src", "/resources/imgUp/img/a7.png").appendTo($section);
                        var $img = $("<img class='up-img up-opcity'>");
                        $img.attr("src", imgArr[i]);
                        $img.appendTo($section);
                        var $p = $("<p class='img-name-p'>");
                        $p.html(fileList[i].name).appendTo($section);
                        var $input = $("<input id='taglocation' name='taglocation' value='' type='hidden'>");
                        $input.appendTo($section);
                        var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
                        $input2.appendTo($section);
                        uploadImg(opt, fileList[i], $section);
                    }
                    ;
                }

                numUp = imgContainer.find(".up-section").length;
                if (numUp >= imageNum) {
                    $(this).parent().hide();
                }
                ;
                //input内容清空
                $(this).val("");
            });

            $(".z_photo").delegate(".close-upimg", "click", function (event) {
                event.preventDefault();
                event.stopPropagation();
                $(".works-mask").show();
                delParent = $(this).parent();
                //console.log(delParent.html()+"delegzat=======");
            });

            $(".wsdel-ok").click(function (event) {
                event.preventDefault();
                event.stopPropagation();
                $(".works-mask").hide();
                var numUp = delParent.siblings(".up-section").length;
                if (numUp < imageNum + 1) {
                    delParent.parent().find(".z_file").show();
                }
                delParent.remove();
                /*增加*/
                var delete_pic = delParent.find("input[name='this_pic']").val();
                //console.log(delete_pic);
                //从url_list删除，添加到 url_list_delete
                //url_list.remove(delete_pic);
                // 判断要删除文件重复 个数，
                var count = url_list.count(delete_pic);
                // 个数大于1，url_list中删除其一个数据，不添加到 url_list_delete
                // 个数等于1，url_list中删除其一个数据，并添加到 url_list_delete
                if (count > 1) {
                    url_list.remove(delete_pic);
                } else if (count == 1) {
                    url_list.remove(delete_pic);
                    if (url_list_delete.indexOf(delete_pic) == -1)
                        url_list_delete.push(delete_pic);
                }
                //if (url_list_delete.indexOf(delete_pic) == -1) // And 不等于特定值
                //    url_list_delete.push(delete_pic);
                console.log("url_list:");
                console.log(url_list);
                console.log("url_list_delete:");
                console.log(url_list_delete);
            });

            $(".wsdel-no").click(function () {
                $(".works-mask").hide();
            });

            // 验证文件的合法性
            function validateUp(files, defaults) {
                var arrFiles = [];// 替换的文件数组
                for (var i = 0, file; file = files[i]; i++) {
                    // 获取文件上传的后缀名
                    var newStr = file.name.split("").reverse().join("");
                    if (newStr.split(".")[0] != null) {
                        var type = newStr.split(".")[0].split("")
                            .reverse().join("");
                        //console.log(type + "===type===");
                        if (jQuery.inArray(type, defaults.fileType) > -1) {
                            // 类型符合，可以上传
                            if (file.size >= defaults.fileSize) {
                                alert('文件大小"' + file.name + '"超出5M限制！');
                            } else {
                                arrFiles.push(file);
                            }
                        } else {
                            alert('您上传的"' + file.name + '"不符合上传类型');
                        }
                    } else {
                        alert('您上传的"' + file.name + '"无法识别类型');
                    }
                }
                return arrFiles;
            }
            ;

            function uploadImg(opt, file, obj) {
                console.log(file);
                $("#imguploadFinish").val(false);
                // 验证通过图片异步上传
                var url = opt.url;
                var data = new FormData();
                data.append("path", opt.formData.path);
                data.append("file", file);
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: data,
                    processData: false,
                    contentType: false,
                    dataType: 'json',
                    success: function (data) {
                        //console.log("chenggong");
                        //console.log(data);
                        //console.log(data.error);
                        // obj.remove();
                        // 上传成功
                        if (data.error == 0) {
                            $(".up-section").removeClass("loading");
                            $(".up-img").removeClass(
                                "up-opcity");
                            $("#imguploadFinish").val(true);
                            var htmlStr = "<input type='text' style='display:none;' name='"
                                + opt.formData.name
                                + "' value='"
                                + data.url
                                + "'>";
                            //console.log(htmlStr);
                            obj.append(htmlStr);
                            if (successCallBack) {
                                successCallBack(data);
                            }
                        }

                        if (data.error == 1) {
                            obj.remove();
                            $("#imguploadFinish").val(false);
                            if (errorCallBack) {
                                errorCallBack(data.url);
                            }
                        }
                    },
                    error: function (e) {
                        obj.remove();
                        var err = "上传失败，请联系管理员！";
                        $("#imguploadFinish").val(false);
                        if (errorCallBack) {
                            errorCallBack(err);
                        }
                    }
                });
            }
        }
    });
})(jQuery);

Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

Array.prototype.count = function (val) {
    var count = 0;
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) count++;
    }
    return count;
};


function initPic(pic, pic_part) {
    // 初始化 url_list
    url_list.push(pic_part);
    //var idFile = $(this).attr("id");
    //var file = document.getElementById(idFile);
    var imgContainer = $(".z_photo"); // 存放图片的父亲元素
    //var input = $(this).parent();// 文本框的父亲元素
    var imgArr = [];
    // fileList = validateUp(fileList, defaults);
    //var imgUrl = window.URL.createObjectURL(fileList[i]);
    //imgArr.push(imgUrl);
    var $section = $("<section class='up-section fl'>");
    imgContainer.children(".z_file").before($section);
    var $span = $("<span class='up-span'>");
    $span.appendTo($section);
    var $img0 = $("<img class='close-upimg'>").on("click", function (event) {
        event.preventDefault();
        event.stopPropagation();
        $(".works-mask").show();
        delParent = $(this).parent();
        console.log(delParent.html() + "delegzat=======");
    });
    $img0.attr("src", "/resources/imgUp/img/a7.png").appendTo($section);
    var $img = $("<img class='up-img'>");
    $img.attr("src", pic);
    $img.appendTo($section);
    var $p = $("<p class='img-name-p'>");
    //$p.html(fileList[i].name).appendTo($section);
    var $input = $("<input id='taglocation' name='taglocation' value='' type='hidden'>");
    $input.appendTo($section);
    var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
    $input2.appendTo($section);
    //uploadImg(opt, fileList[i],$section);

    //$(".up-section").removeClass("loading");
    //$(".up-img").removeClass(
    //    "up-opcity");
    $("#imguploadFinish").val(true);
    var htmlStr = "<input type='text' style='display:none;' name='this_pic' value='"
        + pic_part
        + "'>";
    //console.log(htmlStr);
    $section.append(htmlStr);

    var numUp = imgContainer.find(".up-section").length;
    if (numUp >= imageNum) {
        $("#file").parent().hide();
    }
    ;
    //input内容清空
    //$(this).val("");
}