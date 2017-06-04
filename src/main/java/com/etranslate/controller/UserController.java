package com.etranslate.controller;

import com.alibaba.fastjson.JSONObject;
import com.etranslate.dao.UserDao;
import com.etranslate.entity.User;
import com.etranslate.util.MjStringUtil;
import com.etranslate.util.RespMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yancychan on 17-6-4.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserDao userDao;

    //用户注册
    @PostMapping("/register")
    public JSONObject register(@RequestBody JSONObject jsonObject){

        JSONObject jo = null;

        String email = jsonObject.getString("email");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String repassword = jsonObject.getString("repassword");

        if (MjStringUtil.isEmpty(email)||MjStringUtil.isEmpty(username)||
                MjStringUtil.isEmpty(password)||MjStringUtil.isEmpty(repassword)){
            jo = RespMsgUtil.getFailResponseJoWithErrMsg("输入有误");
        }else if(!password.equals(repassword)){
            jo = RespMsgUtil.getFailResponseJoWithErrMsg("密码输入不一致");
        }else {
            User user;
            user = userDao.getByUsername(username);
            if (user == null){
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                userDao.save(user);
                jo = RespMsgUtil.getSuccessResponseJo();
            }else {
                jo = RespMsgUtil.getFailResponseJoWithErrMsg("该用户名已被注册");
            }
        }
        return jo;
    }

    //用户登录
    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject jsonObject){

        JSONObject jo;

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        if (MjStringUtil.isEmpty(username)||MjStringUtil.isEmpty(password)){
            jo = RespMsgUtil.getFailResponseJoWithErrMsg("用户名或密码为空");
        }else{
            User user = userDao.findByUsernameAndPassword(username,password);

            if (user == null){
                jo = RespMsgUtil.getFailResponseJoWithErrMsg("用户名或密码不正确");
            }else {
                user.setPassword(null);
                jo = RespMsgUtil.getSuccessResponseJoWithData(user);
            }
        }
        return jo;
    }
}
