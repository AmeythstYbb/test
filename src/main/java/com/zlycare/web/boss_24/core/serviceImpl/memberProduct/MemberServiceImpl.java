package com.zlycare.web.boss_24.core.serviceImpl.memberProduct;

import com.zlycare.web.boss_24.core.mongo.bean.MemberSearchBean;
import com.zlycare.web.boss_24.core.mongo.order.MemberMongo;
import com.zlycare.web.boss_24.core.mongo.order.UsersMongo;
import com.zlycare.web.boss_24.core.mongo.po.MemberShips;
import com.zlycare.web.boss_24.core.mongo.po.Users;
import com.zlycare.web.boss_24.core.service.bean.MemberBean;
import com.zlycare.web.boss_24.core.service.bo.member.MemberBo;
import com.zlycare.web.boss_24.core.service.memberProduct.MemberService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author : linguodong
 * Create : 2017/8/4
 * Update : 2017/8/4
 * Descriptions : 会员管理service实现类
 */
@Service
public class MemberServiceImpl implements MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private UsersMongo usersMongo;
    @Autowired
    private MemberMongo memberMongo;

    /**
     * 查询会员列表
     *
     * @param memberBean bean
     * @return List
     */
    @Override
    public List<MemberBo> getAllList(MemberBean memberBean) {
        MemberSearchBean memberSearchBean = new MemberSearchBean();

        List<String> userIds = new ArrayList<>();

        Long now_time = new Date().getTime();
        Double time = now_time.doubleValue();
        String name = memberBean.getName();
        String docChatNum = memberBean.getDocChatNum();
        String phoneNum = memberBean.getPhoneNum();
        /*调用userMongo, 通过姓名(可为空)、热线号(可为空)、手机号(可为空)，查询出所有的userId列表(userId和热线号一一对应)*/
        List<Users> usersList = new ArrayList<>();
        //  至少有一个参数不为空，再去查询其可能匹配的用户id列表
        if (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(docChatNum) || StringUtils.isNotEmpty(phoneNum)) {
            usersList = usersMongo.getAllUserList(name, docChatNum, phoneNum);
            // 判断用户列表是否为空，不为空抽取用户ID
            if (CollectionUtils.isNotEmpty(usersList)) {
                usersList.stream().filter(users -> StringUtils.isNotEmpty(users.getId())).forEach(users -> userIds.add(users.getId()));
            } else {
                // 有查询参数却查不到用户列表，直接返回空集合。
                return new ArrayList<>();
            }
        }
        // 判断userIds是否为空，是则默认查询,否则让字段userId in usersList
        if (CollectionUtils.isNotEmpty(userIds)) {
            memberSearchBean.setUserIds(userIds);
        }
        memberSearchBean.setCurrentTime(time);
        memberSearchBean.setPageSize(memberBean.getPageSize());
        memberSearchBean.setStart(memberBean.getStart());
        memberSearchBean.setTypeList(memberBean.getType());

        List<MemberShips> newMemberShipsList = new ArrayList<>();
        List<MemberShips> memberShipsList = memberMongo.getAllList(memberSearchBean);

        /*去掉合并*/
        /*
        if (CollectionUtils.isNotEmpty(memberShipsList)) {
            HashMap<String, MemberShips> map = new HashMap<String, MemberShips>();
            // 合并数据，根据userID (同一个热线号下的两条数据合并成一条 uuid vip + uuid advancded = uuid vip advanced)
            memberShipsList.stream().filter(memberShips -> StringUtils.isNotEmpty(memberShips.getUserId())).forEach(memberShips -> {
                if (map.containsKey(memberShips.getUserId())) {//已经存过值。
                    MemberShips m = map.get(memberShips.getUserId());
                    if (memberShips.getType().equals("zlycare_vip")) {// vip会员
                        m.setVipBalance(memberShips.getBalance());
                        m.setVipTotalVal(memberShips.getTotalVal());
                    } else if (memberShips.getType().equals("zlycare")) {//高级会员
                        m.setAdvancedBalance(memberShips.getBalance());
                        m.setAdvancedTotalVal(memberShips.getTotalVal());
                    }
                    map.remove(m.getUserId());//移除
                    map.put(m.getUserId(), m);//重新放入
                } else {// 不存在
                    if (memberShips.getType().equals("zlycare_vip")) {// vip会员
                        memberShips.setVipBalance(memberShips.getBalance());
                        memberShips.setVipTotalVal(memberShips.getTotalVal());
                    } else if (memberShips.getType().equals("zlycare")) {//高级会员
                        memberShips.setAdvancedBalance(memberShips.getBalance());
                        memberShips.setAdvancedTotalVal(memberShips.getTotalVal());
                    } else {
                        logger.error("会员数据异常");
                    }
                    map.put(memberShips.getUserId(), memberShips);//第一次放入
                }
            });
            map.forEach((key, value) -> newMemberShipsList.add(value));//map.get(key))
        }
        // 按照userId(热线号)、会员type聚合(指定会员类型，去除全城购)，sum(剩余金额), sum(总金额)，
        // 同时，类型 = docChat，当前时间<过期时间, 按照生效时间排序 -1，userId in userIds(userIds为空则不使用) type in types
        // 传递参数 memberBean 起始值 页码 类型列表 && userIds && 当前时间
        if (CollectionUtils.isEmpty(newMemberShipsList)) {
            logger.error("查询会员信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        List<MemberBo> memberBoList = BeanMapper.mapList(newMemberShipsList, MemberBo.class);
        */

        List<MemberBo> memberBoList = BeanMapper.mapList(memberShipsList, MemberBo.class);

        /*遍历列表，封装user，返回展示*/
        return initMemberBoList(memberBoList);
    }

    /**
     * 查询会员列表 条数(忽略start和size,其余参数和上一方法一致)
     *
     * @param memberBean bean
     * @return Integer
     */
    @Override
    public Integer countAllList(MemberBean memberBean) {
        MemberSearchBean memberSearchBean = new MemberSearchBean();

        List<String> userIds = new ArrayList<>();

        Long now_time = new Date().getTime();
        Double time = now_time.doubleValue();
        String name = memberBean.getName();
        String docChatNum = memberBean.getDocChatNum();
        String phoneNum = memberBean.getPhoneNum();
        /*调用userMongo, 通过姓名(可为空)、热线号(可为空)、手机号(可为空)，查询出所有的userId列表(userId和热线号一一对应)*/
        List<Users> usersList = new ArrayList<>();
        //  至少有一个参数不为空，再去查询其可能匹配的用户id列表
        if (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(docChatNum) || StringUtils.isNotEmpty(phoneNum)) {
            usersList = usersMongo.getAllUserList(name, docChatNum, phoneNum);
            // 判断用户列表是否为空，不为空抽取用户ID
            if (CollectionUtils.isNotEmpty(usersList)) {
                usersList.stream().filter(users -> StringUtils.isNotEmpty(users.getId())).forEach(users -> userIds.add(users.getId()));
            } else {
                // 有查询参数却查不到用户列表，直接返回0。
                return 0;
            }
        }
        // 判断userIds是否为空，是则默认查询,否则让字段userId in usersList
        if (CollectionUtils.isNotEmpty(userIds)) {
            memberSearchBean.setUserIds(userIds);
        }
        memberSearchBean.setCurrentTime(time);
        memberSearchBean.setTypeList(memberBean.getType());

        return memberMongo.countAllList(memberSearchBean);
    }


    private List<MemberBo> initMemberBoList(List<MemberBo> memberBoList) {
        if (CollectionUtils.isEmpty(memberBoList)) {
            logger.error("参数 null");
            return new ArrayList<>();
        }
        memberBoList.stream().filter(memberBo -> StringUtils.isNotEmpty(memberBo.getUserId())).forEach(member -> {
            Users users = usersMongo.getUser(member.getUserId());
            if (users != null) {
                member.setName(users.getName());
                member.setSex(users.getSex());
                member.setDocChatNum(users.getDocChatNum());
                member.setPhoneNum(users.getPhoneNum());
            }
        });
        return memberBoList;
    }
}
