OperationController.prototype.handleShopApply = function (req, res) {
    var payload = req.body;
    var fields = {
        required: ['id','status'],
        optional: ['reason']
    };

    var onFailure = function (handler, type) {
        handler(res, type);
    };
    var onSuccess = function (handler, data) {
        var appl;
        ApplicationService.updateStatus(data.id, data.status , data.reason)
            .then(function (_appl) {
                if (!_appl) {
                    throw ErrorHandler.getBusinessErrorByCode(8006);
                }
                appl = _appl;
                return CustomerService.getInfoByID(appl.applicantId)
            })
            .then(function(_user){
                var updateData = {};
                if (data.status < 0) {//拒绝
                    //data.phone && commonUtil.sendSms('1587284', data.phone, '#content#=' + (data.reason || '') + '&#url#=' + constants.customerPublicDownloadURL);
                    if(_user.shopVenderApplyStatus == 1){
                        updateData.shopVenderApplyStatus = 2
                    }else if(_user.shopVenderApplyStatus == 4){
                        updateData.shopVenderApplyStatus = 5
                    }else{
                        throw ErrorHandler.getBusinessErrorByCode(8006);
                    }
                } else if (data.status > 0) {//审核通过
                    updateData = {
                        "shopCity" : appl.shopCity,//店铺城市
                        "shopName" : appl.shopName,//店铺名称
                        "shopAddress" : appl.shopAddress,//店铺地址
                        "shopType" : appl.shopType,//店铺类型
                        "shopPhoneNum" : appl.shopPhoneNum,//店铺电话号码
                        "shopAvatar" : appl.shopAvatar,//商家头像
                        "shopLicense" : appl.shopLicense, //商家营业执照
                        "shopSubType" : appl.shopSubType, //商家子类型
                        shopVenderApplyStatus : 3,
                        isVender : true
                    }
                    if(appl.shopAddressMapLon && appl.shopAddressMapLat){
                        updateData.shopAddressMapLon = appl.shopAddressMapLon;
                        updateData.shopAddressMapLat = appl.shopAddressMapLat;
                        updateData.shopLocation = [ appl.shopAddressMapLon, appl.shopAddressMapLat]
                    }
                }
                console.log(updateData)
                return CustomerService.updateUserById(appl.applicantId,updateData)
            })
            .then(function (_user) {
                if (data.status > 0) {
                    var updateOpShop = {
                        "shopCity": _user.shopCity,//店铺城市
                        "shopName": _user.shopName,//店铺名称
                        "shopType": _user.shopType,//店铺类型
                        "shopSubType": _user.shopSubType //商家子类型
                    }
                    return ShopService.updateShopByUserId(_user._id, updateOpShop)
                }
            })
            .then(function(){
                apiHandler.OK(res, {});
                if(data.status > 0){
                    commonUtil.sendSms("1779106", appl.applicantPhone);
                }else if(data.status < 0){
                    commonUtil.sendSms("1779110", appl.applicantPhone, "#reason#=" + data.reason);
                }
            }, function (err) {
                return apiHandler.SYS_DB_ERROR(res, err);
            });
    };

    commonUtil.validate(payload, fields, onSuccess, onFailure);
};