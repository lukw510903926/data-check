package com.mockuai.data.check.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-23 19:35
 */
public class DateUtils {

    private DateUtils() {
    }

    public static final String DEFAULT_DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    /**
     * @param timestamp 时间戳
     * @return string
     */
    public static String formatDate(long timestamp) {

        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER));
    }
}
