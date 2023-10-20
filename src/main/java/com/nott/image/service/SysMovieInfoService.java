package com.nott.image.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nott.common.R;
import com.nott.feign.TinyOssServiceClient;
import com.nott.image.entity.SysMovieFile;
import com.nott.image.entity.SysMovieInfo;
import com.nott.image.mapper.SysMovieInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nott
 * @since 2023-08-09
 */
@Slf4j
@Service
public class SysMovieInfoService extends ServiceImpl<SysMovieInfoMapper, SysMovieInfo> {

    @Autowired
    private TinyOssServiceClient tinyOssServiceClient;
    @Resource
    private SysMovieInfoMapper sysMovieInfoMapper;


    public List<String> randomFiles(JSONObject req) {
        Integer size = req.getInteger("size");
        List<String> fileIds = sysMovieInfoMapper.randomFileId(size);
        if (fileIds.isEmpty()) {
            log.info("推荐数据为空");
            return null;
        }

        List<String> urls = fileIds.stream().map(r -> {
            String previeUrl = null;
            if (StringUtils.isNotEmpty(r)) {
                R<String> previewResult = tinyOssServiceClient.preview(r);
                if (previewResult.getCode() == 200) {
                    previeUrl = Base64.getEncoder().encodeToString(previewResult.getObj().getBytes());
                }
            }
            return previeUrl;
        }).filter(r -> StringUtils.isNotEmpty(r)).collect(Collectors.toList());
        return urls;
    }
}
