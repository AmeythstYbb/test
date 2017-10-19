package com.zlycare.web.boss_24.utils.core.bo;

/**
 * 文件名对象
 * @author lufanglong
 * @date 2016-07-12 13:52
 */
public class FilenameBo {

    /**
     * 文件名
     */
    private String name;
    /**
     * 文件相对路径
     */
    private String filePath;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 完整名,包含相对路径
     */
    private String fullname;

    public FilenameBo() {
    }

    public FilenameBo(String name, String filePath, String suffix) {
        this.name = name;
        this.filePath = filePath;
        this.suffix = suffix;
    }

    public FilenameBo(String name, String filePath, String suffix, String fullname) {
        this.name = name;
        this.filePath = filePath;
        this.suffix = suffix;
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFullname() {
        if (fullname == null) {
            StringBuilder sb = new StringBuilder();
            //目录可为空
            if (filePath != null) {
                sb.append(filePath);
            }
            //如果名称为空,返回null
            if (name == null) {
                return null;
            } else {
                sb.append(name);
            }
            return sb.toString();
        } else {
            return fullname;
        }
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
