package com.mockuai.data.check.service.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description : 通知告警
 * @since : 2020/8/27 23:28
 */
@Slf4j
@Component
public class AlertMonitorStrategy extends AbstractMonitorStrategy {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void monitor(Object object) {

    }
}