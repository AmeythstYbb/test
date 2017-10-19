package com.zlycare.web.boss_24.core.dao.mapper;

import com.zlycare.web.boss_24.core.dao.po.UserPo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/30
 * Update : 2017/5/30
 * Descriptions :
 */
public interface UserMapper {

    /**
     * 创建用户
     *
     * @param userPo userPo
     * @return int
     */
    int create(UserPo userPo);

    /**
     * 获取用户
     *
     * @param id id
     * @return User
     */
    UserPo findOne(@Param("id") long id);

    /**
     * 获取用户
     *
     * @param jobNumber  jobNumber
     * @param deleteFlag deleteFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return User
     */
    UserPo findByJobNumber(@Param("jobNumber") String jobNumber, @Param("deleteFlag") String deleteFlag);

    /**
     * 获取用户
     *
     * @param jobNumber jobNumber
     * @return User
     */
    List<UserPo> getAllUserByJobNumber(@Param("jobNumber") String jobNumber);

    /**
     * 查询所以可删除用户
     *
     * @param deleteFlag deleteFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<User>
     */
    List<UserPo> findUserName(@Param("deleteFlag") String deleteFlag);

    /**
     * 根据id删除用户
     *
     * @param id         id
     * @param deleteFlag deleteFlag 默认 MenuConstants.DEL_FLAG_DELETE
     * @return Integer
     */
    Integer deleteById(@Param("id") long id, @Param("deleteFlag") String deleteFlag);

    /**
     * 根据id修改密码
     *
     * @param password password
     * @param id       id
     * @return Integer
     */
    Integer updatePasswordById(@Param("password") String password, @Param("id") long id);

    /**
     * 根据id修改用户业务区域
     *
     * @param area area
     * @param id       id
     * @return Integer
     */
    Integer updateArea(@Param("area") String area, @Param("id") long id);

    /**
     * 根据id修改登录信息
     *
     * @param loginIp   loginIp
     * @param loginDate loginDate
     * @param id        id
     * @return Integer
     */
    Integer updateLoginInfo(@Param("loginIp") String loginIp, @Param("loginDate") Date loginDate, @Param("id") long id);

    /**
     * 查询所有用户
     *
     * @param deleteFlag deleteFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<UserPo>
     */
    List<UserPo> findAllList(@Param("deleteFlag") String deleteFlag);

    /**
     * 查询所有用户
     *
     * @param deleteFlag deleteFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<UserPo>
     */
    List<UserPo> findAllListPage(@Param("pageSize") Integer pageSize, @Param("start") Integer start, @Param("deleteFlag") String deleteFlag);


    /**
     * 查询所有用户
     *
     * @param deleteFlag deleteFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<UserPo>
     */
    List<UserPo> findUserBySearch(
            @Param("jobNumber") String jobNumber,
            @Param("pageSize") Integer pageSize,
            @Param("start") Integer start,
            @Param("deleteFlag") String deleteFlag);

    /**
     * 查询所有用户
     *
     * @param deleteFlag deleteFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<UserPo>
     */
    int countBySearch(
            @Param("jobNumber") String jobNumber,
            @Param("pageSize") Integer pageSize,
            @Param("start") Integer start,
            @Param("deleteFlag") String deleteFlag);
}
