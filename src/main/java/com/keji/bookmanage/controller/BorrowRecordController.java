package com.keji.bookmanage.controller;

import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.BookType;
import com.keji.bookmanage.service.BookInfoService;
import com.keji.bookmanage.service.BookTypeSevice;
import com.keji.bookmanage.util.ResponseEntity;
import com.keji.bookmanage.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
public class BorrowRecordController {

    @Autowired
    BookInfoService bookInfoService;
    @Autowired
    BookTypeSevice bookTypeSevice;

    @RequestMapping(value = "/borrow/list",method = RequestMethod.GET)
    public String bookList(){
        return "admin/borrowlist";
    }

    @RequestMapping(value = "/borrow/info",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookInfo(@Param("page") int page,
                            @Param("limit")int limit){
        Page<BookInfo> infos = bookInfoService.findAllByPage(page,limit);
        return ResponseUtil.success(infos.getContent(),infos.getTotalElements());
    }

    @RequestMapping(value = "/book/search",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookInfo(@Param("page") int page,
                            @Param("limit")int limit,
                            @Param("bookname")String bookname,
                            @Param("auther")String auther,
                            @Param("type")String type){
        Page<BookInfo> infos = bookInfoService.serchBook(page,limit,bookname,auther,type);
        return ResponseUtil.success(infos.getContent(),infos.getTotalElements());
    }

    @RequestMapping(value = "/book/gettype",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getType(){
        List<BookType> types = bookTypeSevice.findAll();
        return ResponseUtil.success(types);
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:creat")
    @RequestMapping(value = "/book/add",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity bookAdd(@Param("bookname")String bookname,
                           @Param("auther")String auther,
                           @Param("type")String type,
                           @Param("introduce")String introduce,
                           @Param("number")int number){
        BookType bookType = bookTypeSevice.findByTypeCode(type);
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookname(bookname);
        bookInfo.setAuther(auther);
        bookInfo.setIntroduce(introduce);
        bookInfo.setNumber(number);
        bookInfo.setBookType(bookType);
        bookInfoService.saveAndFlush(bookInfo);
        return ResponseUtil.success();
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:delete")
    @RequestMapping(value = "/book/deletebyid",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity deleteById(@Param("id")Long id){
        bookInfoService.deleteById(id);
        return ResponseUtil.success();
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:delete")
    @RequestMapping(value = "/book/deletebyids",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity deleteByIds(@Param("ids")Long[] ids){
        bookInfoService.deleteByIds(ids);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/book/selectbyid",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity selectById(@Param("id")Long id){
        BookInfo bookInfo = bookInfoService.selectById(id);
        return ResponseUtil.success(bookInfo);
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:update")
    @RequestMapping(value = "/book/updatebyid",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity updateById(@Param("id")Long id,
                              @Param("bookname")String bookname,
                              @Param("auther")String auther,
                              @Param("introduce")String introduce,
                              @Param("number")int number,
                              @Param("type")String type){
        BookInfo bookInfo = bookInfoService.selectByBookname(bookname);
        if(bookInfo != null && id != bookInfo.getId()){
            return ResponseUtil.error("该书名已存在");
        }
        if(!type.equals(bookInfo.getBookType().getTypeCode())){
            BookType bookType = bookTypeSevice.findByTypeCode(type);
            bookInfo.setBookType(bookType);
        }
        bookInfo.setAuther(auther);
        bookInfo.setBookname(bookname);
        bookInfo.setIntroduce(introduce);
        bookInfo.setNumber(number);
        bookInfoService.updateById(bookInfo);
        return ResponseUtil.success();
    }
}
