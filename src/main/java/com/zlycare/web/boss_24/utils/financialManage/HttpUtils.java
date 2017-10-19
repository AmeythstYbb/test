//package com.zlycare.web.boss_24.utils.financialManage;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import org.json.JSONObject;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.ArrayList;
//
//
//
///**
// * Created by Sulong on 2017/10/11.
// */
//public class HttpUtils {
//    /**
//     * 发送GET请求
//     * @param url 目的地址
//     * @param parameters 请求参数，Map类型
//     * @return 远程响应结果
//     */
//    public static String sendGet(String url, Map<String, String> parameters) {
//        String result="";
//        BufferedReader in = null;// 读取响应输入流
//        StringBuffer sb = new StringBuffer();// 存储参数
//        String params = "";// 编码之后的参数
//        try {
//            // 编码请求参数
//            if(parameters.size()==1){
//                for(String name:parameters.keySet()){
//                    sb.append(name).append("=").append(
//                            java.net.URLEncoder.encode(parameters.get(name),
//                                    "UTF-8"));
//                }
//                params=sb.toString();
//            }else{
//                for (String name : parameters.keySet()) {
//                    sb.append(name).append("=").append(
//                            java.net.URLEncoder.encode(parameters.get(name),
//                                    "UTF-8")).append("&");
//                }
//                String temp_params = sb.toString();
//                params = temp_params.substring(0, temp_params.length() - 1);
//            }
//            String full_url = url + "?" + params;
//            System.out.println(full_url);
//            // 创建URL对象
//            java.net.URL connURL = new java.net.URL(full_url);
//            // 打开URL连接
//            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
//                    .openConnection();
//            // 设置通用属性
//            httpConn.setRequestProperty("Accept", "*/*");
//            httpConn.setRequestProperty("Connection", "Keep-Alive");
//            httpConn.setRequestProperty("User-Agent",
//                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
//            // 建立实际的连接
//            httpConn.connect();
//            // 响应头部获取
//            Map<String, List<String>> headers = httpConn.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : headers.keySet()) {
//                System.out.println(key + "\t：\t" + headers.get(key));
//            }
//            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
//            in = new BufferedReader(new InputStreamReader(httpConn
//                    .getInputStream(), "UTF-8"));
//            String line;
//            // 读取返回的内容
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result ;
//    }
//
//    /**
//     * 发送POST请求
//     *
//     * @param url
//     *            目的地址
//     * @param parameters
//     *            请求参数，Map类型。
//     * @return 远程响应结果
//     */
//    public static String sendPost(String url, Map<String, String> parameters) {
//        String result = "";// 返回的结果
//        BufferedReader in = null;// 读取响应输入流
//        PrintWriter out = null;
//        StringBuffer sb = new StringBuffer();// 处理请求参数
//        String params = "";// 编码之后的参数
//        try {
//            // 编码请求参数
//            if (parameters.size() == 1) {
//                for (String name : parameters.keySet()) {
//                    sb.append(name).append("=").append(
//                            java.net.URLEncoder.encode(parameters.get(name),
//                                    "UTF-8"));
//                }
//                params = sb.toString();
//            } else {
//                for (String name : parameters.keySet()) {
//                    sb.append(name).append("=").append(
//                            java.net.URLEncoder.encode(parameters.get(name),
//                                    "UTF-8")).append("&");
//                }
//                String temp_params = sb.toString();
//                params = temp_params.substring(0, temp_params.length() - 1);
//            }
//            // 创建URL对象
//            java.net.URL connURL = new java.net.URL(url);
//            // 打开URL连接
//            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
//                    .openConnection();
//            // 设置通用属性
//            httpConn.setRequestProperty("Content-Type", "application/json");
//            httpConn.setRequestProperty("charset", "utf-8");
//            httpConn.setRequestProperty("x-docchat-user-id","opAdmin");
//            httpConn.setRequestProperty("x-docchat-session-token","4j32kljkljfsklj32kl4jklefjkf");
//
//            // 设置POST方式
//            httpConn.setDoInput(true);
//            httpConn.setDoOutput(true);
//            // 获取HttpURLConnection对象对应的输出流
//            out = new PrintWriter(httpConn.getOutputStream());
//            // 发送请求参数
//            out.write(params);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
//            in = new BufferedReader(new InputStreamReader(httpConn
//                    .getInputStream(), "UTF-8"));
//            String line;
//            // 读取返回的内容
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
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
//     *
//     * @param postUrl
//     * @param postEntity
//     * @return
//     * @throws Exception
//     */
//    public static String HttpPut(String postUrl,Map<String, String> postEntity) throws Exception {
//        URL postURL = new URL(postUrl);
//        HttpURLConnection httpURLConnection = (HttpURLConnection) postURL.openConnection();
//        httpURLConnection.setDoOutput(true);
//        httpURLConnection.setDoInput(true);
//        httpURLConnection.setRequestMethod("PUT");
//        httpURLConnection.setUseCaches(false);
//        httpURLConnection.setInstanceFollowRedirects(true);
//        //application/json x-www-form-urlencoded
//        //httpURLConnection.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");//表单上传的模式
//        httpURLConnection.setRequestProperty("Content-Type",  "application/json;charset=utf-8");//json格式上传的模式
//        StringBuilder sbStr = new StringBuilder();
////        if(postHeaders != null) {
////            for(String pKey : postHeaders.keySet()) {
////                httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey));
////            }
////        }
//        if(postEntity != null) {
////            JSONObject obj = new JSONObject();
//            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("Content-Type","application/json");
//            map.put("charset","utf-8");
//            map.put("x-docchat-user-id","opAdmin");
//            map.put("x-docchat-session-token","4j32kljkljfsklj32kl4jklefjkf");
//            new ArrayList<Map>();
//            JSONObject obj = new JSONObject(map);
////            obj.put("plate","京NB1314");
//            PrintWriter out = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(),"utf-8"));
//            out.println(obj);
//            out.close();
//            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection
//                    .getInputStream()));
//
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                sbStr.append(inputLine);
//            }
//            in.close();
//        }
//        httpURLConnection.disconnect();
//        return new String(sbStr.toString().getBytes(),"utf-8");
//    }
//
//    /**
//     *
//     * @param httpUrl
//     * @param parameters
//     * @return
//     */
//    public static String sendPut(String httpUrl){
//        String result = "";
//        URL url = null;
//        try {
//            url = new URL(httpUrl);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        if (url != null) {
//            try {
//                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//                urlConn.setRequestProperty("content-type", "application/json");
//                urlConn.setDoInput(true);
//                urlConn.setDoOutput(true);
//                urlConn.setConnectTimeout(5 * 1000);
//                //设置请求方式为 PUT
//                urlConn.setRequestMethod("PUT");
//
//                urlConn.setRequestProperty("Content-Type", "application/json");
//                urlConn.setRequestProperty("charset", "utf-8");
//                urlConn.setRequestProperty("x-docchat-user-id", "opAdmin");
//                urlConn.setRequestProperty("x-docchat-session-token", "4j32kljkljfsklj32kl4jklefjkf");
//
//
//                DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
//                //写入请求参数
//                //这里要注意的是，在构造JSON字符串的时候，实践证明，最好不要使用单引号，而是用“\”进行转义，否则会报错
//                // 关于这一点在上面给出的参考文章里面有说明
//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("status","-2");
//                new ArrayList<Map>();
//                JSONObject jsonParam = new JSONObject(map);
////                String jsonParam = new JSONObject(map);
//                dos.writeBytes(String.valueOf(jsonParam));
//                dos.flush();
//                dos.close();
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return result;
//    }
////    static void mapCreateJson(){
////        Map<String,Object> map = new HashMap<String,Object>();
////        map.put("name","王尼玛");
////        Map<String,Object> map1 = new HashMap<String,Object>();
////        map1.put("Content-Type","application/json");
////        map1.put("charset","utf-8");
////        map1.put("x-docchat-user-id","opAdmin");
////        map1.put("x-docchat-session-token","4j32kljkljfsklj32kl4jklefjkf");
////        Map<String,Object> map2 = new HashMap<String,Object>();
////        map2.put("name","小尼玛");
////        map2.put("age",10);
////        List<Map> jsonObjects = new ArrayList<Map>();
////        jsonObjects.add(map1);
////        jsonObjects.add(map2);
////        map.put("fans",jsonObjects);
////        System.out.println("集合中Map创建json对象:" + new JSONObject(map));
////    }
////    public static void main(String[] args) {
////        Map<String,Object> map = new HashMap<String,Object>();
////        map.put("Content-Type","application/json");
////        map.put("charset","utf-8");
////        map.put("x-docchat-user-id","opAdmin");
////        map.put("x-docchat-session-token","4j32kljkljfsklj32kl4jklefjkf");
////        List<Map> jsonObjects = new ArrayList<Map>();
////        new JSONObject(map);
////        System.out.println("集合中Map创建json对象:" + new JSONObject(map));
////    }
//public static void main(String[] args) {
//    Map<String, String> postEntity = new HashMap<String, String>();
//    postEntity.put("uuid", "59ddf586661914266416dd7c");
//    postEntity.put("status","-2");
////    System.out.println(postEntity);
//    String result =sendPut("http://api.dc.juliye.net/1/operation/withdrawals/59de1226a1d75cfd7805fc17");
//    System.out.println(result);
//}
//    }