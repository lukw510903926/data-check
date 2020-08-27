package com.mockuai.data.check.service.monitor;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020/8/27 23:29
 */
public abstract class AbstractMonitorStrategy implements MonitorStrategy, InitializingBean {

    /**
     * 策略名称
     *
     * @return
     */
    public abstract String getName();

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
