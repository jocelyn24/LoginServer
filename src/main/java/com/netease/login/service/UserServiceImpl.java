package com.netease.login.service;

import com.netease.login.entity.request.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by neo on 2018/2/25.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate mJdbcTemplate;

    public boolean register(User user) {
        String sql = "INSERT INTO user(account_id, password) VALUES(?,?)";
//        String sql2 = "SELECT count(*) FROM user WHERE account_id=? ";
        try {
            mJdbcTemplate.update(sql, user.getAccountId(), user.getPassword());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
//        if (mJdbcTemplate.queryForObject(sql2,Integer.class,user.getAccountId()) > 0) {
//            return false;
//        }else {
//            mJdbcTemplate.update(sql, user.getAccountId(), user.getPassword());
//        }
//        return mJdbcTemplate.queryForObject(sql2,Integer.class,user.getAccountId()) > 0;
    }

    public boolean login(User user) {
        String sql = "SELECT count(*) FROM user WHERE account_id=? AND password=?";
        return mJdbcTemplate.queryForObject(sql, Integer.class, new String[]{user.getAccountId(), user.getPassword()}) > 0;
    }

    @Override
    public String resetPassword(User user) {
        // TODO: 2018/2/27 操作数据库，更新密码
        String sql1 = "SELECT count(*) FROM user WHERE account_id=? AND password=?";
        String sql2 = "UPDATE user SET password=? WHERE account_id =?";
        if (mJdbcTemplate.queryForObject(sql1,Integer.class, new String[]{user.getAccountId(), user.getPassword()}) > 0 ){
            mJdbcTemplate.update(sql2,String.class,new String[]{user.getNewPassword(), user.getAccountId()});
            return "1";
        }else {
            return "0";
        }

    }
}
