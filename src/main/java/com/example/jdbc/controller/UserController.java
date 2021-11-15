package com.example.jdbc.controller;


import com.example.jdbc.dao.UserDao;
import com.example.jdbc.entity.User;
//import com.example.jdbc.service.UserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* 类注解 */
@Api(tags="user_info")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
//    @Resource
//    private UserService userService;
    @Resource
    private UserDao userDao;

    /**
     *
     */
//    @Controller
//    public class HelloController {
//        @RequestMapping(value = {"/","/index.html"})
//        public String index(){
//            return "index";
//        }
//    }
    /**
     * describe：主页，展示所有数据，并且有增删改的跳转按钮
     * @return
     */
    /* 方法注解 */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @GetMapping("/findStu")
    public List<User> findStu(){
        List<User> users = userDao.findAll();
        return users;

    }

    /**
     * 插入数据
     * @param user
     * @return
     */
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PostMapping ("/insertGetKey")
    public Object insertGetKey(User user){
       try {
           userDao.saveUser(user);
           return "insert uccess";
       }catch (Exception e){
           return "Can not input null";
       }
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @ApiOperation(value="删除用户", notes="根据选择的id来指定删除用户")
    @ApiImplicitParam(name = "ids", value = "用户ID", required = true, dataType = "String", paramType = "path")
     @PostMapping("/deletes")
     public Object delete(@RequestParam String ids){
     String[] id=ids.split(",");
     //        System.out.println(ids.length());
     try{
     for(int i=0;i<id.length;i++){
     userDao.deleteUser(Integer.valueOf(id[i]));}
     return "success";}
     catch (Exception e){
     return "Please select at least one";
        }
     }

    /**
     * 更新
     * @param user
     * @return
     */
    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")})
    @PostMapping("/update")
    public Object update( User user) {
        try {
            userDao.updateUser(user);
            //model.addAttribute("user",new User());
            return "Update Success";
        }catch (Exception e){
            return "Can not input null";
        }
    }

    /**判断字符串是否全部为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for(int i = 0; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 查询
     * @param keys
     * @return
     */
    @ApiOperation(value="获取用户详细信息", notes="根据关键词来获取用户详细信息")
    @ApiImplicitParam(name = "keys", value = "关键词", required = true, dataType = "String", paramType = "path")
    @GetMapping("/findByKeys")
    public Object findByKeys(@RequestParam(name = "keys",required = false)  String keys){
        if((keys!=null&&!"".equals(keys))){
            if(isNumeric(keys)){
                try{
                    List<User> usertList= userDao.findById(Integer.valueOf(keys).intValue());
                    return usertList;
                }catch (Exception e){
                    List<User> usertList = userDao.findByName(keys);
                    return usertList;
                }
                //DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
                //Date date = fmt.parse(keys);

            }else{
                List<User> usertList = userDao.findByName(keys);
                return usertList;
            }

//            List<User> usertList = userDao.findByName(keys);
//            return usertList;
        } else{
            return userDao.findAll();}
    }

    @ApiOperation(value="分页显示", notes="分页显示用户信息")
    @GetMapping("/paging")
    public Object getUserPage(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userDao.findAll();
        return list;
    }

//    @GetMapping("/paging")
//    public Object paging(String pagesize, String pageindex){
//        List<User> usertList = userService.findByPage(Integer.parseInt(pagesize),Integer.parseInt(pageindex));
//        return  usertList;
//    }
}