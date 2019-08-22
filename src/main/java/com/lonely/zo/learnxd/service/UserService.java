package com.lonely.zo.learnxd.service;

import com.lonely.zo.learnxd.mapper.UserMapper;
import com.lonely.zo.learnxd.model.User;
import com.lonely.zo.learnxd.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    //此方法已弃用
    public boolean isNotExit(String accountId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            return true;
        }else {
            return false;
        }
    }
    //更新或创建user
    public void createOrUpdata(User user) {
        //根据user的accountId，从数据库中查找User
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users= userMapper.selectByExample(userExample);
        //当users为空，即数据库中不存在此user，则向数据库中插入此user
        if (users.size() == 0) {
            //更新user的创建时间
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //当users不为空，即数据库中存在此user，则更新数据库中此user的相关数据
            User dbuser=users.get(0);
            User updateUser = new User();
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            updateUser.setBio(user.getBio());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}
