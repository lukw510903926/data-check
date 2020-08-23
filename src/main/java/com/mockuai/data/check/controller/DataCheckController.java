package com.mockuai.data.check.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.FlatMessage;
import com.mockuai.data.check.canal.HandleCanalMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-23 18:27
 */
@RestController
public class DataCheckController {

    @Resource
    private HandleCanalMessage handleCanalMessage;

    @GetMapping("/data/check/test")
    public Object test() {
        String message = "{\"data\":[{\"date\":\"20200823\",\"biz_code\":\"mokuaitv\",\"refund_income\":\"125360\",\"order_refund_amount\":\"125360\",\"gmt_modified\":\"2020-08-23 18:26:14\",\"order_sale_count\":\"773\",\"order_refund_count\":\"27\",\"delete_timestamp\":\"0\",\"order_sale_amount\":\"2855870\",\"id\":\"3050401\",\"seller_id\":\"5589048\",\"sale_income\":\"2855870\",\"delete_mark\":\"0\",\"gmt_created\":\"2020-08-23 08:47:12\"}],\"database\":\"finance_db\",\"es\":1598178374000,\"id\":1168116,\"isDdl\":false,\"mysqlType\":{\"date\":\"int(11)\",\"biz_code\":\"varchar(32)\",\"refund_income\":\"bigint(255)\",\"order_refund_amount\":\"bigint(20)\",\"gmt_modified\":\"datetime\",\"order_sale_count\":\"bigint(20)\",\"order_refund_count\":\"bigint(20)\",\"delete_timestamp\":\"bigint(20)\",\"order_sale_amount\":\"bigint(20)\",\"id\":\"bigint(20)\",\"seller_id\":\"bigint(20)\",\"sale_income\":\"bigint(255)\",\"delete_mark\":\"tinyint(4)\",\"gmt_created\":\"datetime\"},\"old\":[{\"order_refund_count\":\"26\",\"refund_income\":\"122370\",\"order_refund_amount\":\"122370\",\"gmt_modified\":\"2020-08-23 17:41:50\"}],\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"date\":4,\"biz_code\":12,\"refund_income\":-5,\"order_refund_amount\":-5,\"gmt_modified\":93,\"order_sale_count\":-5,\"order_refund_count\":-5,\"delete_timestamp\":-5,\"order_sale_amount\":-5,\"id\":-5,\"seller_id\":-5,\"sale_income\":-5,\"delete_mark\":-6,\"gmt_created\":93},\"table\":\"shopkeeper_income_daily_statistics\",\"ts\":1598178374745,\"type\":\"UPDATE\"}";

        FlatMessage flatMessage = JSON.parseObject(message, FlatMessage.class);
        handleCanalMessage.consumer(flatMessage);
        flatMessage.setTable("finance_shopkeeper_income_daily_statistics");
        List<Map<String, String>> data = flatMessage.getData();
        for (Map<String, String> datum : data) {
            datum.put("refund_income", System.currentTimeMillis() + "");
            datum.put("order_refund_amount", System.currentTimeMillis() + "");
            datum.put("order_sale_count", System.currentTimeMillis() + "");
            datum.put("seller_id", ThreadLocalRandom.current().nextLong() + "");
        }
        handleCanalMessage.consumer(flatMessage);
        return flatMessage;
    }
}

