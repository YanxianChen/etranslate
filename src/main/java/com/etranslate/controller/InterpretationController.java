package com.etranslate.controller;

import com.alibaba.fastjson.JSONObject;
import com.etranslate.dao.InterpretationDao;
import com.etranslate.dao.UserDao;
import com.etranslate.entity.Interpretation;
import com.etranslate.entity.Translation;
import com.etranslate.entity.User;
import com.etranslate.util.RespMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yancychan on 17-6-4.
 */
@RestController
@RequestMapping("/interpretation")
public class InterpretationController {

    @Autowired
    InterpretationDao interpretationDao;
    @Autowired
    UserDao userDao;

//    //获取上次使用的译文
//    @PostMapping("/history")
//    public JSONObject getHistoricalInterpretation(@RequestBody JSONObject jsonObject){
//        JSONObject jo;
//
//        String username = jsonObject.getString("username");
//        String url = jsonObject.getString("url");
//        String word = jsonObject.getString("word");
//
//        User user = userDao.getByUsername(username);
//
//        Interpretation interpretation;
//        interpretation = interpretation.findByUserAndWordAndUrl(user,url,word);
//
//        if (interpretation == null){
//            jo = null;
//        }else {
//            JSONObject obj = new JSONObject();
//            obj.put("translateText", interpretation.getTranslateText());
//            jo = RespMsgUtil.getSuccessResponseJoWithData(obj);
//        }
//        return jo;
//    }
}
