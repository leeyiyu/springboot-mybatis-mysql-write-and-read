package com.lee.dao;

import com.lee.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: liyiyu
 * @Date: 2020/5/17 14:44
 * @Description:
 */
@Mapper
public interface UserDao {

    int insert(User user);

    int update(User user);

    int delete(Integer id);

    User findById(Integer id);

}
