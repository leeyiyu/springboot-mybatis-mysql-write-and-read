package com.lee.service;

import com.lee.entity.User;

/**
 * @Auther: liyiyu
 * @Date: 2020/5/17 14:42
 * @Description:
 */
public interface IUserService {


    int insertUser(User u);

    User findById(Integer id);

    void wirteAndRead(User user);

    void insertTest();
}
