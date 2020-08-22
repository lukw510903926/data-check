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
     * key dataStore
     * <p>
     * value propertyMapping  ->
     */
    private static final Map<String, Map<String, String>> PROPERTY_MAPPING_MAP = new HashMap<>();

    private static final Map<String, DataStoreInfo> DATA_STORE_INFO_MAP = new HashMap<>();

    public static String getMappingProperty(String dataStore, String property) {

        return Optional.ofNullable(PROPERTY_MAPPING_MAP.get(dataStore)).map(mapping -> mapping.get(property)).orElse(null);
    }

    public static DataStoreInfo getDataStoreInfo(String dataStore) {

        return DATA_STORE_INFO_MAP.get(dataStore);
    }

    public static void addDataStoreInfo(String dataStore, DataStoreInfo dataStoreInfo) {

        DATA_STORE_INFO_MAP.put(dataStore, dataStoreInfo);
    }
}
