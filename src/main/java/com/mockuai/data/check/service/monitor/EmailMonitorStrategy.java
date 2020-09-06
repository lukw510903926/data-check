package com.mockuai.data.check.service.monitor;

import com.mockuai.data.check.dto.CheckResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description : 邮件告警
 * @since : 2020/8/27 23:28
 */
@Slf4j
@Component
public class EmailMonitorStrategy extends AbstractMonitorStrategy {

    @Override
    public String getName() {
        return MonitorType.EMAIL.name();
    }

    @Override
    public void monitor(CheckResult checkResult) {

    }
}
