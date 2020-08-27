package com.mockuai.data.check.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-10 19:27
 */
@Data
@Accessors(chain = true)
public class DelayData implements Serializable {

    private static final long serialVersionUID = -9038700530704003753L;

    /**
     * 属性
     */
    private String property;

    /**
     * 属性值
     */
    private String attributeValue;

    /**
     * 原数据时间
     */
    private String sourceTime;

    /**
     * 目标数据时间
     */
    private String targetTime;

    /**
     * 延时 (毫秒)
     */
    private Long delay;

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
