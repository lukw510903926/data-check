package com.mockuai.data.check.strategy;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-13 20:24
 */
public abstract class DiffDataCheckStrategy implements DataCheckStrategy, InitializingBean {


    /**
     *
     * @return
     */
    public abstract String getName();

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
