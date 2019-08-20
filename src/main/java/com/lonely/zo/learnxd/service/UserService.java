package com.lonely.zo.learnxd.service;

import com.lonely.zo.learnxd.mapper.UserMapper;
import com.lonely.zo.learnxd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ZO
 * @Description:
 * @Date: Create in 16:56 2019/8/15
 * @Modified By:
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //判断数据库中是否存在形同token的user
    public boolean isNotExit(String accountId) {
        User user=userMapper.findByAccountId(accountId);
        if (user==null){
            return true;
        }else {
            return false;
        }
    }

    public void createOrUpdata(User user) {
        User dbuser=userMapper.findByAccountId(user.getAccountId());
        if (dbuser == null) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setGmtModified(System.currentTimeMillis());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.update(dbuser);
        }
    }
}