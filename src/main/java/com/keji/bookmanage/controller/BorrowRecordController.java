package com.keji.bookmanage.controller;

import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.BookType;
import com.keji.bookmanage.entity.BorrowRecord;
import com.keji.bookmanage.service.BookInfoService;
import com.keji.bookmanage.service.BookTypeSevice;
import com.keji.bookmanage.service.BorrowRecordService;
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
    BorrowRecordService borrowRecordService;

    @RequestMapping(value = "/borrow/list",method = RequestMethod.GET)
    public String bookList(){
        return "admin/borrowlist";
    }

    @RequestMapping(value = "/borrow/info",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookInfo(@Param("page") int page,
                            @Param("limit")int limit){
        Page<BorrowRecord> records = borrowRecordService.findAllByPage(page,limit);
        return ResponseUtil.success(records.getContent(),records.getTotalElements());
    }

    @RequestMapping(value = "/borrow/search",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookInfo(@Param("page") int page,
                            @Param("limit")int limit,
                            @Param("bookname")String bookname,
                            @Param("username")String username,
                            @Param("status")String status){
        Page<BorrowRecord> infos = borrowRecordService.searchBorrow(page,limit,bookname,username,status);
        return ResponseUtil.success(infos.getContent(),infos.getTotalElements());
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:delete")
    @RequestMapping(value = "/borrow/deletebyids",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity deleteByIds(@Param("ids")Long[] ids){
        borrowRecordService.deleteByIds(ids);
        return ResponseUtil.success();
    }

//    @RequiresRoles("admin")
//    @RequiresPermissions("admin:update")
//    @RequestMapping(value = "/book/updatebyid",method = RequestMethod.POST)
//    public @ResponseBody
//    ResponseEntity updateById(@Param("id")Long id,
//                              @Param("bookname")String bookname,
//                              @Param("auther")String auther,
//                              @Param("introduce")String introduce,
//                              @Param("number")int number,
//                              @Param("type")String type){
//        BookInfo bookInfo = bookInfoService.selectByBookname(bookname);
//        if(bookInfo != null && id != bookInfo.getId()){
//            return ResponseUtil.error("该书名已存在");
//        }
//        if(!type.equals(bookInfo.getBookType().getTypeCode())){
//            BookType bookType = bookTypeSevice.findByTypeCode(type);
//            bookInfo.setBookType(bookType);
//        }
//        bookInfo.setAuther(auther);
//        bookInfo.setBookname(bookname);
//        bookInfo.setIntroduce(introduce);
//        bookInfo.setNumber(number);
//        bookInfoService.updateById(bookInfo);
//        return ResponseUtil.success();
//    }
}
