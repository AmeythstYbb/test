package com.zlycare.web.boss_24.utils.core;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;


/**
 * 分页工具类
 *
 * @author DaiJian
 * @create 2015/12/5 19:45
 */
public class PageUtil {
    private static final Logger log = LoggerFactory.getLogger(PageUtil.class);

    /**
     * 组装分页对象
     *
     * @param start      开始查询位置
     * @param size       查询数量
     * @param total      数据记录总数
     * @param resultList 结果集
     * @param <T>
     * @return Page<T> 分页对象
     */
    public static <T> Page<T> initPage(Integer start, Integer size, Integer total, List<T> resultList) {
        if (start == null) {
            start = 0;
        }
        if (size == null || size.equals(0)) {
            size = 20;
        }
        if (total == null) {
            total = 0;
        }
        if (CollectionUtils.isEmpty(resultList)) {
            resultList = new LinkedList<>();
        }
        return new Page<>(start, size, total, resultList);
    }
}
