package com.mockuai.data.check.dto;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description : 数据变动事件
 * @since : 2020/8/3 10:41 下午
 */
@Getter
public enum DataEventType {

    /**
     * 未知
     */
    UN_KNOW(0, "UN_KNOW", "未知"),

    /**
     * 添加
     */
    INSERT(1, "INSERT", "添加"),

    /**
     * 删除
     */
    DELETE(2, "DELETE", "删除"),

    /**
     * 查询
     */
    QUERY(3, "QUERY", "查询"),

    /**
     * 修改
     */
    UPDATE(4, "UPDATE", "修改");

    private int event;

    private String name;

    private String desc;

    DataEventType(Integer event, String name, String desc) {
        this.event = event;
        this.desc = desc;
        this.name = name;
    }

    /**
     * 表间的映射
     */
    private static final Map<Integer, DataEventType> EVENT_TYPE_MAP = Maps.newHashMap();

    private static final Map<String, DataEventType> NAME_MAP = Maps.newHashMap();

    static {

        for (DataEventType value : DataEventType.values()) {
            EVENT_TYPE_MAP.put(value.event, value);
            NAME_MAP.put(value.name, value);
        }
    }

    public static DataEventType getByEvent(Integer event) {
        return EVENT_TYPE_MAP.get(event);
    }


    public static DataEventType getEventType(String eventType) {

        return NAME_MAP.get(eventType.toUpperCase());
    }
}
