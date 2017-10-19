package com.zlycare.web.boss_24.controller.user;

import com.alibaba.druid.util.StringUtils;
import com.zlycare.web.boss_24.controller.user.bean.UserLoginBean;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Descriptions :登录注册
 * Author : kaihua
 * Create : 2016/4/9 16:51
 * Update : 2016/4/9 16:51
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    private static Date getCurrentDayLastDate() {

        DateTime dateTime = new DateTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return dateTime.toDate();
    }


    /**
     * get 进入登录界面
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView loginView() {
        return new ModelAndView("/login");
    }

    /**
     * 用户登录
     * post 提交登录表单
     * <p>
     * 登录失败，真正登录的POST请求由Filter完成,登录成功后直接到登录成功页面，失败后则继续回到当前方法继续进行。
     *
     * @param ulb 登录信息
     * @return ModelAndView
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(UserLoginBean ulb, HttpServletResponse response, HttpSession session) {
        ModelAndView modelAndView;
        //判断是否有用户信息，存在首页，不存在，登录[防止后退浏览器时重新登录无效无法跳转从而停留在登录页的问题]
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {// 登录页
            modelAndView = new ModelAndView("/login");
            modelAndView.addObject("errorMessage", "登录失败, 请重试.");//设置错误信息
        } else {// 首页
            modelAndView = new ModelAndView("redirect:/index");
        }
        return modelAndView;
    }

//    /**
//     * 密码加密
//     *
//     * @param password
//     * @return
//     */
//    private static String passwdMd5(String password) {
//        String tempPassword = MD5Util.md5(password).toUpperCase();
//        return MD5Util.md5(tempPassword + tempPassword.substring(9, 15)).toUpperCase();
//    }

//    /**
//     * 用户注册
//     *
//     * @return
//     */
//    @RequestMapping(value = "/register")
//    public ModelAndView register(HttpSession session, HttpServletResponse response) {
//        //防止浏览器缓存，导致页面不过Controller
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "No-cache");
//        response.setDateHeader("Expires", -1);
//        response.setHeader("Cache-Control", "No-store");
//
//        ModelAndView modelAndView = new ModelAndView("register");
//        if (session != null && session.getAttribute(SessionConstant.USER_UID) != null && session.getAttribute(SessionConstant.USER_ACC) != null) {
//            modelAndView.setViewName("redirect:/user/profile/auth");
//        }
//        return modelAndView;
//    }

//    @RequestMapping(value = "/forgetPassword")
//    public ModelAndView forgetPwd(HttpSession session, HttpServletResponse response) {
//        //防止浏览器缓存，导致页面不过Controller
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "No-cache");
//        response.setDateHeader("Expires", -1);
//        response.setHeader("Cache-Control", "No-store");
//
//        ModelAndView modelAndView = new ModelAndView("resetPassword");
//        if (session != null && session.getAttribute(SessionConstant.USER_UID) != null && session.getAttribute(SessionConstant.USER_ACC) != null) {
//            modelAndView.setViewName("redirect:/user/profile/auth");
//        }
//        return modelAndView;
//    }
//
//
//    @RequestMapping(value = "/resetPassword")
//    @ResponseBody
//    public ModelAndView toResetUserPassword() {
//        return new ModelAndView("resetPassword");
//    }
//
//    /**
//     * 忘记密码修改
//     *
//     * @param forgetPasswordBean
//     */
//    @RequestMapping(value = "/doResetPassword")
//    @ResponseBody
//    public LoginResultVo ResetUserPassword(ForgetPasswordBean forgetPasswordBean) {
//        if (StringUtils.isEmpty(forgetPasswordBean.getPhone())) {
//            logger.debug("登录帐号为空");
//            return new LoginResultVo(1, "登录帐号为空");
//        }
//        if (StringUtils.isEmpty(forgetPasswordBean.getCaptcha())) {
//            logger.debug("短信验证码为空");
//            return new LoginResultVo(2, "短信验证码为空");
//        }
//        if (StringUtils.isEmpty(forgetPasswordBean.getPassword())) {
//            logger.debug("新密码为空");
//            return new LoginResultVo(3, "新密码为空");
//        }
//        if (StringUtils.isEmpty(forgetPasswordBean.getConfirmPassword())) {
//            logger.debug("确认新密码为空");
//            return new LoginResultVo(4, "确认新密码为空");
//        }
//
//        if (!forgetPasswordBean.getPassword().equals(forgetPasswordBean.getConfirmPassword())) {
//            return new LoginResultVo(4, "两次密码输入不一致");
//        }
//
//        //如果账户不是顾问类型的账户
//        UserAccountBo userAccount = userAccountService.getByPhone(forgetPasswordBean.getPhone());
//        if (userAccount != null && userAccount.getType() != UserTypeEnum.KEEPER.getValue()) {
//            logger.info("修改密码失败，该手机号不是顾问类型的账号。手机号： " + forgetPasswordBean.getPhone());
//            return new LoginResultVo(1, "非本平台用户");
//        }
//
//        //如果手机号没有注册
//        KeeperUserBo userBo = keeperUserService.getByPhone(forgetPasswordBean.getPhone());
//        if (userBo == null) {
//            logger.info("修改密码失败，未找到用户 , 电话号码为： " + forgetPasswordBean.getPhone());
//            return new LoginResultVo(1, "该手机未注册");
//        }
//
//        //手机验证码
//        if (!validateSmsCaptcha(forgetPasswordBean.getPhone(), forgetPasswordBean.getCaptcha())) {
//            return new LoginResultVo(2, "验证码错误");
//        }
//
//        //验证密码格式 非中文非空格
//        if (forgetPasswordBean.getPassword().contains(" ")) {
//            logger.debug("提交修改密码有空格");
//            return new LoginResultVo(3, "密码不能有空格");
//        }
//        forgetPasswordBean.setPassword(forgetPasswordBean.getPassword());
//        forgetPasswordBean.setConfirmPassword(forgetPasswordBean.getConfirmPassword());
//        try {
//            keeperUserPasswordService.forgetPassword(forgetPasswordBean.getPhone(), forgetPasswordBean.getCaptcha(),
//                    forgetPasswordBean.getPassword(), forgetPasswordBean.getConfirmPassword());
//        } catch (KeeperPasswordException e) {
//            logger.error("resetPassword error : " + e);
//            return new LoginResultVo(4, e.getServiceBusinessCode().getDescription());
//        } catch (Exception e) {
//            logger.error("resetPassword error : " + e);
//            return new LoginResultVo(4, "密码重置失败");
//        }
//        logger.info("密码重置成功");
//        return new LoginResultVo(0);
//    }


//
//
//    /**
//     * 用户注册(ajax)
//     *
//     * @return
//     */
//    @RequestMapping(value = "/registerKeeper")
//    @ResponseBody
//    public LoginResultVo registerKeeper(RegisterBean registerBean, HttpSession session) {
//        //手机号验证
//        if (StringUtils.isEmpty(registerBean.getPhone())) {
//            logger.debug("注册帐号为空");
//            return new LoginResultVo(1, "手机号不可为空");
//        }
//
//        //密码判空
//        if (StringUtils.isEmpty(registerBean.getPassword())) {
//            logger.debug("密码为空");
//            return new LoginResultVo(3, "密码不可为空");
//        }
//
//        if (StringUtils.isEmpty(registerBean.getConfirmPassword())) {
//            logger.debug("确认密码为空");
//            return new LoginResultVo(4, "确认密码不可为空");
//        }
//
//        //密码输入一致性校验
//        if (!registerBean.getPassword().equals(registerBean.getConfirmPassword())) {
//            return new LoginResultVo(4, "两次密码输入不一致");
//        }
//
//        //图片验证码（访刷验证码）
//        if (StringUtils.isEmpty(registerBean.getImgCaptcha()) || !validateCaptcha(session, registerBean.getImgCaptcha())) {
//            logger.debug("图片验证码为空");
//            return new LoginResultVo(5, "验证码错误");
//        }
//
//        //验证手机号是否已注册
//        UserAccountBo userAccount = userAccountService.getByPhone(registerBean.getPhone());
//        if (userAccount != null) {
//            logger.info("注册帐号失败，该手机号已注册。手机号： " + registerBean.getPhone());
//            return new LoginResultVo(1, "该手机号已经注册");
//        }
//
//        KeeperUserBo userBo = keeperUserService.getByPhone(registerBean.getPhone());
//        if (userBo != null) {
//            logger.info("注册帐号失败，该手机号已注册。手机号： " + registerBean.getPhone());
//            return new LoginResultVo(1, "该手机号已经注册");
//        }
//
//        //短信验证码（最后校验！）
//        if (StringUtils.isEmpty(registerBean.getSmCaptcha())) {
//            logger.debug("短信验证码为空");
//            return new LoginResultVo(2, "请输入验证码");
//        }
//
//        if (!validateRegisterSmsCaptcha(registerBean.getPhone(), registerBean.getSmCaptcha())) {
//            logger.debug("短信验证码验证失败");
//            return new LoginResultVo(2, "验证码错误");
//        }
//
//        //开始注册过程：
//        registerBean.setPassword(registerBean.getPassword());
//        registerBean.setConfirmPassword(registerBean.getPassword());
//        /*CpRegisterBean ub = new CpRegisterBean();
//        ub.setPassword(registerBean.getPassword());
//        ub.setConfirmPassword(ub.getPassword());
//        ub.setPhone(registerBean.getPhone());*/
//        com.dongdaodao.user.keeper.bean.RegisterBean registerServiceBean = BeanMapper.map(registerBean, com.dongdaodao.user.keeper.bean.RegisterBean.class);
//        try {
//            //注册IM用户
//            ImUserBo imUserBo = easemobIMUsersService.createNewIMUserSingle();
//            if (imUserBo != null) {
//                registerServiceBean.setImAccount(imUserBo.getUsername());
//                registerServiceBean.setImPassword(imUserBo.getPassword());
//            } else {
//                logger.error("[ERROR] Fail to Register Keeper 'cause Registered IM user is null.");
//                throw new NullPointerException();
//            }
//        } catch (Exception e) {
//            logger.error("[ERROR] Fail to Register Keeper because IM Registration Failed. " + e);
//            return new LoginResultVo(6, "注册失败!");
//        }
//        try {
//            LoginInfoBean loginInfoBean = new LoginInfoBean();
//            loginInfoBean.setSystemVersion("web pc");
//            loginInfoBean.setDeviceName("web");
//            KeeperUserBo newUserBo = keeperUserAuthService.register(registerServiceBean, loginInfoBean);
//
//            //登录
//            session.setAttribute(SessionConstant.USER_ACC, newUserBo);
//            session.setAttribute(SessionConstant.USER_UID, newUserBo.getId());
//        } catch (KeeperUserException e) {
//            logger.error("[ERROR] Fail to Register Keeper!", e);
//            return new LoginResultVo(6, "注册失败!");
//        }
//        return new LoginResultVo(0);
//    }


//
//    @RequestMapping(value = "/verifyPhoneNumber")
//    @ResponseBody
//    public ResultVo verifyPhoneNumber(String phone, String type) {
//        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(type)) {
//            return new ResultVo(false, "手机号不可为空");
//        }
//
//        if ("register".equals(type)) {
//            //如果账户不是顾问类型的账户
//            UserAccountBo userAccount = userAccountService.getByPhone(phone);
//            if (userAccount != null && userAccount.getType() != UserTypeEnum.KEEPER.getValue()) {
//                return new ResultVo(false, "该手机号已经注册");
//            }
//
//            KeeperUserBo userBo = keeperUserService.getByPhone(phone);
//            if (userBo != null) {
//                return new ResultVo(false, "该手机号已经注册");
//            }
//        } else if ("forget".equals(type)) {
//            //如果账户不是顾问类型的账户
//            UserAccountBo userAccount = userAccountService.getByPhone(phone);
//            if (userAccount != null && userAccount.getType() != UserTypeEnum.KEEPER.getValue()) {
//                return new ResultVo(false, "非本平台用户");
//            }
//
//            KeeperUserBo userBo = keeperUserService.getByPhone(phone);
//            if (userBo == null) {
//                return new ResultVo(false, "该手机号未注册");
//            }
//        } else if ("login".equals(type)) {
//            //如果账户不是顾问类型的账户
//            UserAccountBo userAccount = userAccountService.getByPhone(phone);
//            if (userAccount != null && userAccount.getType() != UserTypeEnum.KEEPER.getValue()) {
//                return new ResultVo(false, "该账号无法登陆");
//            }
//
//            KeeperUserBo userBo = keeperUserService.getByPhone(phone);
//            if (userBo == null) {
//                return new ResultVo(false, "该账号未注册");
//            }
//        }
//
//        return new ResultVo(true, "验证成功");
//    }
}
