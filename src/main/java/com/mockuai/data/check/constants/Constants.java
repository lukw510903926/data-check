package com.mockuai.data.check.constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-16 16:05
 */
public class Constants {

    /**
     * 分隔符
     */
    public static final String SEPARATOR = ":";

    /**
     * 前缀
     */
    public static final String ROW_KEY_PREFIX = "data:check:prefix:" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + SEPARATOR;

    /**
     * 一分钟
     */
    public static final Long ONE_MINUTES = 60L;

    /**
     * 一小时
     */
    public static final Long ONE_HOUR = 60L;

    public static void main(String[] args) {

        System.out.println(ROW_KEY_PREFIX);
    }
}