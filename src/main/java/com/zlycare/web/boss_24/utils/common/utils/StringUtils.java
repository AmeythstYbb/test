package com.zlycare.web.boss_24.utils.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author yz.wu
 */
public class StringUtils {

    public static final String URL_REGEX = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&= ]*)?";
    private static Pattern pattern = Pattern.compile(URL_REGEX);

    /**
     * 把一个字符串列表转换成指定字符分割的字符串
     * @param list
     * @param separator
     * */
    public static String listToString(List<String> list, String separator) {
        if (CollectionUtils.isNotEmpty(list) && separator != null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                if(i < list.size() -1){
                    sb.append(list.get(i)+separator);
                }else{
                    sb.append(list.get(i));
                }
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
     *
     * @param c 需要判断的字符
     * @return 返回true, Ascill字符
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s 需要得到长度的字符串
     * @return i得到的字符串长度
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    /**
     * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
     *
     * @param text    原始字符串
     * @param length  截取长度(一个汉字长度按2算的)
     * @param endWith 后缀
     * @return 返回的字符串
     */
    public static String substring(String text, int length, String endWith) {
        int textLength = text.length();
        int byteLength = 0;
        StringBuilder sb = new StringBuilder();
        String s = null;
        for (int i = 0; i < textLength && byteLength < length; i++) {
            s = org.apache.commons.lang3.StringUtils.substring(text, i, i + 1);
            if (s.getBytes().length == 1) {//英文  
                byteLength++;
            } else {//中文  
                byteLength += 2;
            }
            sb.append(s);
        }
        try {
            if (byteLength < text.getBytes("GBK").length) {//getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3
                sb.append(endWith);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * 取用户输入热词的跳转url
     *
     * @param hotword
     * @return
     */
    public static String getBaiduHotWordUrl(String hotword, String from) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(hotword) && org.apache.commons.lang3.StringUtils.isNotEmpty(from)) {
            StringBuilder sb = new StringBuilder();
            sb.append("http://m.baidu.com/s?from=").append(from).append("&bd_page_type=1&word=").append(com.zlycare.web.boss_24.utils.common.utils.EncodeUtils.urlEncode(hotword, "UTF-8"));
            return sb.toString();
        }

        return null;
    }

    public static boolean IsUrl(String str) {
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        String url = "http://m.jd.com/product/1113824653.html?sid=&teamSign=1";
        System.out.println(IsUrl(url));
    }
}
