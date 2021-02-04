package com.keji.bookmanage.controller;

import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.BorrowRecord;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.service.BookInfoService;
import com.keji.bookmanage.service.BorrowRecordService;
import com.keji.bookmanage.service.SysUserService;
import com.keji.bookmanage.util.ResponseEntity;
import com.keji.bookmanage.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther tangguangchuan
 * @date 2021/2/4 上午10:49
 * 查询最近7天的用户,图书,借阅数据
 */
@Controller
public class IndexController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    BookInfoService bookInfoService;
    @Autowired
    BorrowRecordService borrowRecordService;

    @RequestMapping(value = "index/getdata",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getData(ModelMap modelMap){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate now = LocalDate.now().plusDays(-8);
        LocalDateTime start,end;
        //定义数组存储X轴数据
        String[] xData = new String[7];
        //定义数组存储Y轴数据(用户,图书,借阅)
        int[] userYData = new int[7];
        int[] bookYData = new int[7];
        int[] borrowYData = new int[7];
        for (int i = 0; i < 7; i++) {
            now = now.plusDays(1);
            start = LocalDateTime.parse(now + " 00:00:00",df);
            end = LocalDateTime.parse(now + " 23:59:59",df);
            xData[i] = now.getMonthValue()+"-"+now.getDayOfMonth();
            List<SysUser> sysUsers = sysUserService.findByCreateAt(start,end);
            List<BookInfo> bookInfos = bookInfoService.findByCreateAt(start,end);
            List<BorrowRecord> records = borrowRecordService.findByCreateAt(start,end);
            userYData[i] = sysUsers.size();
            bookYData[i] = bookInfos.size();
            borrowYData[i] = records.size();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("xData",xData);
        map.put("userYData",userYData);
        map.put("bookYData",bookYData);
        map.put("borrowYData",borrowYData);
        //查询所有的用户数,图书数,借阅数
        List<SysUser> users = sysUserService.findAll();
        List<BookInfo> bookInfos = bookInfoService.findAll();
        List<BorrowRecord> records = borrowRecordService.findAll();
        map.put("serverTime",df.format(LocalDateTime.now()));
        map.put("userCount",users.size());
        map.put("bookCount",bookInfos.size());
        map.put("borrowCount",records.size());
        return ResponseUtil.success(map);
    }
}
