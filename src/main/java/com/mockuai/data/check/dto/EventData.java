package com.mockuai.data.check.dto;

import com.alibaba.fastjson.JSON;
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
     * 变化后的数据
     */
    private RowValue rowValue;

    /**
     * 事件类型
     */
    private DataEventType eventType;

    /**
     * 表名
     */
    private String dataStore;

    /**
     * 执行sql
     */
    private String sql;

    /**
     * 事件的时间戳
     */
    private Long occourTime;

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
