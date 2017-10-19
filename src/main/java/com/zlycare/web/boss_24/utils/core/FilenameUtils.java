package com.zlycare.web.boss_24.utils.core;

import com.zlycare.web.boss_24.beans.constants.UserTypeEnum;
import com.zlycare.web.boss_24.utils.common.utils.UUIDUtils;
import com.zlycare.web.boss_24.utils.core.bean.OrderFileBean;
import com.zlycare.web.boss_24.utils.core.bo.FilenameBo;
import com.zlycare.web.boss_24.utils.core.constants.FileTypeRuleEnum;
import com.zlycare.web.boss_24.utils.core.constants.OrderFileTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * 文件名工具类(道道)
 *
 * @author lufanglong
 * @date 2016-07-12 13:56
 */
public class FilenameUtils {


    public static final FastDateFormat ORDER_PATH_HOUR_FORMAT = FastDateFormat.getInstance("/yyyy/MM/dd/HH");
    public static final FastDateFormat ORDER_FILENAME_DAY_FORMAT = FastDateFormat.getInstance("yyMMdd");
    public static final String ORDER_ATT_PATH = "/att";
    public static final String ORDER_SOLUTION_PATH = "/solution";
    public static final String ORDER_SOLUTION_IMG_PATH = "/img";
    public static final String ORDER_SOLUTION_FILE_PATH = "/file";
    public static final String ORDER_SOURCE_FILE_PATH = "/sourcefile";
    public static final String ORDER_QS_PATH = "/qs";
    
    public static final String SEPARATOR_POINT = ".";
    public static final String SEPARATOR_SLASH = "/";
    public static final String SEPARATOR_SPLIT_POINT = "\\.";
    public static final String SEPARATOR_SPLIT_BRACKET_START = "[";
    public static final String SEPARATOR_SPLIT_BRACKET_END = "]";

    /**
     * 获取图片文件名
     *
     * @param annotationFilePath    注解文件路径
     * @param suffix                文件后缀
     * @param fileTypeRuleEnum      文件规则
     * @return FilenameBo
     */
    public static FilenameBo getImgFilename(String annotationFilePath, String suffix, FileTypeRuleEnum fileTypeRuleEnum) {
        if (StringUtils.isEmpty(annotationFilePath) || StringUtils.isEmpty(suffix) || fileTypeRuleEnum == null) {
            return null;
        }
        String uuid = UUIDUtils.getUUID();
        FilenameBo filenameBo = new FilenameBo();
        filenameBo.setFilePath(annotationFilePath);
        filenameBo.setSuffix(suffix);

        StringBuilder sb = new StringBuilder();
        //图片使用uuid前2位做第一段目录,uuid后2位最第二段目录
        if (fileTypeRuleEnum.equals(FileTypeRuleEnum.IMG)) {
            sb.append(getStartPath(uuid));
            sb.append(getEndPath(uuid));
            sb.append(SEPARATOR_SLASH);
            sb.append(uuid);
            sb.append(SEPARATOR_POINT);
            sb.append(suffix);
        } else {//默认uuid作为文件名
            sb.append(SEPARATOR_SLASH);
            sb.append(uuid);
            sb.append(SEPARATOR_POINT);
            sb.append(suffix);
        }
        filenameBo.setName(sb.toString());
        return filenameBo;
    }

    /**
     * 获取图片文件名
     *
     * @param annotationFilePath    注解文件路径
     * @param suffix                文件后缀
     * @param fileTypeRuleEnum      文件规则
     * @return FilenameBo
     */
    public static FilenameBo getUserImgFilename(String annotationFilePath, String suffix, UserTypeEnum userTypeEnum,
                                                FileTypeRuleEnum fileTypeRuleEnum) {
        if (StringUtils.isEmpty(annotationFilePath) || userTypeEnum == null || StringUtils.isEmpty(suffix)
                || fileTypeRuleEnum == null) {
            return null;
        }
        String uuid = UUIDUtils.getUUID();
        FilenameBo filenameBo = new FilenameBo();
        filenameBo.setFilePath(annotationFilePath + SEPARATOR_SLASH + userTypeEnum.getRole());
        filenameBo.setSuffix(suffix);

        StringBuilder sb = new StringBuilder();
        //图片使用uuid前2位做第一段目录,uuid后2位最第二段目录
        if (fileTypeRuleEnum.equals(FileTypeRuleEnum.IMG)) {
            sb.append(getStartPath(uuid));
            sb.append(getEndPath(uuid));
            sb.append(SEPARATOR_SLASH);
            sb.append(uuid);
            sb.append(SEPARATOR_POINT);
            sb.append(suffix);
        } else {//默认uuid作为文件名
            sb.append(SEPARATOR_SLASH);
            sb.append(uuid);
            sb.append(SEPARATOR_POINT);
            sb.append(suffix);
        }
        filenameBo.setName(sb.toString());
        return filenameBo;
    }

    /**
     * 获取订单附件文件名
     *
     * @param annotationFilePath    注解文件路径
     * @param suffix                文件后缀
     * @param fileTypeRuleEnum      文件规则
     * @return FilenameBo
     */
    public static FilenameBo getOrderAttFilename(String annotationFilePath, String suffix, FileTypeRuleEnum fileTypeRuleEnum) {
        if (StringUtils.isEmpty(annotationFilePath) || StringUtils.isEmpty(suffix) || fileTypeRuleEnum == null) {
            return null;
        }
        String uuid = UUIDUtils.getUUID();
        FilenameBo filenameBo = new FilenameBo();
        filenameBo.setFilePath(annotationFilePath);
        filenameBo.setSuffix(suffix);

        StringBuilder sb = new StringBuilder();
        //图片使用uuid前2位做第一段目录,uuid后2位最第二段目录
        if (fileTypeRuleEnum.equals(FileTypeRuleEnum.IMG)) {
            sb.append(getStartPath(uuid));
            sb.append(getEndPath(uuid));
            sb.append(SEPARATOR_SLASH);
            sb.append(uuid);
            sb.append(SEPARATOR_POINT);
            sb.append(suffix);
        } else {//默认uuid作为文件名
            sb.append(SEPARATOR_SLASH);
            sb.append(uuid);
            sb.append(SEPARATOR_POINT);
            sb.append(suffix);
        }
        filenameBo.setName(sb.toString());
        return filenameBo;
    }

    /**
     * 获取订单方案文件名
     *
     * @param orderFileBean 订单文件参数bean
     * @return FilenameBo
     */
    public static FilenameBo getOrderSolutionFilename(OrderFileBean orderFileBean) {
        if (orderFileBean == null || !orderFileBean.check()) {
            return null;
        }
        //文件名
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR_SLASH);//斜杠
        sb.append(SEPARATOR_SPLIT_BRACKET_START);//中括号
        sb.append(orderFileBean.getOrderName());//订单名称
        sb.append(SEPARATOR_SPLIT_BRACKET_END);//中括号
        sb.append(orderFileBean.getOrderFileTypeEnum().getValue());//文件类型
        sb.append(orderFileBean.getSolutionNo());//方案序号
        sb.append(orderFileBean.getSubmitCount());//方案提交次数
        sb.append(ORDER_FILENAME_DAY_FORMAT.format(orderFileBean.getOrderCreateTime()));//格式化时间
        sb.append(orderFileBean.getOrderNo());//订单编号
        sb.append(SEPARATOR_POINT);//点
        sb.append(orderFileBean.getSuffix());//后缀

        //文件目录
        StringBuilder filePathBuilder = new StringBuilder();
        filePathBuilder.append(orderFileBean.getAnnotationFilePath());
        filePathBuilder.append(getOrderIdDatePath(orderFileBean.getOrderCreateTime(), orderFileBean.getOrderId()));
        filePathBuilder.append(ORDER_SOLUTION_PATH);
        filePathBuilder.append(ORDER_SOLUTION_FILE_PATH);

        FilenameBo filenameBo = new FilenameBo();
        //规则:/yyyy/MM/dd/HH/orderId/solution/solutionId
        filenameBo.setFilePath(filePathBuilder.toString());
        filenameBo.setSuffix(orderFileBean.getSuffix());
        filenameBo.setName(sb.toString());
        return filenameBo;
    }

    /**
     * 获取订单方案图片文件名
     *
     * @param orderFileBean 订单文件参数bean
     * @return FilenameBo
     */
    public static FilenameBo getOrderSolutionImgFilename(OrderFileBean orderFileBean) {
        if (orderFileBean == null || !orderFileBean.check()) {
            return null;
        }
        //文件名
        String uuid = UUIDUtils.getUUID();
        StringBuilder sb = new StringBuilder();
        sb.append(getStartPath(uuid));
        sb.append(getEndPath(uuid));
        sb.append(SEPARATOR_SLASH);
        sb.append(uuid);
        sb.append(SEPARATOR_POINT);
        sb.append(orderFileBean.getSuffix());

        //文件目录
        StringBuilder filePathBuilder = new StringBuilder();
        filePathBuilder.append(orderFileBean.getAnnotationFilePath());
        filePathBuilder.append(getOrderIdDatePath(orderFileBean.getOrderCreateTime(), orderFileBean.getOrderId()));
        filePathBuilder.append(ORDER_SOLUTION_PATH);
        filePathBuilder.append(ORDER_SOLUTION_IMG_PATH);

        FilenameBo filenameBo = new FilenameBo();
        //规则:/yyyy/MM/dd/HH/orderId/solution/solutionId
        filenameBo.setFilePath(filePathBuilder.toString());
        filenameBo.setSuffix(orderFileBean.getSuffix());
        filenameBo.setName(sb.toString());
        return filenameBo;
    }


    /**
     * 获取订单源文件文件名
     *
     * @param orderFileBean 订单文件参数bean
     * @return FilenameBo
     */
    public static FilenameBo getOrderSourceFilename(OrderFileBean orderFileBean) {
        if (orderFileBean == null) {
            return null;
        }

        //文件名
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR_SLASH);//斜杠
        sb.append(SEPARATOR_SPLIT_BRACKET_START);//中括号
        sb.append(orderFileBean.getOrderName());//订单名称
        sb.append(SEPARATOR_SPLIT_BRACKET_END);//中括号
        sb.append(orderFileBean.getOrderFileTypeEnum().getValue());//文件类型
        sb.append(orderFileBean.getSubmitCount());//方案提交次数
        sb.append(ORDER_FILENAME_DAY_FORMAT.format(orderFileBean.getOrderCreateTime()));//格式化时间
        sb.append(orderFileBean.getOrderNo());//订单编号
        sb.append(orderFileBean.getSourceFileNo());//源文件序号
        sb.append(SEPARATOR_POINT);//点
        sb.append(orderFileBean.getSuffix());//后缀

        //文件目录
        StringBuilder filePathBuilder = new StringBuilder();
        filePathBuilder.append(orderFileBean.getAnnotationFilePath());
        filePathBuilder.append(getOrderIdDatePath(orderFileBean.getOrderCreateTime(), orderFileBean.getOrderId()));
        filePathBuilder.append(ORDER_SOURCE_FILE_PATH);

        FilenameBo filenameBo = new FilenameBo();
        //规则:/yyyy/MM/dd/HH/orderId/sourcefile
        filenameBo.setFilePath(filePathBuilder.toString());
        filenameBo.setSuffix(orderFileBean.getSuffix());
        filenameBo.setName(sb.toString());
        return filenameBo;
    }
    
    
    /**
     * 获取订单调研问卷名称
     *
     * @param orderFileBean 订单文件参数bean
     * @return FilenameBo
     */
    public static FilenameBo getOrderQsFilename(OrderFileBean orderFileBean) {
        if (orderFileBean == null || !orderFileBean.check()) {
            return null;
        }
        //文件名
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR_SLASH);//斜杠
        sb.append(SEPARATOR_SPLIT_BRACKET_START);//中括号
        sb.append(orderFileBean.getOrderName());//订单名称
        sb.append(SEPARATOR_SPLIT_BRACKET_END);//中括号
        sb.append(orderFileBean.getOrderFileTypeEnum().getValue());//文件类型
        sb.append(ORDER_FILENAME_DAY_FORMAT.format(orderFileBean.getOrderCreateTime()));//格式化时间
        sb.append(orderFileBean.getOrderNo());//订单编号
        sb.append(SEPARATOR_POINT);//点
        sb.append(orderFileBean.getSuffix());//后缀

        //文件目录
        StringBuilder filePathBuilder = new StringBuilder();
        filePathBuilder.append(orderFileBean.getAnnotationFilePath());
        filePathBuilder.append(getOrderIdDatePath(orderFileBean.getOrderCreateTime(), orderFileBean.getOrderId()));
        filePathBuilder.append(ORDER_QS_PATH);
        filePathBuilder.append(ORDER_SOLUTION_FILE_PATH);

        FilenameBo filenameBo = new FilenameBo();
        filenameBo.setFilePath(filePathBuilder.toString());
        filenameBo.setSuffix(orderFileBean.getSuffix());
        filenameBo.setName(sb.toString());
        return filenameBo;
    }
    
    /**
    **
    * 获取订单调研问卷图片文件名
    *
    * @param orderFileBean 订单文件参数bean
    * @return FilenameBo
    */
   public static FilenameBo getOrderQsImgFilename(OrderFileBean orderFileBean) {
       if (orderFileBean == null || !orderFileBean.check()) {
           return null;
       }
       //文件名
       String uuid = UUIDUtils.getUUID();
       StringBuilder sb = new StringBuilder();
       sb.append(getStartPath(uuid));
       sb.append(getEndPath(uuid));
       sb.append(SEPARATOR_SLASH);
       sb.append(uuid);
       sb.append(SEPARATOR_POINT);
       sb.append(orderFileBean.getSuffix());

       //文件目录
       StringBuilder filePathBuilder = new StringBuilder();
       filePathBuilder.append(orderFileBean.getAnnotationFilePath());
       filePathBuilder.append(getOrderIdDatePath(orderFileBean.getOrderCreateTime(), orderFileBean.getOrderId()));
       filePathBuilder.append(ORDER_QS_PATH);
       filePathBuilder.append(ORDER_SOLUTION_IMG_PATH);

       FilenameBo filenameBo = new FilenameBo();
       //规则:/yyyy/MM/dd/HH/orderId/solution/solutionId
       filenameBo.setFilePath(filePathBuilder.toString());
       filenameBo.setSuffix(orderFileBean.getSuffix());
       filenameBo.setName(sb.toString());
       return filenameBo;
   }


    /**
     * 获取文件后缀名
     *
     * @param filename 文件名
     * @return String
     */
    public static String getFileSuffix(String filename) {
        if (StringUtils.isEmpty(filename)) {
            return null;
        }
        if (filename.contains(SEPARATOR_POINT)) {//判断是否包含.
            String[] strArr = filename.split(SEPARATOR_SPLIT_POINT);
            return strArr[strArr.length - 1];
        }
        return null;
    }


    /**
     * 截取uuid开头2位最为目录
     *
     * @param uuid
     * @return String
     */
    private static String getStartPath(String uuid) {
        return StringUtils.isEmpty(uuid) ? null : SEPARATOR_SLASH + uuid.substring(0, 2);
    }

    /**
     * 截取uuid最后2位最为目录
     *
     * @param uuid
     * @return String
     */
    private static String getEndPath(String uuid) {
        return StringUtils.isEmpty(uuid) ? null : SEPARATOR_SLASH + uuid.substring(uuid.length() - 2, uuid.length());
    }


    /**
     * 获取订单时间路径
     *
     * @param createTime 订单创建时间
     * @return String
     */
    private static String getOrderDatePath(Date createTime) {
        return createTime == null ? null : ORDER_PATH_HOUR_FORMAT.format(createTime);
    }

    /**
     * 获取订单时间路径
     *
     * @param createTime 订单创建时间
     * @param orderId    订单ID
     * @return String
     */
    private static String getOrderIdDatePath(Date createTime, Integer orderId) {
        return createTime == null ? null : ORDER_PATH_HOUR_FORMAT.format(createTime) + SEPARATOR_SLASH + orderId;
    }

    public static void main(String[] args) {
        String test = "ac123eg";
        String testFilename = "ac123eg.jpg";
        System.out.println("startPath = [" + getStartPath(test) + "]");
        System.out.println("endPath = [" + getEndPath(test) + "]");
        System.out.println("fileSuffix = [" + getFileSuffix(testFilename) + "]");
        System.out.println("imgFilename = [" + getImgFilename("/banner", "png", FileTypeRuleEnum.IMG).getFullname() + "]");
        System.out.println("userImgFilename = [" + getUserImgFilename("/user/avatar", "jpg", UserTypeEnum.CUSTOMER, FileTypeRuleEnum.IMG).getFullname() + "]");
        System.out.println("orderAttFilename = [" + getOrderAttFilename("/order/att", "jpg", FileTypeRuleEnum.IMG).getFullname() + "]");

        OrderFileBean orderFileBean = new OrderFileBean();
        orderFileBean.setAnnotationFilePath("/order");
        orderFileBean.setSuffix("ppt");
        orderFileBean.setOrderId(12345678);
        orderFileBean.setOrderNo("87654321");
        orderFileBean.setOrderCreateTime(new Date());
        orderFileBean.setOrderName("test");
//        orderFileBean.setSolutionId(111);
        orderFileBean.setSolutionNo(3);
        orderFileBean.setOrderFileTypeEnum(OrderFileTypeEnum.DEEPON);
        orderFileBean.setSubmitCount(2);
        System.out.println("orderSolutionFilename = [" + getOrderSolutionFilename(orderFileBean).getFullname() + "]");

        orderFileBean.setSuffix("jpg");
        System.out.println("orderSolutionImgFilename = [" + getOrderSolutionImgFilename(orderFileBean).getFullname() + "]");

        orderFileBean.setSuffix("zip");
        System.out.println("orderSourceFilename = [" + getOrderSourceFilename(orderFileBean).getFullname() + "]");
    }
}
