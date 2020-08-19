package com.mockuai.data.check.strategy;

import com.mockuai.data.check.dto.EventData;
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
public class DelayCheckStrategy extends AbstractDataCheckStrategy {


    @Override
    public String getName() {
        return DataCheckType.DELAY_CHECK.name();
    }

    @Override
    public void comparison(EventData eventData) {

    }
}
