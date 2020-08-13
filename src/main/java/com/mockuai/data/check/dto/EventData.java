package com.mockuai.data.check.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020/8/3 11:12 下午
 */
@Data
@Accessors(chain = true)
public class EventData implements Serializable {
    private static final long serialVersionUID = -1620021370436333807L;

    /**
     * 变化前的数据
     */
    private RowValue beforeValue;

    /**
     * 变化后的数据
     */
    private RowValue afterValue;

    /**
     * 事件类型
     */
    private DataEventType eventType;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 执行sql
     */
    private String sql;

    /**
     * 事件的时间戳
     */
    private Long occourTime;
}
