package com.mockuai.data.check.dto;

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
     * key tableName
     * <p>
     * value columnMapping  ->
     */
    private static final Map<String, Map<String, String>> COLUMN_MAPPING_MAP = new HashMap<>();

    private static final Map<String, DataStoreInfo> TABLE_INFO_MAP = new HashMap<>();

    public static String getMappingProperty(String dataStore, String property) {

        return Optional.ofNullable(COLUMN_MAPPING_MAP.get(dataStore)).map(mapping -> mapping.get(property)).orElse(null);
    }

    public static DataStoreInfo getTableInfo(String dataStore) {

        return TABLE_INFO_MAP.get(dataStore);
    }

    public static void addTableInfo(String dataStore, DataStoreInfo dataStoreInfo) {

        TABLE_INFO_MAP.put(dataStore, dataStoreInfo);
    }
}
