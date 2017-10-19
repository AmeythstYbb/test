package com.zlycare.web.boss_24.mongo.po;

/**
 * User: lfl
 * Date: 13-12-10
 * Modify: 2016-01-04
 * Description: 序列对象
 */
public class Sequence {

    /**
     * 主键ID
     */
    private String id;
    /**
     * int序列值
     */
    private Integer seq;
    /**
     * long序列值
     */
    private Long seqLong;
    /**
     * 默认增幅
     */
    private static final Long inc = 1L;
    /**
     * 自增key(int)
     */
    private static final String incKey = "seq";
    /**
     * 自增key(long)
     */
    private static final String incLongKey = "seqLong";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Long getSeqLong() {
        return seqLong;
    }

    public void setSeqLong(Long seqLong) {
        this.seqLong = seqLong;
    }

    public static Long getInc() {
        return inc;
    }

    public static String getIncKey() {
        return incKey;
    }

    public static String getIncLongKey() {
        return incLongKey;
    }
}
