package com.zlycare.web.boss_24.beans.constants.qrcode;

/**
 * @author lena
 * @date 2016/12/8
 * @Description 渠道枚举，参照mongo里appChannel表
 */
public enum ChannelEnum {
    YINGYONGBAO("10001", "应用宝"),
    OFFICIAL("10029", "官网");

    ChannelEnum(String channelNo, String channel) {
        this.channelNo = channelNo;
        this.channel = channel;
    }

    /**
     * 渠道ID
     */
    private String channelNo;
    /**
     * 渠道名
     */
    private String channel;

    public String getChannelNo() {
        return channelNo;
    }

    public String getChannel() {
        return channel;
    }

}
