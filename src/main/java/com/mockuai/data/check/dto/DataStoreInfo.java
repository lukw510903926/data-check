package com.mockuai.data.check.dto;

import lombok.Data;

import java.util.List;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-16 15:28
 */
@Data
public class DataStoreInfo {

    /**
     * 表名
     */
    private String tableName;

    private List<String> uniqueColumn;

    private List<String> columnList;
}
