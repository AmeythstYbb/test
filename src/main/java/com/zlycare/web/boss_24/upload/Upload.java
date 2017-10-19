package com.zlycare.web.boss_24.upload;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.zlycare.web.boss_24.utils.common.utils.HttpClient;
import jdk.nashorn.api.scripting.JSObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


/**
 * Author : linguodong
 * Create : 2017/7/2
 * Update : 2017/7/2
 * Descriptions :
 */
public class Upload {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "eYHlzP_qTnzvl-vWvXKG6Tl9bEeGVdIykUPzfCI1";
    String SECRET_KEY = "7CzeyzszcIH8rW8WUzioQmUJXe8jhrFMoQ_mqwny";
    //要上传的空间
    String bucketname = "juliye";
    //上传到七牛后保存的文件名
    String key = "my-java.png";
    //上传文件的路径
    String FilePath = "/.../...";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    Configuration cfg = new Configuration(Zone.zone0());
    UploadManager uploadManager = new UploadManager(cfg);
    BucketManager bucketManager = new BucketManager(auth, cfg);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public String upload(byte[] data) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(data, null, getUpToken());
            //打印返回的信息
            //System.out.println(res.bodyString());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            //System.out.println(putRet.key);
            //System.out.println(putRet.hash);
            return putRet.key;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }

    public void delete(List<String> key) throws IOException {
        if (CollectionUtils.isEmpty(key))
            return;
        try {
            String[] keys = new String[key.size()];
            // String[] keys = (String[]) key.toArray();
            key.toArray(keys);
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucketname, keys);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);

            for (int i = 0; i < keys.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key_ = keys[i];
                System.out.print(key_ + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException e) {
            System.err.println(e.response.toString());
        }
    }


    public static void main(String args[]) throws IOException {
        //new Upload().upload();
//        HttpClient.post("http://api.bate.juliye.net/1/open/news", "{'account':'805001409','content':'805000000'}");
//        HttpClient.fileDownload("https://wx4.sinaimg.cn/mw690/63adf688gy1fjkdf5fqadj21bf0qodt4.jpg","63adf688gy1fjkdf5fqadj21bf0qodt4.jpg");
    }

}
