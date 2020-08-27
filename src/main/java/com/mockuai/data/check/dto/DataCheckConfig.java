package com.mockuai.data.check.dto;

import lombok.Data;

import java.util.List;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description : 比对配置  告警 | 比对策略等
 * @since : 2020-08-27 23:32
 */
@Data
public class DataCheckConfig {

    /**
     * 比对映射
     */
    private DataStoreMapping dataStoreMapping;

    /**
     * 比对策略配置
     */
    private List<String> checkStrategyList;

    /**
     * 比对结果通知配置
     */
    private List<String> monitorList;
}
