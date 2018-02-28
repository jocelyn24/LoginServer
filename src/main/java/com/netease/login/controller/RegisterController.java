package com.netease.login.controller;

import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.request.User;
import com.netease.login.entity.response.RegResult;
import com.netease.login.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by neo on 2018/2/20.
 */
@Controller
public class RegisterController {

    private static final Logger LOG = Logger.getLogger(RegisterController.class.getSimpleName());

    @Autowired
    UserServiceImpl mUserService;

    @GetMapping("/register")
    public String register(@ModelAttribute User user) {

        return "register";
    }

    @PostMapping("/register")
    public @ResponseBody BaseResponse<RegResult> register_submit(@ModelAttribute User user) {
        LOG.info(user.getAccountId());
        LOG.info(user.getPassword());
        BaseResponse<RegResult> response = new BaseResponse<>();
        if (null == user) {
            LOG.error(user.getAccountId());
            response.setCode("-1");
            return response;
        }
        LOG.info(user.getAccountId() + " : " + user.getPassword());
        response.setCode("200");
        RegResult result = new RegResult();
        if (mUserService.register(user)) {
            LOG.info("register success.");
            result.setSuccess(true);
            result.setUrl("/login");
        } else {
            LOG.error("register failed.");
            result.setSuccess(false);
            result.setExist(true);
            result.setDesc("用户名已存在");
        }
        response.setEntity(result);
        return response;

    }
}
