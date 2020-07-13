package com.lee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.annotation.ReadDataSource;
import com.lee.dao.UserDao;
import com.lee.entity.User;
import com.lee.service.IUserService;
import com.lee.util.SpringContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Auther: liyiyu
 * @Date: 2020/5/17 15:10
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    private IUserService getService(){
        return SpringContextUtil.getBean(this.getClass());
    }


    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,readOnly=false)
    public int insertUser(User u){
        return userDao.insert(u);

        //如果类上面没有@Transactional,方法上也没有，哪怕throw new RuntimeException,数据库也会成功插入数据
        //	throw new RuntimeException("测试插入事务");
    }


    @Override
    public void wirteAndRead(User u){
        getService().insertUser(u);//这里走写库，那后面的读也都要走写库
        //这是刚刚插入的
        User uu = getService().findById(u.getId());
        System.out.println("==读写混合测试中的读(刚刚插入的)====id="+u.getId()+",  user_name=" + uu.getName());
        //为了测试,3个库中id=1的user_name是不一样的
        User uuu = getService().findById(1);
        System.out.println("==读写混合测试中的读====id=1,  user_name=" + uuu.getName());

    }

    public void readAndWirte(User u){
        //为了测试,3个库中id=1的user_name是不一样的
        User uu = getService().findById(1);
        System.out.println("==读写混合测试中的读====id=1,user_name=" + uu.getName());
        getService().insertUser(u);

    }

    @Override
    public User findById(Integer id){
        User u = userDao.findById(id);
        return u;
    }

    @Transactional
    @Override
    public void insertTest(){

    };


    @ReadDataSource
    public PageInfo<User> queryPage(String userName, int pageNum, int pageSize){
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        //PageHelper会自动拦截到下面这查询sql
        //userDao.query(userName);
        return page.toPageInfo();
    }


}
