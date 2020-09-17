package com.mockuai.data.check.dto;

import com.google.common.collect.Maps;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description : 列映射
 * @since : 2020-08-03 16:24
 */
@Data
public class ColumnMapping implements Serializable {

    private static final long serialVersionUID = 7022597545751002654L;

    private DataStoreMapping storeMapping;

    private Map<String, String> mapping;

    public static ColumnMapping build(DataStoreMapping storeMapping) {

        ColumnMapping columnMapping = new ColumnMapping();
        columnMapping.setStoreMapping(storeMapping);
        return columnMapping;
    }

    public ColumnMapping addMapping(String sourceColumn, String targetColumn) {

        mapping = Optional.ofNullable(this.mapping).orElse(Maps.newHashMap());
        mapping.put(sourceColumn, targetColumn);
        return this;
    }
}
