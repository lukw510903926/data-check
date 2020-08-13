package com.mockuai.data.check.strategy;

import com.mockuai.data.check.dto.EventData;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:23
 */
public interface DataCheckStrategy {


    /**
     * 数据比对
     *
     * @param eventData
     */
    void comparison(EventData eventData);
}
