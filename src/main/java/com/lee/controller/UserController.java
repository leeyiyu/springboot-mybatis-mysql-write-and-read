package com.lee.controller;

import com.lee.entity.User;
import com.lee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: liyiyu
 * @Date: 2020/5/17 18:41
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 测试插入
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public int add(@RequestBody User user){
        return this.userService.insertUser(user);
    }

    /**
     *
     * 功能描述:测试写并读
     *
     * @param:
     * @return:
     * @auther: liyiyu
     * @date: 2020/5/18 10:30 上午
     */
    @RequestMapping("/wirteAndRead")
    @ResponseBody
    public void wirteAndRead(@RequestBody User user){
         this.userService.wirteAndRead(user);
    }


    /**
     * 测试读
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    @ResponseBody
    public User findById(@PathVariable("id") Integer id){
        User user = this.userService.findById(id);
        return user;
    }


}
