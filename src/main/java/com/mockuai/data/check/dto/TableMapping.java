package com.mockuai.data.check.dto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-03 16:24
 */
@Getter
public enum TableMapping {

    /**
     * 店主收入日统计
     */
    SHOPKEEPER_INCOME("shopkeeper_income_daily_statistics", "finance_shopkeeper_income_daily_statistics"),

    /**
     * 品牌商日统计
     */
    BRAND_INCOME("brand_income_daily_statistics", "finance_brand_income_daily_statistics");

    private String oldTable;

    private String newTable;

    TableMapping(String oldTable, String newTable) {
        this.newTable = newTable;
        this.oldTable = oldTable;
    }

    /**
     * 表list
     */
    private static final List<String> TABLE_LIST = Lists.newArrayList();

    /**
     * 表间的映射
     */
    private static final Map<String, TableMapping> MAPPING_MAP = Maps.newHashMap();

    static {

        for (TableMapping value : TableMapping.values()) {
            MAPPING_MAP.put(value.getNewTable(), value);
            MAPPING_MAP.put(value.getOldTable(), value);
            TABLE_LIST.add(value.getNewTable());
            TABLE_LIST.add(value.getOldTable());
        }
    }

    public static boolean containTable(String table) {

        return TABLE_LIST.contains(table);
    }

    public static TableMapping getTableMapping(String tableName) {
        return MAPPING_MAP.get(tableName);
    }

}