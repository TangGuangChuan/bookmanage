package com.keji.bookmanage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;

@SpringBootTest
class BookmanageApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal(0));
        System.out.println(new BigDecimal(0).subtract(new BigDecimal(1208.16)));
    }
}
