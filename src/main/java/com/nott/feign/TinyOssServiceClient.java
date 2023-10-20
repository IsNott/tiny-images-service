package com.nott.feign;

import com.nott.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @Date 2023/10/19
 */

@FeignClient(name = "tiny-oss-service")
public interface TinyOssServiceClient {

    @PostMapping("/file/preview/{id}")
    R preview(@NotNull @PathVariable("id") String id);
}
