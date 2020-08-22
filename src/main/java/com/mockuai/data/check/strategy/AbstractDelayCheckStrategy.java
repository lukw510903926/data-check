package com.mockuai.data.check.strategy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mockuai.data.check.dto.DataStoreMapping;
import com.mockuai.data.check.dto.DelayData;
import com.mockuai.data.check.dto.EventData;
import lombok.extern.slf4j.Slf4j;

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
        DataStoreMapping dataStoreMapping = DataStoreMapping.getDataStoreMapping(dataStore);
        String sourceStore = dataStoreMapping.getSourceStore();
        if (dataStoreMapping.getTargetStore().equalsIgnoreCase(eventData.getDataStore())) {
            EventData rowValue = this.getRowValue(eventData, sourceStore);
            if (rowValue == null) {
                return;
            }
            List<DelayData> delayData = this.getDelayDataList(eventData, rowValue);
            storeDelayData(delayData, dataStore);
        }
    }

    public List<DelayData> getDelayDataList(EventData targetData, EventData sourceData) {

        return Lists.newArrayList();
    }

    public void storeDelayData(List<DelayData> delayDataList, String dataStore) {

        for (DelayData delayData : delayDataList) {
            log.info("dataStore {} property {} delayData {} ", dataStore, delayData.getProperty(), JSON.toJSONString(delayData));
        }
    }

    @Override
    public String getName() {
        return DataCheckType.DELAY_CHECK.name();
    }

}
