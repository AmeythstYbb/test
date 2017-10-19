package com.zlycare.web.boss_24.sms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;

/**
 * Author : linguodong
 * Create : 2017/6/21
 * Update : 2017/6/21
 * Descriptions :
 */
public class SmsService extends JavaSmsApi {
    private static final Integer pass = 1779106;
    private static final Integer refuse = 1779110;
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    /**
     * 封装审核成功的短信
     *
     * @param phone phone
     * @return boolean
     */
    public static boolean sendShopApplicationsAuditPass(String phone) {
        if (StringUtils.isEmpty(phone)) {
            logger.error("审核通过短信发送失败");
            return false;
        }
        try {
            String result = JavaSmsApi.tplSendSms(JavaSmsApi.API_KEY, pass, "", phone);
            JSONObject json = JSON.parseObject(result);
            System.out.println(json.get("http_status_code"));//调用结束后的http_status_code值
            System.out.println();//错误码
            System.out.println(json.get("msg"));//错误描述
            System.out.println(json.get("detail"));//具体错误描述或解决方法
            // ----------------------------
            System.out.println(json.get("count"));
            System.out.println(json.get("fee"));
            System.out.println(json.get("unit"));
            System.out.println(json.get("mobile"));
            System.out.println(json.get("sid"));
            // ----------------------------
            // 可能异常状况--后续补全并输出或者返回
            Integer code = Integer.parseInt(json.get("code").toString());
            if (code == 8) {//同一手机号30秒内重复提交相同的内容
                logger.error(json.get("detail").toString());
            }
            // ----------------------------
            System.out.println(result);
        } catch (Exception e) {
            logger.error("审核通过短信发送失败，手机号是" + phone + "error:" + e);
            // e.printStackTrace();
        }
        return true;
    }

    /**
     * 封装审核失败的短信
     *
     * @param phone phone
     * @return boolean
     */
    public static boolean sendShopApplicationsAuditRefuse(String phone, String reason) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(reason)) {
            logger.error("审核通过短信发送失败");
            return false;
        }
        try {
            String tpl_value = URLEncoder.encode("#reason#", ENCODING) + "="
                    + URLEncoder.encode(reason, ENCODING);
            //SmsResult smsResult = JSON.parseObject(JavaSmsApi.tplSendSms(JavaSmsApi.API_KEY, refuse, tpl_value, phone), SmsResult.class);
            //System.out.println(smsResult.getDetail());
            String result = JavaSmsApi.tplSendSms(JavaSmsApi.API_KEY, refuse, tpl_value, phone);
            JSONObject json = JSON.parseObject(result);
            System.out.println(json.get("http_status_code"));//调用结束后的http_status_code值
            System.out.println();//错误码
            System.out.println(json.get("msg"));//错误描述
            System.out.println(json.get("detail"));//具体错误描述或解决方法
            // ----------------------------
            System.out.println(json.get("count"));
            System.out.println(json.get("fee"));
            System.out.println(json.get("unit"));
            System.out.println(json.get("mobile"));
            System.out.println(json.get("sid"));
            // ----------------------------
            // 可能异常状况--后续补全并输出或者返回
            Integer code = Integer.parseInt(json.get("code").toString());
            if (code == 8) {//同一手机号30秒内重复提交相同的内容
                logger.error(json.get("detail").toString());
            }
            // ----------------------------
            System.out.println(result);
        } catch (Exception u) {
            logger.error("审核通过短信发送失败，手机号是" + phone);
            // e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        //sendShopApplicationsAuditPass("15210336463");
        //sendShopApplicationsAuditRefuse("15210336463", "测试一下");
    }

    class SmsResult {
        /**
         * http 状态
         */
        private Integer http_status_code;
        /**
         * code
         */
        private Integer code;
        /**
         * msg
         */
        private String msg;
        /**
         * detail
         */
        private String detail;

        /**
         * count
         */
        private Integer count;
        /**
         * unit
         */
        private String unit;
        /**
         * mobile
         */
        private String mobile;
        /**
         * sid
         */
        private Integer sid;

        /**
         * fee
         */
        private Double fee;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Integer getSid() {
            return sid;
        }

        public void setSid(Integer sid) {
            this.sid = sid;
        }

        public Double getFee() {
            return fee;
        }

        public void setFee(Double fee) {
            this.fee = fee;
        }

        public Integer getHttp_status_code() {
            return http_status_code;
        }

        public void setHttp_status_code(Integer http_status_code) {
            this.http_status_code = http_status_code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
