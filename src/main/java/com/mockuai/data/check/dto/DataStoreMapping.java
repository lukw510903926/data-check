package com.mockuai.data.check.dto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-03 16:24
 */
@Data
public class DataStoreMapping {

    /**
     * 表list
     */
    private static final List<String> DATA_STORE_LIST = Lists.newArrayList();

    /**
     * 表间的映射
     */
    private static final Map<String, DataStoreMapping> MAPPING_MAP = Maps.newHashMap();

    private String sourceStore;

    private String targetStore;

    public static DataStoreMapping getDataStoreMapping(String dataStore) {
        return MAPPING_MAP.get(dataStore);
    }

}
