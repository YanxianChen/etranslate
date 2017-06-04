package com.etranslate.controller;

import com.alibaba.fastjson.JSONObject;
import com.etranslate.dao.TranslationDao;
import com.etranslate.dao.UserDao;
import com.etranslate.entity.Translation;
import com.etranslate.entity.User;
import com.etranslate.util.MjStringUtil;
import com.etranslate.util.RespMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yancychan on 17-6-4.
 */
@RestController
@RequestMapping("/translation")
public class TranslationController {

    @Autowired
    TranslationDao translationDao;
    @Autowired
    UserDao userDao;

    //提交译文
    @PostMapping("/submit")
    public JSONObject submitTranslation(@RequestBody JSONObject jsonObject){

        JSONObject jo;

        String username = jsonObject.getString("username");
        String word = jsonObject.getString("word");
        String translateText = jsonObject.getString("translateText");

        User user = userDao.getByUsername(username);

        if (MjStringUtil.isEmpty(translateText)){
            jo = RespMsgUtil.getFailResponseJoWithErrMsg("译文为空");
        }else {
            Translation translation = translationDao.findByUserAndWordAndTranslateText(user,word,translateText);
            if (translation == null){
                translation = new Translation();
                translation.setUser(user);
                translation.setWord(word);
                translation.setTranslateText(translateText);
                translationDao.save(translation);

                jo = RespMsgUtil.getSuccessResponseJo();
            }else {
                jo = RespMsgUtil.getFailResponseJoWithErrMsg("您已提交过该译文");
            }
        }
        return jo;
    }

    //获取其他用户的译文
    @PostMapping("/get")
    public JSONObject getTranslations(@RequestBody JSONObject jsonObject){

        JSONObject jo = null;

        String word = jsonObject.getString("word");

        if (MjStringUtil.isEmpty(word)){
            jo = RespMsgUtil.getFailResponseJoWithErrMsg("单词为空");
        }else {
            Translation translation;
            translation = translationDao.findByWord(word);
            if (translation == null){
                jo = null;
            }else {
//                System.out.println(translation.toString());
//                Object translateText = translation.getTranslateText();
                JSONObject obj = new JSONObject();
                obj.put("translateText",translation.getTranslateText());
                jo = RespMsgUtil.getSuccessResponseJoWithData(obj);
            }
        }
        return jo;
    }

    //点赞
    @PostMapping("/upvote")
    public JSONObject upvoteTranslation(@RequestBody JSONObject jsonObject){

        JSONObject jo;

        String tId = jsonObject.getString("tId");

        Translation translation = translationDao.findByTId(tId);
        int vote = translation.getUpvote();
        translation.setUpvote(vote+1);

        translationDao.save(translation);

        JSONObject obj = new JSONObject();
        obj.put("vote", translation.getUpvote());

        jo = RespMsgUtil.getSuccessResponseJoWithData(obj);

        return jo;
    }
}
