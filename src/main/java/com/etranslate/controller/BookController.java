package com.etranslate.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.etranslate.dao.BookDao;
import com.etranslate.dao.UserDao;
import com.etranslate.entity.Book;
import com.etranslate.entity.User;
import com.etranslate.util.RespMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yancychan on 17-6-4.
 */
@RestController
@RequestMapping("/book")
public class BookController extends BaseController {

    @Autowired
    BookDao bookDao;
    @Autowired
    UserDao userDao;

    //显示单词本
    @GetMapping("/list")
    public JSONObject list(){

        JSONObject jo;
//        jo = baseList(bookDao,new Book());

        List<Book> bookList = new ArrayList<Book>();
        bookList = bookDao.findAll();
        JSONArray array = new JSONArray();

        for(Book book:bookList){
            JSONObject obj = new JSONObject();
            obj.put("translateText",book.getTranslateText());
            obj.put("word",book.getWord());
            array.add(obj);
        }

        jo = RespMsgUtil.getSuccessResponseJoWithData(array);
        return jo;
    }

    //加入单词
    @PostMapping("/add")
    public JSONObject addWord(@RequestBody JSONObject jsonObject){
        JSONObject jo = null;

        String username = jsonObject.getString("username");
        String word = jsonObject.getString("word");
        String translateText = jsonObject.getString("translateText");

        User user = userDao.getByUsername(username);
        Book book;
        book = bookDao.findByUsernameAndWord(user,word);
        if (book != null){
            jo = RespMsgUtil.getFailResponseJoWithErrMsg("该单词已在生词本中");
        }else {
            book = new Book();
            book.setUser(user);
            book.setWord(word);
            book.setTranslateText(translateText);

            bookDao.save(book);

//            JSONObject obj = new JSONObject();
//            obj.put("word", book.getWord());
//            obj.put("translateText", book.getTranslateText());
//
//            jo = RespMsgUtil.getSuccessResponseJoWithData(obj);
        }
        return jo;
    }

}
