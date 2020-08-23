package com.mockuai.data.check.strategy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mockuai.data.check.constants.Constants;
import com.mockuai.data.check.dto.DataStoreInfo;
import com.mockuai.data.check.dto.DataStoreMappingUtils;
import com.mockuai.data.check.dto.DelayData;
import com.mockuai.data.check.dto.EventData;
import com.mockuai.data.check.dto.RowValue;
import com.mockuai.data.check.service.RedisService;
import com.mockuai.data.check.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
public class RedisDelayCheckStrategy extends AbstractDelayCheckStrategy {

    private static final String DELAY_KEY = "delay_key";

    @Resource
    private RedisService redisService;

    @Override
    public void comparison(EventData eventData) {

        RowValue afterValue = eventData.getAfterValue();
        DataStoreInfo dataStoreInfo = DataStoreMappingUtils.getDataStoreInfo(eventData.getDataStore());
        List<String> propertyList = dataStoreInfo.getPropertyList();
        for (String property : propertyList) {
            String propertyKey = this.getPropertyKey(property, afterValue);
            this.redisService.set(propertyKey, afterValue.getOccourTime(), Constants.ONE_HOUR);
        }
        super.comparison(eventData);
    }

    @Override
    public List<DelayData> getDelayDataList(EventData targetData) {

        List<DelayData> list = Lists.newArrayList();
        DataStoreInfo dataStoreInfo = DataStoreMappingUtils.getDataStoreInfo(targetData.getDataStore());
        List<String> propertyList = dataStoreInfo.getPropertyList();
        RowValue afterValue = targetData.getAfterValue();
        for (String property : propertyList) {
            String propertyKey = this.getPropertyKey(property, afterValue);
            Object o = this.redisService.get(propertyKey);
            if (o != null) {
                long t = Long.parseLong(o.toString());
                long delay = targetData.getOccourTime() - t;
                if (delay > 0) {
                    DelayData delayData = new DelayData();
                    delayData.setAttributeValue(afterValue.getPropertyValue(property))
                            .setDelay(delay)
                            .setProperty(property)
                            .setSourceTime(DateUtils.formatDate(t))
                            .setTargetTime(DateUtils.formatDate(afterValue.getOccourTime()));
                    list.add(delayData);
                }
            }
        }
        return list;
    }

    @Override
    public void storeDelayData(List<DelayData> delayDataList, String dataStore) {

        super.storeDelayData(delayDataList, dataStore);
        String diffRedisKey = Constants.ROW_KEY_PREFIX + DELAY_KEY + Constants.SEPARATOR + dataStore;
        if (CollectionUtils.isNotEmpty(delayDataList)) {
            this.redisService.set(diffRedisKey, JSON.toJSONString(delayDataList), Constants.ONE_HOUR);
        }
    }

    private String getPropertyKey(String property, RowValue afterValue) {

        StringBuilder builder = new StringBuilder();
        String dataStore = afterValue.getDataStore();
        Map<String, String> rowKeyMap = afterValue.getRowKeyMap();
        rowKeyMap.forEach((key, value) -> builder.append(value).append(Constants.SEPARATOR));
        builder.append(property).append(Constants.SEPARATOR).append(afterValue.getPropertyValue(property));
        return Constants.ROW_KEY_PREFIX + DELAY_KEY + Constants.SEPARATOR + dataStore + Constants.SEPARATOR + builder.toString();
    }

}
