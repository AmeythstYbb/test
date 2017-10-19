package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.core.mongo.enums.ProductCollectionEnum;
import com.zlycare.web.boss_24.mongo.base.SequenceBaseDao;
import org.springframework.stereotype.Repository;

/**
 * Mongodb sequence dao
 *
 * @author DaiJian
 * @since 1.9.8
 */
@Repository
public class ProductSequenceDao extends SequenceBaseDao {
    /**
     * 官网快速发布需求 PK seq
     *
     * @return Integer
     */
    public Integer getOfficialWebRequirementSeq() {
        return super.getSequenceId(ProductCollectionEnum.OFFICIAL_WEB_REQUIREMENT.getName());
    }

    /**
     * orderCpAssignTimeLogs long seq
     *
     * @return Long
     * @since 1.9.14
     */
    public Long getOrderCpAssignTimeLogsLongSeq() {
        return super.getSequenceLongId(ProductCollectionEnum.ORDER_CP_ASSIGN_TIME_LOGS.getName());
    }

    /**
     * Order op status long seq
     *
     * @return long
     * @since 1.9.14
     */
    public Long getOrderOpStatusLongSeq() {
        return super.getSequenceLongId(ProductCollectionEnum.ORDER_OP_STATUS.getName());
    }

    /**
     * CpSolutionNoRecord long seq
     *
     * @return Long
     * @since 1.10.0
     */
    public Long getCpSolutionNoRecord() {
        return super.getSequenceLongId(ProductCollectionEnum.CP_SOLUTION_NO_RECORD.getName());
    }

    /**
     * questionnaire long seq
     * @return Long
     * @since 1.11.0
     */
    public Long getQuestionnaireSeq() {
        return super.getSequenceLongId(ProductCollectionEnum.QUESTIONNAIRE.getName());
    }
}
