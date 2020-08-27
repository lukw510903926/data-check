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

        String dataStore = eventData.getDataStore();
        DataStoreMapping dataStoreMapping = DataStoreMappingUtils.getDataStoreMapping(dataStore);
        String sourceStore = dataStoreMapping.getSourceStore();
        if (dataStoreMapping.getTargetStore().equalsIgnoreCase(eventData.getDataStore())) {
            EventData rowValue = this.getRowValue(eventData, sourceStore);
            if (rowValue == null) {
                return;
            }
            List<DifferencePropertyValue> diffValues = this.getDiffValues(eventData, rowValue);
            storeDiffValues(diffValues, eventData);
        }
    }

    /**
     * 差异数据保存
     *
     * @param diffValues
     */
    public void storeDiffValues(List<DifferencePropertyValue> diffValues, EventData eventData) {
        if (CollectionUtils.isNotEmpty(diffValues)) {
            for (DifferencePropertyValue diffValue : diffValues) {
                log.info("dataStore {} diffValue {}", eventData.getDataStore(), diffValue);
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
        String dataStore = targetData.getDataStore();
        RowValue afterValue = targetData.getAfterValue();
        RowValue sourceAfterValue = sourceData.getAfterValue();
        Map<String, String> sourcePropertyValueMap = sourceAfterValue.getPropertyValueMap();
        log.info("afterValue {} sourceAfterValue {}", afterValue, sourceAfterValue);
        Map<String, String> propertyValueMap = afterValue.getPropertyValueMap();
        DataStoreInfo dataStoreInfo = DataStoreMappingUtils.getDataStoreInfo(dataStore);
        List<String> propertyList = dataStoreInfo.getPropertyList();
        DifferencePropertyValue value;
        for (String property : propertyList) {
            String newDataValue = propertyValueMap.get(property);
            String mappingProperty = DataStoreMappingUtils.getMappingProperty(dataStore, property);
            String originalValue = sourcePropertyValueMap.get(mappingProperty);
            if (newDataValue == null && originalValue == null) {
                continue;
            }
            if (newDataValue == null) {
                value = DifferencePropertyValue.build(originalValue, null, property);
                list.add(value);
                continue;
            }
            if (!newDataValue.equals(originalValue)) {
                value = DifferencePropertyValue.build(originalValue, newDataValue, property);
                list.add(value);
            }
        }
        return list;
    }
}
