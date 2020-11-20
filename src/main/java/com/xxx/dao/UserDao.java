package com.xxx.dao;

import com.xxx.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {


    User getUserById(@Param("id") String id);
    User getUserByUsername(@Param("username") String username);

    int addUser(User user);

    int deleteUserById(@Param("id") String id);
    int deleteUserByUsername(@Param("username") String username);

    int modifyUser(User user);

}
