package com.mockuai.data.check.dto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mockuai.data.check.util.DataCheckException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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
    private static final List<String> TABLE_LIST = Lists.newArrayList();

    /**
     * 表间的映射
     */
    private static final Map<String, DataStoreMapping> MAPPING_MAP = Maps.newHashMap();

    private String sourceStore;

    private String targetStore;

    public DataStoreMapping(String sourceStore, String targetStore) {

        if (StringUtils.isEmpty(sourceStore) || StringUtils.isEmpty(targetStore)) {
            throw new DataCheckException("sourceStore | targetStore can not be null");
        }
        if (sourceStore.equalsIgnoreCase(targetStore)) {
            throw new DataCheckException("sourceStore && targetStore 不可相同");
        }
        this.sourceStore = sourceStore;
        this.targetStore = targetStore;
        MAPPING_MAP.put(sourceStore, this);
        MAPPING_MAP.put(targetStore, this);
    }

    public static DataStoreMapping getTableMapping(String dataStore) {
        return MAPPING_MAP.get(dataStore);
    }

}
