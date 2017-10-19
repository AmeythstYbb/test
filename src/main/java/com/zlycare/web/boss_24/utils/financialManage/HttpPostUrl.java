//package com.zlycare.web.boss_24.utils.financialManage;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import org.json.JSONObject;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
///**
// * Created by Sulong on 2017/10/11.
// */
//public class HttpPostUrl {
//
//    /**
//     * 向指定URL发送POST请求
//     *
//     * @param url
//     * @param paramMap
//     * @return 响应结果
//     */
//    public static String sendPost(String url, Map<String, String> paramMap) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("charset", "utf-8");
//            conn.setRequestProperty("x-docchat-user-id", "opAdmin");
//            conn.setRequestProperty("x-docchat-session-token", "4j32kljkljfsklj32kl4jklefjkf");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // conn.setRequestProperty("Charset", "UTF-8");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//
//            // 设置请求属性
//            String param = "";
//            if (paramMap != null && paramMap.size() > 0) {
//                Iterator<String> ite = paramMap.keySet().iterator();
//                while (ite.hasNext()) {
//                    String key = ite.next();// key
//                    String value = paramMap.get(key);
//                    param += key + "=" + value + "&";
//                }
//                param = param.substring(0, param.length() - 1);
//            }
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.err.println("发送 POST 请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输出流、输入流
//        finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 数据流post请求
//     *
//     * @param urlStr
//     * @param strInfo
//     */
//    public static String doPost(String urlStr, String strInfo) {
//        String reStr = "";
//        try {
//            URL url = new URL(urlStr);
//            URLConnection con = url.openConnection();
//            con.setDoOutput(true);
//            con.setRequestProperty("Pragma:", "no-cache");
//            con.setRequestProperty("Cache-Control", "no-cache");
//            con.setRequestProperty("Content-Type", "text/xml");
//            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
//            out.write(new String(strInfo.getBytes("utf-8")));
//            out.flush();
//            out.close();
//            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
//            String line = "";
//            for (line = br.readLine(); line != null; line = br.readLine()) {
//                reStr += line;
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return reStr;
//    }
//
//
//    /**
//     * 测试主方法
//     *
//     * @param args
//     */
//
//    public static void main(String[] args) {
//        Map<String, String> mapParam = new HashMap<String, String>();
//        /*mapParam.put("name", "张三");
//        mapParam.put("validation", "test");*/
//
//        // 测试
//        //mapParam.put("account", "805001409");
//
//        //线上
//       // mapParam.put("uuid", "59de1226a1d75cfd7805fc17");
//
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("status",-2);
////        map.put("charset","utf-8");
////        map.put("x-docchat-user-id","opAdmin");
////        map.put("x-docchat-session-token","4j32kljkljfsklj32kl4jklefjkf");
////        new ArrayList<Map>();
////        JSONObject mapp =  new JSONObject(map);
////        mapParam.put("status", "-2");
////        mapParam.put("reason", "999999");
////        mapParam.put("phone", "15809515380");
////        mapParam.put("pics", "ewtyuwetr.png&pics=asd.jpeg");
////        mapParam.put("location", "23.9745345,127.112");
////        mapParam.put("tags", "美食&tags=电影");
////        mapParam.put("topics", "创业&topics=爱上我");
//
//        //String pathUrl = "http://localhost/testPost.action";
//        // 测试
//        //String pathUrl = "http://api.bate.juliye.net/1/open/news";
//        // 线上
//        String pathUrl = "http://api.dc.juliye.net/1/operation/withdrawals/59de1226a1d75cfd7805fc17";
//
//        String result = sendPost(pathUrl,mapParam);
//        System.out.println(result);
//
//    }
//}
