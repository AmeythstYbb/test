package com.zlycare.web.boss_24.utils.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 拷贝文件
     * @param src
     * @param desc
     * @return status
     */
    public static boolean copyFile(File src, File desc){
        if(src.exists()){
            return saveFile(readFile(src.getPath()),desc.getPath());
        }
        return false;
    }
    /**
     * 保存文件
     * @param data
     * @param fileName
     * */
    public static boolean saveFile(byte[] data, String fileName) {
        if (data != null && StringUtils.isNotEmpty(fileName)) {
            try {
                File file = new File(fileName);
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
                fos.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    /**
     * 读取文件内容，返回byte[]
     * @param fileName
     * @return byte[]
     * */
    public static byte[] readFile(String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            try {
                File file = new File(fileName);
                FileInputStream fin = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fin.read(data);

                return data;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static byte[] readFile(String path, String fileName){
        if(StringUtils.isNotEmpty(path) && StringUtils.isNotEmpty(fileName)){
            if(new File(path+fileName).exists())
                return readFile(path+fileName);
        }
        return null;
    }

    /**
     * 删除当前文件
     * @param fileName
     * */
    public static boolean deleteFile(String fileName){
        if(StringUtils.isNotEmpty(fileName)){
            File file = new File(fileName);
            if(file.exists()){
                log.info("file exists...............");
                return file.delete();
            }
        }
        return false;
    }

    /**
     * 删除文件夹及文件夹下所有文件
     * @param folderPath
     * */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            File myFilePath = new File(folderPath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除当前文件夹下的所有文件和文件夹
     * @param path
     * */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 将字符串写入到指定的文件
     */
    public static void writeString2File(String filePath, String context, boolean append) {
        if (StringUtils.isNotEmpty(filePath) && StringUtils.isNotEmpty(context)) {
            try {
                File file = new File(filePath);
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                FileWriter fw = new FileWriter(filePath, append);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(context);
                bw.close();
                fw.close();
            } catch (Exception e) {
                if (log.isErrorEnabled())
                    log.error("write string to file error:'" + e.getMessage() + "'");
                e.printStackTrace();
            }
        }
    }

    public static String file2Base64(String f) {
        if (f != null && StringUtils.isNotEmpty(f)) {
            return file2Base64(new File(f));
        }
        return null;
    }

    /**
     * 将文件转成base64编码的字符串
     */
    public static String file2Base64(File f) {
        if (f != null && f.exists()) {
            return Base64.encodeBase64String(getByteForFile(f));
        }
        return null;
    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getByteForFile(File f) {
        byte[] buffer = null;
        try {
            int length = 1000;
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
            byte[] b = new byte[length];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();

            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            if (log.isErrorEnabled())
                log.error("get byte for file error:'" + e.getMessage() + "'");
            e.printStackTrace();
        } catch (IOException e) {
            if (log.isErrorEnabled())
                log.error("get byte for file error:'" + e.getMessage() + "'");
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 文件转换为字符串
     *
     * @param f       文件
     * @param charset 文件的字符集
     * @return 文件内容
     * @author tonydeng
     */
    public static String file2String(File f, String charset) {
        String result = null;
        try {
            result = stream2String(new FileInputStream(f), charset);
        } catch (FileNotFoundException e) {
            if (log.isErrorEnabled())
                log.error("file to string error:'" + e.getMessage() + "'");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 文件转换为字符串
     *
     * @param in      字节流
     * @param charset 文件的字符集
     * @return 文件内容
     * @author tonydeng
     */
    public static String stream2String(InputStream in, String charset) {
        StringBuffer sb = new StringBuffer();
        Reader r = null;
        try {
            r = new InputStreamReader(in, charset);
            int length = 0;
            for (char[] c = new char[1024]; (length = r.read(c)) != -1; ) {
                sb.append(c, 0, length);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (r != null)
                    r.close();
            } catch (IOException e) {
                if (log.isErrorEnabled())
                    log.error("stream to string error:'" + e.getMessage() + "'");
                e.printStackTrace();
            }

        }
        return sb.toString();
    }


    /**
     * 获得完整的文件名，并且在扩展名前加入自己的给定的标识
     *
     * @param file
     * @param flag
     * @return filename
     * @author tonydeng
     */
    public static String getFilePathName(File file, String flag) {
        if (file.exists()) {
            return file.getParent() + "/" + getFileName(file) + flag + "."
                    + getPostfix(file);
        }
        return null;
    }

    /**
     * 指定输出格式
     *
     * @param file
     * @param flag
     * @param specialPostfix
     * @return
     */
    public static String getFilePathName(File file, String flag, String specialPostfix) {
        if (file.exists()) {
            return file.getParent() + "/" + getFileName(file) + flag + "."
                    + (StringUtils.isNotEmpty(specialPostfix) ? specialPostfix : getPostfix(file));
        }
        return null;
    }

    /**
     * 将url的扩展名前嵌入指定的标记
     *
     * @param url
     * @param flag
     * @return url
     * @author tonydeng
     */
    public static String getFilePathName(String url, String flag) {

        if (!StringUtils.isEmpty(StringUtils.trim(url))) {
            int lastflag = url.lastIndexOf(".");
            if (lastflag > -1) {
                String postfix = url.substring(lastflag + 1);
                return url.substring(0, lastflag) + flag + "." + postfix;
            }
        }
        return null;
    }

    /**
     * 获取去除最后一个标识（以_分隔）的文件路径
     *
     * @param url
     * @return path
     * @author tonydeng
     */
    public static String getFilePathNameByNoFlag(String url) {
        if (!StringUtils.isEmpty(StringUtils.trim(url))) {
            return url.substring(0, url.lastIndexOf("_")) + url.substring(url.lastIndexOf("."));
        }
        return null;
    }

    /**
     * 获取文件名（不包括扩展名）
     *
     * @param file
     * @author tonydeng
     */
    public static String getFileName(File file) {
        String filename = "";
        if (file.exists()) {
            if (file.getName().lastIndexOf(".") != -1) {
                filename = file.getName().substring(0,
                        file.getName().lastIndexOf("."));
            } else {
                filename = file.getName();
            }
        }
        return filename;
    }

    /**
     * 获取文件名（不包括扩展名）
     *
     * @param file
     * @author tonydeng
     */
    public static String getFileName(String file) {
        File f = new File(file);
        return getFileName(f);
    }

    public static String replaceFilePostfix(String file, String postfix) {
        return replaceFilePostfix(new File(file), postfix);
    }

    /**
     * 替换文件后缀
     */
    public static String replaceFilePostfix(File file, String postfix) {
        if (file.exists()) {
            if (file.getName().lastIndexOf(".") != -1) {
                return file.getParent() + "/" + file.getName().substring(0,
                        file.getName().lastIndexOf(".") + 1) + postfix;
            }
        }
        return null;
    }


    /**
     * 获取扩展名
     *
     * @param file
     * @author tonydeng
     */
    public static String getPostfix(File file) {
        String postfix = "";
        if (file.exists()) {
            if (file.getName().lastIndexOf(".") != -1) {
                postfix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            }
        }
        return postfix;
    }

    /**
     * 获取扩展名
     *
     * @param file
     * @author tonydeng
     */
    public static String getPostfix(String file) {
        File f = new File(file);
        return getPostfix(f);
    }
}
