package com.mockuai.data.check.strategy;

import com.google.common.collect.Lists;
import com.mockuai.data.check.dto.DataStoreInfo;
import com.mockuai.data.check.dto.DataStoreMapping;
import com.mockuai.data.check.dto.DataStoreMappingUtils;
import com.mockuai.data.check.dto.DifferencePropertyValue;
import com.mockuai.data.check.dto.EventData;
import com.mockuai.data.check.dto.RowValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:24
 */
@Slf4j
@Component
public abstract class AbstractDiffDataCheckStrategy extends AbstractDataCheckStrategy {


    @Override
    public String getName() {
        return DataCheckType.DIFF_CHECK.name();
    }

    @Override
    public void comparison(EventData eventData) {

        String tableName = eventData.getDataStore();
        DataStoreMapping tableMapping = DataStoreMapping.getTableMapping(tableName);
        String oldTable = tableMapping.getSourceStore();
        if (tableMapping.getTargetStore().equalsIgnoreCase(eventData.getDataStore())) {
            EventData rowValue = this.getRowValue(eventData, oldTable);
            if (rowValue == null) {
                return;
            }
            List<DifferencePropertyValue> diffValues = this.getDiffValues(eventData, rowValue);
            storeDiffValues(diffValues, tableName);
        }
    }

    /**
     * 差异数据保存
     *
     * @param diffValues
     */
    public void storeDiffValues(List<DifferencePropertyValue> diffValues, String dataStore) {
        if (CollectionUtils.isNotEmpty(diffValues)) {
            for (DifferencePropertyValue diffValue : diffValues) {
                log.info("dataStore {} diffValue {}", dataStore, diffValue);
            }
        }
    }

    /**
     * 获取数据差异
     *
     * @param targetData
     * @param sourceData
     * @return
     */
    public List<DifferencePropertyValue> getDiffValues(EventData targetData, EventData sourceData) {

        List<DifferencePropertyValue> list = Lists.newArrayList();
        String tableName = targetData.getDataStore();
        RowValue afterValue = targetData.getAfterValue();
        RowValue sourceAfterValue = sourceData.getAfterValue();
        Map<String, String> sourceColumnValueMap = sourceAfterValue.getPropertyValueMap();
        log.info("afterValue {} sourceAfterValue {}", afterValue, sourceAfterValue);
        Map<String, String> columnValueMap = afterValue.getPropertyValueMap();
        DataStoreInfo tableInfo = DataStoreMappingUtils.getTableInfo(tableName);
        List<String> columnList = tableInfo.getColumnList();
        DifferencePropertyValue value;
        for (String column : columnList) {
            String newDataValue = columnValueMap.get(column);
            String mappingColumn = DataStoreMappingUtils.getMappingProperty(tableName, column);
            String originalValue = sourceColumnValueMap.get(mappingColumn);
            if (newDataValue == null && originalValue == null) {
                continue;
            }
            if (newDataValue == null) {
                value = DifferencePropertyValue.build(originalValue, null, column);
                list.add(value);
                continue;
            }
            if (originalValue == null) {
                value = DifferencePropertyValue.build(null, newDataValue, column);
                list.add(value);
            }
            if (!newDataValue.equals(originalValue)) {
                value = DifferencePropertyValue.build(originalValue, newDataValue, column);
                list.add(value);
            }
        }
        return list;
    }
}
