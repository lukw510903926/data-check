package com.mockuai.data.check.service.monitor;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-09-06 22:23
 */
public class MonitorHolder {

    private static final Map<String, MonitorStrategy> MONITOR_STRATEGY_MAP = Maps.newConcurrentMap();

    public static void addStrategy(String name, MonitorStrategy strategy) {
        MONITOR_STRATEGY_MAP.put(name, strategy);
    }

    public static MonitorStrategy getStrategy(String name) {
        return MONITOR_STRATEGY_MAP.get(name);
    }
}


