package com.nott.image.controller;


import com.nott.common.CommonController;
import com.nott.image.entity.SysMovieFile;
import com.nott.image.service.SysMovieFileService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2023-08-09
 */
@RestController
@RequestMapping("/movie/det")
public class SysMovieFileController extends CommonController<SysMovieFileService, SysMovieFile> {

}
