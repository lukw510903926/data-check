package com.mockuai.data.check.service.impl;

import com.mockuai.data.check.service.MonitorStrategy;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractMonitorStrategy implements MonitorStrategy, InitializingBean {

    public abstract String getName();

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
