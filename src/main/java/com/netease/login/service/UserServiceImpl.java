package com.netease.login.service;

import com.netease.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by neo on 2018/2/25.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate mJdbcTemplate;

    public void register(User user) {
        String sql = "INSERT INTO user(account_id, password) VALUES(?,?)";
        mJdbcTemplate.update(sql, user.getAccountId(), user.getPassword());
    }

    public boolean login(User user) {
        String sql = "SELECT count(*) FROM user WHERE account_id=? AND password=?";
        return mJdbcTemplate.queryForObject(sql, Integer.class, new String[]{user.getAccountId(), user.getPassword()}) > 0;
    }

    @Override
    public void resetPassword(User user, String newPassword) {
        // TODO: 2018/2/27 操作数据库，更新密码
        mJdbcTemplate.update("");
    }
}
