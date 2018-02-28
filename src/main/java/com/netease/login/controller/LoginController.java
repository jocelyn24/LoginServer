package com.netease.login.controller;

import com.netease.login.entity.request.User;
import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.response.LoginResult;
import com.netease.login.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by neo on 2018/2/20.
 */
@Controller
@ComponentScan()
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getSimpleName());

    @Autowired
    UserServiceImpl mUserService;

    @GetMapping("/login")
    public String go2LoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value = "/login")
    public @ResponseBody BaseResponse<LoginResult> realLogin(@ModelAttribute User user) {
        BaseResponse<LoginResult> response = new BaseResponse<>();
        if (null == user) {
            LOG.error(user.getAccountId());
            response.setCode("-1");
            return response;
        }
        LOG.info(user.getAccountId() + " : " + user.getPassword());
        response.setCode("200");
        LoginResult result = new LoginResult();
        if (mUserService.login(user)) {
            LOG.info("login success.");
            result.setSuccess(true);
            result.setUrl("/login_success");
        } else {
            LOG.error("login failed.");
            result.setSuccess(false);
            result.setDesc("用户名/密码不正确");
        }
        response.setEntity(result);
        return response;
    }

//    @PostMapping(value = "/login")
//    public void realLogin(@ModelAttribute User user) {
//        if (mUserService.login(user)) {
//            LOG.info("login success!");
//        } else {
//            LOG.info("login failed!");
//        }
//    }

    @GetMapping(value = "/login_success")
    public String loginSuccess() {
        return "login_success";
    }
}
