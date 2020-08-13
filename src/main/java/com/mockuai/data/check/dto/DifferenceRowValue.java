package com.mockuai.data.check.dto;

import lombok.Data;

import java.util.List;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-04 13:32
 */
@Data
public class DifferenceRowValue {

    private List<DifferenceColumnValue> list;

    private String tableName;

    private String rowKey;
}
