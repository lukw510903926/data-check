package com.mockuai.data.check.dto;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-22 14:26
 */
public class DataStoreMappingUtils {

    /**
     * 表间的映射
     * 源数据
     */
    private static final Map<String, DataStoreMapping> MAPPING_MAP = Maps.newHashMap();

    private static final Map<String, DataStoreInfo> DATA_STORE_INFO_MAP = new HashMap<>();

    public static String getMappingProperty(String dataStore, String property) {

        return Optional.ofNullable(MAPPING_MAP.get(dataStore)).map(DataStoreMapping::getPropertyMapping).map(mapping -> mapping.get(property)).orElse(null);
    }

    public static DataStoreInfo getDataStoreInfo(String dataStore) {

        return DATA_STORE_INFO_MAP.get(dataStore);
    }

    public static void addDataStoreInfo(String dataStore, DataStoreInfo dataStoreInfo) {

        DATA_STORE_INFO_MAP.put(dataStore, dataStoreInfo);
    }

    public static DataStoreMapping getDataStoreMapping(String dataStore) {
        return MAPPING_MAP.get(dataStore);
    }

    public void addDataStoreMapping(DataStoreMapping dataStoreMapping) {
        MAPPING_MAP.put(dataStoreMapping.getSourceStore(), dataStoreMapping);
        MAPPING_MAP.put(dataStoreMapping.getTargetStore(), dataStoreMapping);
    }
}
