package com.mockuai.data.check;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-16 15:31
 */
@Slf4j
@SpringBootApplication
public class DataCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataCheckApplication.class, args);
        log.info(" data check application start successfully");
    }
}
