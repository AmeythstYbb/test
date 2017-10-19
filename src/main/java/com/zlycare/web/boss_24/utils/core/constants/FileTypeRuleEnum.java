package com.zlycare.web.boss_24.utils.core.constants;

/**
 * 文件类型规则
 * @author lufanglong
 * @date 2016-07-12 14:26
 */
public enum FileTypeRuleEnum {

    /**
     * 默认不处理
     */
    DEFAULT(0),
    /**
     * 图片
     */
    IMG(1);

    private int value;

    public int getValue() {
        return this.value;
    }

    FileTypeRuleEnum(int value) {
        this.value = value;
    }
}
