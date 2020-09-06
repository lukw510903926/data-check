package com.mockuai.data.check.service.monitor;

import com.mockuai.data.check.dto.CheckResult;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020/8/27 23:27
 */
public interface MonitorStrategy {

    /**
     * 监控告警
     *
     * @param checkResult 比对结果
     */
    void monitor(CheckResult checkResult);
}
