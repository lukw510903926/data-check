package com.mockuai.data.check.canal;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.FlatMessage;
import com.google.common.collect.Lists;
import com.mockuai.data.check.dto.DataEventType;
import com.mockuai.data.check.dto.DataStoreMappingUtils;
import com.mockuai.data.check.dto.EventData;
import com.mockuai.data.check.dto.PropertyValue;
import com.mockuai.data.check.dto.RowValue;
import com.mockuai.data.check.strategy.DataCheckStrategy;
import com.mockuai.data.check.strategy.DataCheckStrategyHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-23 17:56
 */
@Slf4j
@Component
public class HandleCanalMessage {

    /**
     * 需要处理的event类型
     */
    private static final List<String> HANDLE_EVENT_TYPES = Lists.newArrayList(CanalEntry.EventType.INSERT.toString(), CanalEntry.EventType.UPDATE.toString());

    public void consumer(FlatMessage flatMessage) {
        String eventType = flatMessage.getType();
        String dataStore = this.getDataStoreName(flatMessage);
        log.info("================> dataStore {}  eventType : {}", dataStore, eventType);
        if (!DataStoreMappingUtils.containDataStore(dataStore)) {
            log.info("表不在监听处理范围内 dataStore {}", dataStore);
            return;
        }
        if (!HANDLE_EVENT_TYPES.contains(eventType)) {
            log.info("eventType 不处理 eventType {}", eventType);
            return;
        }
        List<EventData> list = buildEventData(flatMessage, dataStore);
        for (EventData eventData : list) {
            List<DataCheckStrategy> strategyList = DataCheckStrategyHolder.getStrategyList();
            for (DataCheckStrategy dataCheckStrategy : strategyList) {
                dataCheckStrategy.comparison(eventData);
            }
        }

    }

    public List<EventData> buildEventData(FlatMessage flatMessage, String dataStore) {

        EventData eventData = new EventData();
        DataEventType dataEventType = DataEventType.getEventType(flatMessage.getType());
        List<Map<String, String>> afterColumnsList = flatMessage.getData();
        List<EventData> list = Lists.newArrayListWithExpectedSize(afterColumnsList.size());
        for (Map<String, String> columnMap : afterColumnsList) {
            List<PropertyValue> afterList = Lists.newArrayList();
            columnMap.forEach((key, value) -> afterList.add(PropertyValue.build(key, value)));
            RowValue afterValue = RowValue.build(afterList, dataStore);
            afterValue.setOccourTime(flatMessage.getEs());
            eventData.setEventType(dataEventType)
                    .setOccourTime(afterValue.getOccourTime())
                    .setDataStore(dataStore)
                    .setRowValue(afterValue);
            list.add(eventData);
        }
        return list;
    }

    private String getDataStoreName(FlatMessage flatMessage) {
        return flatMessage.getDatabase() + "." + flatMessage.getTable();
    }
}
