package com.zlycare.web.boss_24.beans.constants.qrcode;

/**
 * @author lena
 * @date 2016/10/24
 * @Description collection名称枚举
 */
public enum QRCodeCommonEnum {
    /**
     * 二维码下载
     */
    QRCODE_DOWNLOAD("qrcodeDownload");

    String name;
    QRCodeCommonEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
