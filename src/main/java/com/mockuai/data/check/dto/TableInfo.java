package com.mockuai.data.check.dto;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-16 15:28
 */
@Getter
public enum TableInfo {

    /**
     * 工单
     */
    BIZ_INFO("esflow.biz_info", Lists.newArrayList("work_num"));

    /**
     * 表名
     */
    private String tableName;

    private List<String> uniqueColumn;

    TableInfo(String tableName, List<String> uniqueColumn) {
        this.tableName = tableName;
        this.uniqueColumn = uniqueColumn;
    }

    private static final Map<String, TableInfo> TABLE_MAP = new HashMap<>();

    static {
        Arrays.stream(TableInfo.values()).forEach(table -> TABLE_MAP.put(table.getTableName(), table));
    }

    public static TableInfo getTableInfo(String tableName) {

        return TABLE_MAP.get(tableName);
    }
}
