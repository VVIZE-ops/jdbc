package com.example.jdbc.controller;


import com.example.jdbc.entity.User;
import com.example.jdbc.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/insertUser")
    public String insertUser(String login_name,String username,String password){
        userService.insertUser(login_name,username,password);
        return "插入数据成功";
    }

    @RequestMapping("/insertGetKey")
    public User insertGetKey(User user){
        return userService.insertGetKey(user);
    }

    @RequestMapping("/selectByUsername")
    public User selectByUsername(String username){
        return userService.selectByUsername(username);
    }

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/findUserById")
    public User findUserById(Integer id){
        return userService.findUserById(id);
    }
/*
    @RequestMapping("/update")
    public String update(User user){
        userService.update(user);
        return "修改成功";
    }*/
    @RequestMapping("/update")
    public void update(String username,String login_name,Integer id){
        userService.update(username,login_name, id);
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        userService.delete(id);
        return "删除成功";
    }
}