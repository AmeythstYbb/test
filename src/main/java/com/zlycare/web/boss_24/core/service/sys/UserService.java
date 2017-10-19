package com.zlycare.web.boss_24.core.service.sys;

import com.zlycare.web.boss_24.core.service.bean.UserBean;
import com.zlycare.web.boss_24.core.service.bo.UserBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions : 系统管理，安全相关实体的管理类,用户
 */
public interface UserService {

    /**
     * 根据id获取用户信息
     *
     * @param id id
     * @return User
     */
    UserBo getUser(Long id);

    /**
     * 查询当前用户可以查看到的所有用户列表。地域、业务板块、角色
     * 最高权限，全查
     *
     * @return User
     */
    List<UserBo> findUser(Integer pageSize, Integer start);

    /**
     * 查询当前用户可以查看到的所有用户列表。地域、业务板块、角色
     * 最高权限，全查
     *
     * @return User
     */
    List<UserBo> findUser();

    /**
     * 根据登录名获取账号
     *
     * @param jobNumber jobNumber
     * @return User
     */
    UserBo getUserByJobNumber(String jobNumber);

    /**
     * 根据登录名获取账号 不区分是否删除
     *
     * @param jobNumber jobNumber
     * @return User
     */
    List<UserBo> getAllUserByJobNumber(String jobNumber);


    /**
     * 根据id删除用户，修改状态
     *
     * @param id id
     */
    Integer deleteUser(Long id);


    /**
     * 修改登录信息
     *
     * @param id id
     */
    public void updateUserLoginInfo(Long id);

    /**
     * 存储用户
     *
     * @param userBean userBean
     */
    boolean saveUser(UserBean userBean);

    /**
     * 根据id修改密码
     *
     * @param id          id
     * @param jobNumber   jobNumber
     * @param newPassword newPassword
     */
    void updatePasswordById(Long id, String jobNumber, String newPassword);

    /**
     * 根据id修改密码 修改权限
     *
     * @param id          id
     * @param jobNumber   jobNumber
     * @param newPassword newPassword
     */
    void updateUser(Long id, String jobNumber, String newPassword, List<Integer> roleList, String area);

    /**
     * 查询当前用户可以查看到的所有用户列表。地域、业务板块、角色
     * 最高权限，全查
     *
     * @param jobNumber jobNumber
     * @param pageSize  pageSize
     * @param start     start
     * @return List<UserBo>
     */
    List<UserBo> findUserBySearch(String jobNumber, Integer start, Integer pageSize);

    /**
     * 查询数量
     *
     * @param jobNumber jobNumber
     * @param pageSize  pageSize
     * @param start     start
     * @return Integer
     */
    Integer countBySearch(String jobNumber, Integer start, Integer pageSize);
}
