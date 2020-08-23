package com.mockuai.data.check.runner;

import com.google.common.collect.Lists;
import com.mockuai.data.check.dto.DataStoreInfo;
import com.mockuai.data.check.dto.DataStoreMapping;
import com.mockuai.data.check.dto.DataStoreMappingUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-23 18:30
 */
@Component
public class DataStoreInitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        DataStoreInfo dataStoreInfo = new DataStoreInfo();
        dataStoreInfo.setDataStore("finance_db.shopkeeper_income_daily_statistics");
        dataStoreInfo.setPropertyList(Lists.newArrayList("refund_income", "order_refund_amount", "order_sale_count"));
        dataStoreInfo.setUniqueProperty(Lists.newArrayList("seller_id", "date"));

        DataStoreInfo dataStoreInfo1 = new DataStoreInfo();
        dataStoreInfo1.setDataStore("finance_db.finance_shopkeeper_income_daily_statistics");
        dataStoreInfo1.setPropertyList(Lists.newArrayList("refund_income", "order_refund_amount", "order_sale_count"));
        dataStoreInfo1.setUniqueProperty(Lists.newArrayList("seller_id", "date"));

        DataStoreMappingUtils.addDataStoreInfo(dataStoreInfo.getDataStore(), dataStoreInfo);
        DataStoreMappingUtils.addDataStoreInfo(dataStoreInfo1.getDataStore(), dataStoreInfo1);

        DataStoreMapping dataStoreMapping = new DataStoreMapping();
        dataStoreMapping.setSourceStore(dataStoreInfo.getDataStore());
        dataStoreMapping.setTargetStore(dataStoreInfo1.getDataStore());
        dataStoreMapping.addPropertyMapping("refund_income", "refund_income");
        dataStoreMapping.addPropertyMapping("order_refund_amount", "order_refund_amount");
        dataStoreMapping.addPropertyMapping("order_sale_count", "order_sale_count");

        DataStoreMappingUtils.addDataStoreMapping(dataStoreMapping);
    }
}
