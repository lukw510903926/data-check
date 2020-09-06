package com.mockuai.data.check.strategy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mockuai.data.check.dto.DataStoreMapping;
import com.mockuai.data.check.dto.DataStoreMappingUtils;
import com.mockuai.data.check.dto.DelayData;
import com.mockuai.data.check.dto.EventData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:24
 */
@Slf4j
public abstract class AbstractDelayCheckStrategy extends AbstractDataCheckStrategy {

    /**
     * 数据比对
     *
     * @param eventData
     */
    @Override
    public void comparison(EventData eventData) {
        String dataStore = eventData.getDataStore();
        List<DataStoreMapping> mappings = DataStoreMappingUtils.getDataStoreMapping(dataStore);
        log.info("delay data check eventValue {}", eventData);
        if (CollectionUtils.isNotEmpty(mappings)) {
            for (DataStoreMapping dataStoreMapping : mappings) {
                if (dataStoreMapping.getTargetStore().equalsIgnoreCase(eventData.getDataStore())) {
                    List<DelayData> delayData = this.getDelayDataList(eventData);
                    storeDelayData(delayData, eventData);
                }
            }
        }
    }

    public List<DelayData> getDelayDataList(EventData targetData) {

        return Lists.newArrayList();
    }

    public void storeDelayData(List<DelayData> delayDataList, EventData eventData) {

        for (DelayData delayData : delayDataList) {
            log.info("dataStore {} property {} delayData {} ", eventData.getDataStore(), delayData.getProperty(), JSON.toJSONString(delayData));
        }
    }

    @Override
    public EventData getRowValue(EventData eventData, String dataStore) {
        return null;
    }

    @Override
    public String getName() {
        return DataCheckType.DELAY_CHECK.name();
    }

}
