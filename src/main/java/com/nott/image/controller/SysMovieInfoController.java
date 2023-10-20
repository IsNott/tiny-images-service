package com.nott.image.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nott.common.CommonController;
import com.nott.common.R;
import com.nott.image.entity.SysMovieInfo;
import com.nott.image.service.SysMovieInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nott
 * @since 2023-08-09
 */
@RestController
@RequestMapping("/movie/")
public class SysMovieInfoController extends CommonController<SysMovieInfoService, SysMovieInfo> {

    @Resource
    private SysMovieInfoService sysMovieInfoService;

    @PostMapping("randomFiles")
    public R recommended(@RequestBody JSONObject req) {
        return R.okData(sysMovieInfoService.randomFiles(req));
    }


}
