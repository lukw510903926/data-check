package com.mockuai.data.check.strategy;

import com.alibaba.fastjson.JSON;
import com.mockuai.data.check.DataCheckType;
import com.mockuai.data.check.constants.Constants;
import com.mockuai.data.check.dto.EventData;
import com.mockuai.data.check.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    @Override
    public String getName() {
        return DataCheckType.DIFF_CHECK.name();
    }

    @Override
    public void comparison(EventData eventData) {

        String tableName = eventData.getTableName();
        String key = this.getKey(tableName, eventData.getAfterValue().getRowKey());
        redisService.set(key, JSON.toJSONString(eventData), Constants.ONE_HOUR);
        super.comparison(eventData);
    }

    @Override
    public EventData getRowValue(EventData eventData, String tableName) {

        String key = this.getKey(tableName, eventData.getAfterValue().getRowKey());
        Object o = redisService.get(key);
        return Optional.ofNullable(o).map(item -> JSON.parseObject(item.toString(), EventData.class)).orElse(null);
    }

    public String getKey(String tableName, String rowKey) {
        return Constants.ROW_KEY_PREFIX + tableName + Constants.SEPARATOR + rowKey;
    }
}
