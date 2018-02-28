package com.netease.login.controller;

import com.netease.login.entity.base.BaseResponse;
import com.netease.login.entity.response.ResetResult;
import com.netease.login.entity.request.User;
import com.netease.login.service.UserServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by neo on 2018/2/24.
 */
@Controller
public class ResetPasswordController {

    private static final Logger LOG = Logger.getLogger(ResetPasswordController.class.getSimpleName());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserServiceImpl mUserService;

    @GetMapping("reset_password")
    public String resetPassword() {
        return "reset_password";
    }

    @PostMapping("reset_password")
    public @ResponseBody BaseResponse<ResetResult> resetPassword(@ModelAttribute User user) {
        LOG.info(user.getAccountId());
        LOG.info(user.getPassword());
        LOG.info(user.getNewPassword());
        BaseResponse<ResetResult> response = new BaseResponse<>();
        ResetResult result = new ResetResult();
        if (user == null) {
            LOG.error(user.getAccountId());
            response.setCode("-1");
            return response;
        }
        LOG.info("原密码："+ user.getPassword()+"新密码："+user.getNewPassword());
        response.setCode("200");
        if(mUserService.resetPassword(user) == "1"){
            LOG.info("resetPassword success");
            result.setSuccess(true);
            result.setUrl("/login");
        }
        if (mUserService.resetPassword(user) == "0"){
            LOG.info("resetPassword failed");
            result.setSuccess(false);
            result.setDesc("用户名或密码不正确");

        }
        response.setEntity(result);
        return response;

    }
}
