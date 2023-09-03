package com.nott.feign;

import com.alibaba.fastjson.JSONObject;
import com.nott.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nott
 * @Date 2023/9/3
 */

@FeignClient(value = "tiny-mq-producer")
public interface MqProducerServiceClient {

    @RequestMapping("mq/producer/message")
    R sendMessage(@RequestBody JSONObject jsonObject);

}

