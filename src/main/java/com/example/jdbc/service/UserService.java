package com.example.jdbc.service;

import com.example.jdbc.entity.User;
import com.example.jdbc.repository.UserRepository;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
//    public User findUserByKeys(String keys){
//        return
//    };
    //注入UserRepository
    @Resource
    private UserRepository userRepository;

    public Integer insertUser(String login_name,String username,String password){
        return userRepository.insertUser(login_name,username,password);
    }

    public User selectByUsername(String username){
        return userRepository.selectByUsername(username);
    }

    public User findUserById(Integer id){return userRepository.findUserById(id);}

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByKeys(String keys){return userRepository.findByKeys(keys);}

    public List<User> findStu(){return userRepository.findAll();}

    public User insertGetKey(User user){
        return userRepository.insertGetKey(user);
    }

    public void update(String username,String login_name,Integer id){ userRepository.update(username,login_name, id); }

    public void upDate(String username,String login_name,String password,Integer id){ userRepository.upDate(login_name,username,password,id); }

    public void delete(Integer id){
        userRepository.delete(id);
    }
}