package com.zlycare.web.boss_24.utils.user;//package com.dongdaodao.user.keeper.utils;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//
//public class UserAvatarUtils {
//    protected final static Logger logger = LoggerFactory.getLogger(UserAvatarUtils.class);
//
//    public static String downloadAvatarIfNeeded(String savePath, String avatar) {
//        String avatarNew = avatar;
//        if (avatar.length() > 0 && avatar.startsWith("http")) {
//            avatarNew = UserAvatarUtils.downloadAvatar(savePath, avatar);
//        } else if (avatar.length() == 0) {
//            avatarNew = "default_avatar.png";
//        }
//        return avatarNew;
//    }
//
//    public static String downloadAvatar(String savePath, String sourceUrl) {
//        String outputFileName;
//        File uploadRoot = new File(savePath);
//        outputFileName = "platform_" + RandomStringUtils.random(10, true, true) + ".jpg";
//        uploadRoot.mkdirs();
//        File outputFile = new File(uploadRoot, outputFileName);
//        try {
//            URL httpurl = new URL(sourceUrl);
//            FileUtils.copyURLToFile(httpurl, outputFile);
//        } catch (IOException e) {
//            logger.error("Fail to save user avatar to " + outputFile.getAbsolutePath(), e);
//            return "";
//        }
//        return outputFileName;
//    }
//}
