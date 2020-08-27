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

    /**
     * 数据源基本信息
     */
    private static final Map<String, DataStoreInfo> DATA_STORE_INFO_MAP = new HashMap<>();

    /**
     * 数据比对配置
     */
    private static final Map<String, DataCheckConfig> DATA_CHECK_CONFIG_MAP = new HashMap<>();

    /**
     * 获取映射的属性
     *
     * @param dataStore
     * @param property
     * @return
     */
    public static String getMappingProperty(String dataStore, String property) {

        return Optional.ofNullable(MAPPING_MAP.get(dataStore)).map(DataStoreMapping::getPropertyMapping).map(mapping -> mapping.get(property)).orElse(null);
    }

    /**
     * 获取数据源信息
     *
     * @param dataStore
     * @return
     */
    public static DataStoreInfo getDataStoreInfo(String dataStore) {

        return DATA_STORE_INFO_MAP.get(dataStore);
    }

    /**
     * 添加数据源信息
     *
     * @param dataStore
     * @param dataStoreInfo
     */
    public static void addDataStoreInfo(String dataStore, DataStoreInfo dataStoreInfo) {

        DATA_STORE_INFO_MAP.put(dataStore, dataStoreInfo);
    }

    /**
     * 获取数据源映射关系
     *
     * @param dataStore
     * @return
     */
    public static DataStoreMapping getDataStoreMapping(String dataStore) {
        return MAPPING_MAP.get(dataStore);
    }

    /**
     * 添加数据源映射关系
     *
     * @param dataStoreMapping
     */
    public static void addDataStoreMapping(DataStoreMapping dataStoreMapping) {
        MAPPING_MAP.put(dataStoreMapping.getSourceStore(), dataStoreMapping);
        MAPPING_MAP.put(dataStoreMapping.getTargetStore(), dataStoreMapping);
    }

    /**
     * 是否包含某一数据源
     *
     * @param dataStore
     * @return
     */
    public static boolean containDataStore(String dataStore) {

        return MAPPING_MAP.containsKey(dataStore);
    }

    /**
     * 添加比对配置
     *
     * @param dataCheckConfig
     */
    public static void addDataCheckConfig(DataCheckConfig dataCheckConfig) {
        DataStoreMapping dataStoreMapping = dataCheckConfig.getDataStoreMapping();
        DATA_CHECK_CONFIG_MAP.put(dataStoreMapping.getSourceStore(), dataCheckConfig);
        DATA_CHECK_CONFIG_MAP.put(dataStoreMapping.getTargetStore(), dataCheckConfig);
    }

    /**
     * 获取比对配置信息
     *
     * @param dataStore
     * @return
     */
    public static DataCheckConfig getDataCheckConfig(String dataStore) {
        return DATA_CHECK_CONFIG_MAP.get(dataStore);
    }
}
