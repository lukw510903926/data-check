package com.mockuai.data.check.strategy;

import com.alibaba.fastjson.JSON;
import com.mockuai.data.check.constants.Constants;
import com.mockuai.data.check.dto.DifferencePropertyValue;
import com.mockuai.data.check.dto.EventData;
import com.mockuai.data.check.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:24
 */
@Slf4j
@Component
public class RedisDiffDataCheckStrategy extends AbstractDiffDataCheckStrategy {

    @Resource
    private RedisService redisService;

    private static final String DIFF_PREFIX_KEY = "diff:key";

    private static final String DIFF_RESULT = "result";

    @Override
    public String getName() {
        return DataCheckType.DIFF_CHECK.name();
    }

    @Override
    public void comparison(EventData eventData) {

        String dataStore = eventData.getDataStore();
        String key = this.getKey(dataStore, eventData.getAfterValue().getRowKeyMap());
        redisService.set(key, JSON.toJSONString(eventData), Constants.ONE_HOUR);
        super.comparison(eventData);
    }

    @Override
    public void storeDiffValues(List<DifferencePropertyValue> diffValues, EventData eventData) {
        super.storeDiffValues(diffValues, eventData);
        String diffKey = this.getKey(eventData.getDataStore(), eventData.getAfterValue().getRowKeyMap());
        String diffResultKey = diffKey + Constants.SEPARATOR + DIFF_RESULT;
        if (CollectionUtils.isNotEmpty(diffValues)) {
            this.redisService.set(diffResultKey, JSON.toJSONString(diffValues), Constants.ONE_HOUR);
        }
    }

    @Override
    public EventData getRowValue(EventData eventData, String dataStore) {

        String key = this.getKey(dataStore, eventData.getAfterValue().getRowKeyMap());
        Object o = redisService.get(key);
        return Optional.ofNullable(o).map(item -> JSON.parseObject(item.toString(), EventData.class)).orElse(null);
    }

    public String getKey(String dataStore, Map<String, String> rowKeyMap) {
        StringBuilder builder = new StringBuilder();
        rowKeyMap.forEach((key, value) -> builder.append(value).append(Constants.SEPARATOR));
        String substring = builder.toString().substring(0, builder.lastIndexOf(":"));
        return Constants.ROW_KEY_PREFIX + DIFF_PREFIX_KEY + Constants.SEPARATOR + dataStore + Constants.SEPARATOR + substring;
    }
}
