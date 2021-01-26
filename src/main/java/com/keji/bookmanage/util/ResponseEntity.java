package com.keji.bookmanage.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午4:32
 */
@Setter
@Getter
public class ResponseEntity implements Serializable {

    private Integer code;
    private String msg;
    private long count;
    private Object data;

}
