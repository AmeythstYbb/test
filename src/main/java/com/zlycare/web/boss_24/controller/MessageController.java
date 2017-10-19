package com.zlycare.web.boss_24.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author : LinGuoDong
 * Create : 2016/4/1 17:48
 * Update : 2016/4/1 17:48
 * Descriptions :
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);


    /**
     * 平台消息 - 左侧菜单栏点击
     * @return
     */
    @RequestMapping(value = "/platformMessage")
    @ResponseBody
    public ModelAndView platformMessage() {
        System.out.println("1111111111");
        /**
         * do
         */
        return new ModelAndView("platformMessage");
    }

    /**
     * 订单消息 - 左侧菜单栏点击
     *
     * @return
     */
    @RequestMapping(value = "/orderMessage")
    @ResponseBody
    public ModelAndView orderMessage() {
        /**
         * do
         */
        return new ModelAndView("orderMessage");
    }

    /**
     * 提现消息 - 左侧菜单栏点击
     *
     * @return
     */
    @RequestMapping(value = "/withdrawalMessage")
    @ResponseBody
    public ModelAndView withdrawalMessage() {
        /**
         * do
         */
        return new ModelAndView("withdrawalMessage");
    }
}
