package com.xxx.service;

import com.xxx.pojo.User;

public interface UserService {

    /**
     * 以任意一个参数查询user
     *  当两个参数均有时，默认使用第一个id参数
     * @param id
     * @param username
     * @return
     */
    User getUser(String id, String username) throws Exception;

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);


}
