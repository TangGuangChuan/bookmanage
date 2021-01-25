package com.keji.bookmanage.controller;

import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.service.BookInfoService;
import com.keji.bookmanage.util.ResponseEntity;
import com.keji.bookmanage.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午1:55
 */
@Controller
public class BookController {

    @Autowired
    BookInfoService bookInfoService;

    @RequestMapping(value = "/book/list",method = RequestMethod.GET)
    public String bookList(){
        return "admin/booklist";
    }
    @RequestMapping(value = "/book/listinfo",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookPage(){
        List<BookInfo> infoList = bookInfoService.findAll();
        return ResponseUtil.success(infoList);
    }
}
