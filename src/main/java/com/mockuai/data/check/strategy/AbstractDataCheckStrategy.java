package com.mockuai.data.check.strategy;

import com.mockuai.data.check.constants.Constants;
import com.mockuai.data.check.dto.RowValue;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:24
 */
public abstract class AbstractDataCheckStrategy implements DataCheckStrategy, InitializingBean {


    /**
     * 获取策略名称
     *
     * @return
     */
    public abstract String getName();

    /**
     * 获取差异检查数据的key
     *
     * @param rowValue
     * @return
     */
    public String getDiffRowKey(RowValue rowValue) {
        return Constants.ROW_KEY_PREFIX + rowValue.getRowKey();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
