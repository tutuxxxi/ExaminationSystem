package com.xxx.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xxx.dao.UserDao;
import com.xxx.pojo.User;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    public User getUser(String id, String username) throws Exception {
        if(!StringUtils.isEmpty(id)){
            return userDao.getUserById(id);
        }
        if(!StringUtils.isEmpty(username)){
            return userDao.getUserByUsername(username);
        }

        throw new Exception(" id 和 username 应该至少有一个不为空!");
    }

    public boolean addUser(User user) {
        if(user != null){
            user.setRole(user.getRole() == null ? "学生" : "老师");
            return userDao.addUser(user) != 0;
        }
        return false;
    }
}
