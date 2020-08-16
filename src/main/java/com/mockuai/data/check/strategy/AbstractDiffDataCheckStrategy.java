package com.mockuai.data.check.strategy;

import com.mockuai.data.check.DataCheckType;
import com.mockuai.data.check.dto.EventData;
import com.mockuai.data.check.dto.RowValue;
import com.mockuai.data.check.dto.TableMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
            RowValue rowValue = this.getRowValue(oldTable, eventData.getAfterValue().getRowKey());
        }
    }

    /**
     * 获取一行数据
     *
     * @param tableName
     * @param rowKey
     * @return
     */
    public abstract RowValue getRowValue(String tableName, String rowKey);
}
