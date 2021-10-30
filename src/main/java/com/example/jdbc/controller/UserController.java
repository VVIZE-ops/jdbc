package com.example.jdbc.controller;


import com.example.jdbc.entity.User;
import com.example.jdbc.service.UserService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
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
    @GetMapping ("/insertuser")
    public Object insertuser(User user){
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

    @GetMapping("/findStu")
    public List<User> findStu(){
        List<User> studentList = userService.findAll();
        return studentList;

    }
    /*
    @RequestMapping("/findStu")
    public String findStu(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("jdbc",list);
        return "index";

    }*/


    @PostMapping ("/insertGetKey")
    public User insertGetKey(User user){
        return userService.insertGetKey(user);
    }

    @GetMapping("/selectByUsername")
    public User selectByUsername(String username){
        return userService.selectByUsername(username);
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findUserById")
    public User findUserById(Integer id){
        return userService.findUserById(id);
    }
/*
    @RequestMapping("/update")
    public String update(User user){
        userService.update(user);
        return "修改成功";
    }*/

/**
 *
 * */

    @GetMapping("/findByKeys")
    public Object findByKeys(@RequestParam(name = "keys",required = false)  String keys){
        return userService.findByKeys(keys);
    }
    /**
     * describe:修改数据
     * @param:username
     * @param:login_name
     * @param:id
     */
    @RequestMapping("/upDate")
    public ModelAndView upDate(User user) {
        userService.upDate(user.getUsername(),user.getLoginName(),user.getPassword(),user.getId());
        //model.addAttribute("user",new User());
        return new ModelAndView("redirect:/user/findStu");
    }

    @RequestMapping("/update/{id}")
    public ModelAndView update(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(id);
        modelAndView.addObject("students", user);
        modelAndView.setViewName("update");
        return modelAndView;
    }


    /**
     * describe:删除
     */
    @PostMapping("/delete/{id}")
    public Object delete(@PathVariable Integer id){
        userService.delete(id);
       return "success";
    }


    @PostMapping("/deletes/{ids}")
    public Object delete(@PathVariable String ids){
        String[] id=ids.split(",");
        System.out.println(ids.length());
        for(int i=0;i<id.length;i++){
            userService.delete(Integer.valueOf(id[i]));}
        return "success";
    }


    /**
     * */
//    for(int i=0;i<id.length;i++){
//        try{
//            userService.delete(Integer.valueOf(id[i]));
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("不存在id为"+id[i]+"的数据");
//        }
//    }

//    @RequestMapping(value ="/Delete", method = RequestMethod.POST)
//    @ResponseBody
//    public String deleteApp(HttpServletRequest request) {
//        //logger.info("Delete info from table.");
//        String ids= request.getParameter("ids");//获取id
//        String[] id=ids.split(",");
//        System.out.println(ids.length());
//        for(int i=0;i<id.length;i++){
//            userService.delete(Integer.valueOf(id[i]));}
//        return "success";
//    }
//    @ResponseBody
//    @PutMapping("/Delete/{ids}")
//    private String Test(@PathVariable String ids){
//        String[] id=ids.split(",");
//        System.out.println(ids.length());
//        for(int i=0;i<id.length;i++){
//            userService.delete(Integer.valueOf(id[i]));}
//        return "success";
//    }
//
//


}