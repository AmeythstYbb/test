package com.zlycare.web.boss_24.utils.financialManage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zlycare.web.boss_24.utils.other.StringUtils;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by Sulong on 2017/10/11.
 */
public class HttpUrlPUT {
    public static String HttpPut(String postUrl, Map<String, String> postHeaders, String postEntity, String reason, String phone) throws Exception {
        if (StringUtils.isEmpty(postUrl)) {
            throw new Exception("httpUrlPUT 参数异常");
        }

        String result = "";
        URL postURL = new URL(postUrl);
//        postUrl = "http://api.dc-test.zlycare.com/1/operation/withdrawals/";
        HttpURLConnection httpURLConnection = (HttpURLConnection) postURL.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");//json格式上传的模式
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
//        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0");//服务器的安全设置不接受Java程序作为客户端访问
        StringBuilder sbStr = new StringBuilder();
        if (postHeaders != null) {
            for (String pKey : postHeaders.keySet()) {
                httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey));
            }
        }
        List<String> postEntityList = Lists.newArrayList("2", "-2", "3", "-3");
        if (postEntity != null) {
            if (!postEntityList.contains(postEntity)) {
                throw new Exception(" postEntity 参数异常");
            }
            JSONObject obj = new JSONObject();
//            String newReason = "";
//            reason = new String(reason.getBytes("UTF-8"),"UTF-8");
            obj.put("status", Integer.parseInt(postEntity));
            obj.put("reason", reason);
            obj.put("phone", phone);
            System.out.println(obj);
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.write(obj.toString().getBytes());
            PrintWriter out = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "utf-8"));
            dos.flush();
            dos.close();

            System.out.println(httpURLConnection.getResponseCode());
            if (httpURLConnection.getResponseCode() == 200) {
                InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String inputLine = null;
                while ((inputLine = br.readLine()) != null) {
                    result += inputLine;
                }
                isr.close();
                httpURLConnection.disconnect();
            }
            System.out.println(result);
        }

        return result;
    }

    /**
     * 接收参数 拼写访问url+参数
     *
     * @param _id
     * @param url
     * @param status
     * @throws Exception
     */
    public static String sendFinancial(String _id, String url, String status, String reason, String phone) throws Exception {
        if (StringUtils.isEmpty(_id) || StringUtils.isEmpty(url) || StringUtils.isEmpty(status)) {
            throw new Exception("sendFinancial 参数异常");
        }
        String uuid = _id;// _id 赋值给uuid
        String putuuid = url;
        String bbb = putuuid + uuid;
        /**
         * 运营页面
         * 同意 ：status传过来的是null 在上边判断postEntity为空 赋值status 为String -2 然后  给-2  ; 还要传reason,phone
         * 拒绝 ：判断postEntity为 2 给 2
         */
        String postEntity = status;
        Map<String, String> postHeaders = new HashMap<String, String>();
        postHeaders.put("Content-Type", "application/json");
        postHeaders.put("charset", "UTF-8");
        postHeaders.put("x-docchat-user-id", "opAdmin");
        postHeaders.put("x-docchat-session-token", "4j32kljkljfsklj32kl4jklefjkf");
        /**
         * /status 赋值给postEntity(只能传 2 or -2)
         * 1.运营页面  status传过来的是null 在上边判断postEntity为空  给-2  ; 判断postEntity为 2 给 2
         * 2.财务页面  读取excel中的值  把  同意/拒绝 字段String 传过来赋值给postEntity
         *             判断postEntiy 不等于空 && postEntiy 为 同意 给3 ;不为空&&postEntiy为 拒绝 给-3
         */
        //System.out.println(bbb);
        String newReason = "";
//        String nnewReason = "";
        if (StringUtils.isNotEmpty(reason)) {
            newReason = new String(reason.getBytes("UTF-8"),"UTF-8");
//            newReason = new String(new String(reason.toString().getBytes(), "utf-8").toString().getBytes("utf-8"));
//            nnewReason = URLEncoder.encode(newReason, "UTF-8");
            System.out.println(newReason);
        }

        String aa = HttpPut(bbb, postHeaders, postEntity, newReason, phone);
        return aa;
    }
////    测试工具类demo
//    public static void main(String[] args) throws  Exception{
//          String aa=sendFinancial("59e5c558fa1e351221702122", "http://api.dc-test.zlycare.com/1/operation/withdrawals/",
//                  "-2","测33试44干22啥111" ,"15809515380");
//        System.out.println(aa);
//    }

}
