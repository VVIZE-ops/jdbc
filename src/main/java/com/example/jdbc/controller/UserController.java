package com.example.jdbc.controller;


import com.example.jdbc.entity.User;
import com.example.jdbc.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    /**
    * describe:查询后页面，展示命中的数据，并且可以返回到主页
    *
    * */
    @RequestMapping("/findKeys/{keys}")
    public ModelAndView findKeys(@PathVariable String keys){
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> studentList = userService.findByKeys(keys);
        modelAndView.addObject("students",studentList);
        modelAndView.setViewName("findKey");
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

    @RequestMapping("/selectByUsername/{name}")
    public User selectByUsername(@PathVariable String username){
        return userService.selectByUsername(username);
    }

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }


//    未实现
//    @RequestMapping("/findUserById/{id}")
//    public User findUserById(@PathVariable Integer id){
//        return userService.findUserById(id);
//    }

    /**
     * 模糊查询
     *
     * @return
     * **/
    @RequestMapping("/findByKeys/{keys}")
    public Object findByKeys(@PathVariable String keys){
         if(keys!=null){
             return userService.findByKeys(keys);
         }
         else {
             return new ModelAndView("redirect:/user/findStu");
         }
    }
/*
    @RequestMapping("/update")
    public String update(User user){
        userService.update(user);
        return "修改成功";
    }*/

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
    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id){
        userService.delete(id);
        return new ModelAndView("redirect:/user/findStu");
    }


    @RequestMapping("/deletes/{ids}")
    public ModelAndView delete(@PathVariable String ids){
        String[] id=ids.split(",");
        System.out.println(ids.length());
        for(int i=0;i<id.length;i++){
            userService.delete(Integer.valueOf(id[i]));}
        return new ModelAndView("redirect:/user/findStu");
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