package com.zlycare.web.boss_24.utils.core;

import com.zlycare.web.boss_24.beans.constants.error.ServiceBusinessCode;
import com.zlycare.web.boss_24.beans.exception.ServiceException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * 文件操作工具类
 *
 * @author DaiJian
 * @create 2016/1/31 15:58
 */
public class FileUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    /**
     * @param fileArray      文件数组
     * @param destinationDir 文件目标目录
     * @return List<String> 文件路径列表
     */
    public static List<String> uploadFiles(CommonsMultipartFile[] fileArray, String destinationDir) {
        if (ArrayUtils.isEmpty(fileArray)) {
            log.error("文件上传失败，文件列表为空");
            throw new ServiceException(ServiceBusinessCode.FILE_ARRAY_IS_EMPTY);
        }
        String fileName;             //原始文件名
        String destinationPath;      //目标文件名
        String suffix;               //文件后缀
        String destinationName; //目标文件名
        StringBuilder builder;
        List<String> filePathList = new LinkedList<>();     //文件路径
        for (CommonsMultipartFile commonsMultipartFile : fileArray) {
            if (commonsMultipartFile == null) {
                log.info("文件为null");
            } else if (StringUtils.isEmpty(commonsMultipartFile.getOriginalFilename())) {
                log.info("文件为空");
            } else {
                fileName = commonsMultipartFile.getOriginalFilename();
                suffix = fileName.substring(fileName.lastIndexOf("."));
                builder = new StringBuilder();
                destinationName = UUID.randomUUID().toString();
                builder.append(destinationDir)
                        .append(destinationName)
                        .append(suffix);
                destinationPath = builder.toString();
                destinationName += suffix;
                try {
                    commonsMultipartFile.getFileItem().write(new File(destinationPath));
                    filePathList.add(destinationName);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ServiceException(ServiceBusinessCode.FILE_UPLOAD_ERROR);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServiceException(ServiceBusinessCode.FILE_UPLOAD_ERROR);
                }
            }
        }
        return filePathList;
    }
}
