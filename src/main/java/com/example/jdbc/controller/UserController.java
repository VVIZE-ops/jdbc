package com.example.jdbc.controller;


import com.example.jdbc.entity.User;
import com.example.jdbc.service.UserService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

/*
    @RequestMapping("/insertUser")
    public String insertUser(String login_name,String username,String password){
        userService.insertUser(login_name,username,password);
        return "插入数据成功";
    }*/

    /**
     * describe:为了实现页面跳转后正常插入数据
     * @return
     */
    @RequestMapping("/insertuser")
    public ModelAndView insertuser(User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students",user);
        modelAndView.setViewName("add");
        return modelAndView;
                //new ModelAndView("redirect:/user/findStu");
    }

    @RequestMapping("/insertUser")
    public ModelAndView insertUser(User user){
        userService.insertUser(user.getLoginName(),user.getUsername(),user.getPassword());
        //model.addAttribute("user",new User());
        return new ModelAndView("redirect:/user/findStu");
    }


    /**
     * describe：主页，展示所有数据，并且有增删改的跳转按钮
     * @return
     */

    @RequestMapping("/findStu")
    public ModelAndView findStu(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> studentList = userService.findAll();
        modelAndView.addObject("students",studentList);
        modelAndView.setViewName("index");
        return modelAndView;

    }
    /*
    @RequestMapping("/findStu")
    public String findStu(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("jdbc",list);
        return "index";

    }*/


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
    @RequestMapping("/update/{id}")
    public void update(@PathVariable String username,String login_name,Integer id){
        userService.update(username,login_name, id);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id){
        userService.delete(id);
        return new ModelAndView("redirect:/user/findStu");
    }
}