package com.keji.bookmanage.controller;

import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.BookType;
import com.keji.bookmanage.entity.BorrowRecord;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.service.BookInfoService;
import com.keji.bookmanage.service.BookTypeSevice;
import com.keji.bookmanage.service.BorrowRecordService;
import com.keji.bookmanage.util.ResponseEntity;
import com.keji.bookmanage.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
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
public class BookTypeController {

    @Autowired
    BookTypeSevice bookTypeSevice;
    @Autowired
    BookInfoService bookInfoService;

    @RequestMapping(value = "/booktype/list",method = RequestMethod.GET)
    public String bookList(){
        return "admin/booktype";
    }

    @RequestMapping(value = "/booktype/info",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookTypeInfo(@Param("page") int page,
                            @Param("limit")int limit){
        Page<BookType> infos = bookTypeSevice.findAll(page,limit);
        return ResponseUtil.success(infos.getContent(),infos.getTotalElements());
    }

    @RequestMapping(value = "/booktype/search",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity bookTypeSearch(@Param("page") int page,
                            @Param("limit")int limit,
                            @Param("typeCode")String typeCode,
                            @Param("typeName")String typeName){
        Page<BookType> types = bookTypeSevice.search(page,limit,typeCode,typeName);
        return ResponseUtil.success(types.getContent(),types.getTotalElements());
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:creat")
    @RequestMapping(value = "/booktype/add",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity bookTypeAdd(@Param("typeName")String typeName,
                           @Param("typeCode")String typeCode){
        BookType bookType = bookTypeSevice.findByTypeCode(typeCode);
        if(bookType != null){
            return ResponseUtil.error("该类型编码已存在,请确认后再添加");
        }
        BookType newBookType = new BookType();
        newBookType.setTypeCode(typeCode);
        newBookType.setTypeName(typeName);
        bookTypeSevice.saveAndFlush(newBookType);
        return ResponseUtil.success();
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:delete")
    @RequestMapping(value = "/booktype/deletebyid",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity deleteById(@Param("id")Long id){
        List<BookInfo> infoList = bookInfoService.findByType_id(id);
        if(infoList.size() > 0){
            return ResponseUtil.error("该类型下存在书籍,请先删除书籍信息");
        }
        bookTypeSevice.deleteById(id);
        return ResponseUtil.success();
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:delete")
    @RequestMapping(value = "/booktype/deletebyids",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity deleteByIds(@Param("ids")Long[] ids){
        List<BookInfo> infoList = bookInfoService.findByType_ids(ids);
        if(infoList.size() > 0){
            return ResponseUtil.error("请先删除对应类型下的所有书籍");
        }
        bookTypeSevice.deleteByIds(ids);
        return ResponseUtil.success();
    }
}
