package com.mockuai.data.check.strategy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mockuai.data.check.dto.DelayData;
import com.mockuai.data.check.dto.EventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:24
 */
@Slf4j
@Component
public class RedisDelayCheckStrategy extends AbstractDelayCheckStrategy {

    @Override
    public List<DelayData> getDelayDataList(EventData targetData) {

        return Lists.newArrayList();
    }

    @Override
    public void storeDelayData(List<DelayData> delayDataList, String dataStore) {

        for (DelayData delayData : delayDataList) {
            log.info("dataStore {} property {} delayData {} ", dataStore, delayData.getProperty(), JSON.toJSONString(delayData));
        }
    }

}
