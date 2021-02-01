package com.keji.bookmanage.controller;

import com.keji.bookmanage.contants.ResponseEnum;
import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.BookType;
import com.keji.bookmanage.entity.BorrowRecord;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.service.BookInfoService;
import com.keji.bookmanage.service.BookTypeSevice;
import com.keji.bookmanage.service.BorrowRecordService;
import com.keji.bookmanage.service.SysUserService;
import com.keji.bookmanage.util.ResponseEntity;
import com.keji.bookmanage.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import java.time.LocalDate;
import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午1:55
 */
@Controller
public class BookController {

    @Autowired
    BookInfoService bookInfoService;
    @Autowired
    BookTypeSevice bookTypeSevice;
    @Autowired
    BorrowRecordService borrowRecordService;

    @RequestMapping(value = "/book/list",method = RequestMethod.GET)
    public String bookList(){
        return "admin/booklist";
    }

    @RequestMapping(value = "/book/info",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookInfo(@Param("page") int page,
                            @Param("limit")int limit){
        Page<BookInfo> infos = bookInfoService.findAllByPage(page,limit);
        return ResponseUtil.success(infos.getContent(),infos.getTotalElements());
    }

    @RequestMapping(value = "/book/search",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookSearch(@Param("page") int page,
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
        List<BorrowRecord> records = borrowRecordService.findByBookId(id);
        if(records.size() > 0){
            return ResponseUtil.error("须先删除对应的借阅记录再删除书籍信息");
        }
        bookInfoService.deleteById(id);
        return ResponseUtil.success();
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:delete")
    @RequestMapping(value = "/book/deletebyids",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity deleteByIds(@Param("ids")Long[] ids){
        List<BorrowRecord> records = borrowRecordService.findByBookIds(ids);
        if(records.size() > 0){
            return ResponseUtil.error("须先删除对应的借阅记录再删除书籍信息");
        }
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

    @RequestMapping(value = "/book/borrow",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity borrowBook(@Param("bookname")String bookname,
                                                   @Param("borrowDays")int borrowDays){

        BookInfo bookInfo = bookInfoService.selectByBookname(bookname);
        if(bookInfo.getNumber() < 1){ //判断图书库存量>1
            return ResponseUtil.error("该图书库存量不足,可等用户归还后再借阅!!!");
        }
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<BorrowRecord> recordList = borrowRecordService.findByUsername(user.getUsername());
        if(recordList.size() > 2){//限制同一用户只能同时借三本书
            return ResponseUtil.error("同一用户只能借三本书,请先归还再借阅!!!");
        }
        borrowRecordService.saveAndFlush(bookInfo,user,borrowDays);
        return ResponseUtil.success();
    }
}
