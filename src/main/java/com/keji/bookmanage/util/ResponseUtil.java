package com.keji.bookmanage.util;

import com.keji.bookmanage.contants.ResponseEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午4:43
 */
public class ResponseUtil {
        /**
         * 成功返回
         * @param object 返回数据
         * @return
         */
        public static ResponseEntity success(Object object,long count) {
            ResponseEntity resp = new ResponseEntity();
            resp.setCode(ResponseEnum.SUCCESS.getCode());
            resp.setMsg(ResponseEnum.SUCCESS.getMsg());
            resp.setCount(count);
            resp.setData(object);
            return resp;
        }

        /**
         * 成功返回
         * @param object 返回数据
         * @return
         */
        public static ResponseEntity success(Object object) {
            ResponseEntity resp = new ResponseEntity();
            resp.setCode(ResponseEnum.SUCCESS.getCode());
            resp.setMsg(ResponseEnum.SUCCESS.getMsg());
            resp.setData(object);
            return resp;
        }

        /**
         * 成功返回  无数据
         * @return
         */
        public static ResponseEntity success() {
            return success(null);
        }

        /**
         * 失败返回
         * @param responseEnum 响应标识
         * @return
         */
        public static ResponseEntity error(ResponseEnum responseEnum) {
            ResponseEntity resp = new ResponseEntity();
            resp.setCode(responseEnum.getCode());
            resp.setMsg(responseEnum.getMsg());
            return resp;
        }

        /**
         *
         * @param msg
         * @return
         */
        public static  ResponseEntity error(String msg){
            ResponseEntity resp = new ResponseEntity();
            resp.setCode(501);
            resp.setMsg(msg);
            return resp;
        }
}
