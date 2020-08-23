package com.mockuai.data.check.strategy;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:25
 */
public class DataCheckStrategyHolder {

    private DataCheckStrategyHolder() {
    }

    private static final Map<String, DataCheckStrategy> CHECK_STRATEGY_MAP = new ConcurrentHashMap<>();

    public static void addStrategy(String strategy, DataCheckStrategy dataCheckStrategy) {
        CHECK_STRATEGY_MAP.put(strategy, dataCheckStrategy);
    }

    public static DataCheckStrategy getStrategy(String strategy) {
        return CHECK_STRATEGY_MAP.get(strategy);
    }

    public static List<DataCheckStrategy> getStrategyList() {
        return Lists.newArrayList(CHECK_STRATEGY_MAP.values());
    }
}
