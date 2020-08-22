package com.mockuai.data.check.strategy;

import com.mockuai.data.check.dto.EventData;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:24
 */
public abstract class AbstractDataCheckStrategy implements DataCheckStrategy, InitializingBean {

    /**
     * 获取一行数据
     *
     * @param eventData
     * @param dataStore
     * @return
     */
    public abstract EventData getRowValue(EventData eventData, String dataStore);

    /**
     * 获取策略名称
     *
     * @return
     */
    public abstract String getName();

    @Override
    public void afterPropertiesSet() throws Exception {
        DataCheckStrategyHolder.addStrategy(getName(), this);
    }
}
