package com.zlycare.web.boss_24.constants.user;/**
 * Created by zhanglw on 2017/6/9.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Author : zhanglianwei
 * Create : 2017/6/9 10:54
 * Update : 2017/6/9 10:54
 * Descriptions :
 */
public class DictConstants {

    public final static Map<String, String> ageMap = new HashMap<>();

    public final static Map<String, String> maritalMap = new HashMap<>();

    public final static Map<String, String> politicalMap = new HashMap<>();

    public final static Map<String, String> certificateMap = new HashMap<>();

    public final static Map<String, String> educationMap = new HashMap<>();

    public final static Map<String, String> kindMap = new HashMap<>();

    public final static Map<String, String> statusMap = new HashMap<>();

    public final static Map<String, String> contractTypeMap = new HashMap<>();

    public final static Map<String, String> recruitChannelMap = new HashMap<>();

    static {
        ageMap.put("1", "男");
        ageMap.put("2", "女");

        maritalMap.put("1", "未婚");
        maritalMap.put("2", "已婚");
        maritalMap.put("3", "离异");
        maritalMap.put("4", "丧偶");


        politicalMap.put("1", "中共党员");
        politicalMap.put("2", "中共预备党员");
        politicalMap.put("3", "共青团员");
        politicalMap.put("4", "民革党员");
        politicalMap.put("5", "民盟党员");
        politicalMap.put("6", "民建党员");
        politicalMap.put("7", "民进党员");
        politicalMap.put("8", "农工党党员");
        politicalMap.put("9", "致公党党员");
        politicalMap.put("10", "九三学社社员");
        politicalMap.put("11", "台盟盟员");
        politicalMap.put("12", "无党派人士");
        politicalMap.put("13", "群众");
        politicalMap.put("14", "中共预备党员");

        certificateMap.put("1", "身份证");
        certificateMap.put("2", "居住证");
        certificateMap.put("3", "签证");
        certificateMap.put("4", "护照");
        certificateMap.put("5", "军人证");
        certificateMap.put("6", "其他");

        educationMap.put("1", "高中及以下");
        educationMap.put("2", "专科");
        educationMap.put("3", "大学本科");
        educationMap.put("4", "硕士研究生");
        educationMap.put("5", "博士研究生");

        kindMap.put("1", "正式工");
        kindMap.put("2", "实习生");
        kindMap.put("3", "临时工");

        statusMap.put("1", "在职");
        statusMap.put("2", "离职");

        contractTypeMap.put("1", "劳动合同");
        contractTypeMap.put("2", "劳务合同");
        contractTypeMap.put("3", "兼职合同");
        contractTypeMap.put("4", "实习协议");
        contractTypeMap.put("5", "咨询合同");
        contractTypeMap.put("6", "未签");

        recruitChannelMap.put("1", "简历投递");
        recruitChannelMap.put("2", "猎头推荐");
        recruitChannelMap.put("3", "内部推荐");
    }
}
