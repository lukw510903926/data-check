package com.mockuai.data.check.strategy;

import com.google.common.collect.Lists;
import com.mockuai.data.check.DataCheckType;
import com.mockuai.data.check.dto.DifferenceColumnValue;
import com.mockuai.data.check.dto.EventData;
import com.mockuai.data.check.dto.RowValue;
import com.mockuai.data.check.dto.TableMapping;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
public abstract class AbstractDiffDataCheckStrategy extends AbstractDataCheckStrategy {


    @Override
    public String getName() {
        return DataCheckType.DIFF_CHECK.name();
    }

    @Override
    public void comparison(EventData eventData) {

        String tableName = eventData.getTableName();
        TableMapping tableMapping = TableMapping.getTableMapping(tableName);
        String oldTable = tableMapping.getOldTable();
        if (tableMapping.getNewTable().equalsIgnoreCase(eventData.getTableName())) {
            EventData rowValue = this.getRowValue(eventData, oldTable);
            if (rowValue == null) {
                return;
            }
            List<DifferenceColumnValue> diffValues = this.getDiffValues(eventData, rowValue);
            if (CollectionUtils.isNotEmpty(diffValues)) {
                for (DifferenceColumnValue diffValue : diffValues) {
                    log.info("table {} diffValue {}", tableName, diffValue);
                }
            }
        }
    }

    /**
     * 获取一行数据
     *
     * @param eventData
     * @param tableName
     * @return
     */
    public abstract EventData getRowValue(EventData eventData, String tableName);

    /**
     * 获取数据差异
     *
     * @param targetValue
     * @param sourceValue
     * @return
     */
    public List<DifferenceColumnValue> getDiffValues(EventData targetValue, EventData sourceValue) {

        RowValue afterValue = targetValue.getAfterValue();
        RowValue sourceAfterValue = sourceValue.getAfterValue();
        log.info("afterValue {} sourceAfterValue {}", afterValue, sourceAfterValue);
        return Lists.newArrayList();
    }
}
