package com.nott.image.mapper;

import com.nott.image.entity.SysMovieInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2023-08-09
 */
public interface SysMovieInfoMapper extends BaseMapper<SysMovieInfo> {

    @Select("SELECT DISTINCT T2.id FROM( (SELECT smi.*,smi.id as movieId FROM sys_movie_info smi ORDER BY RAND() LIMIT #{size}) T1 RIGHT JOIN sys_minio_file T2 ON T2.holder_code = T1.ID)order by rand() LIMIT #{size}")
    public List<String> randomFileId(@Param(value = "size")Integer size);
}
