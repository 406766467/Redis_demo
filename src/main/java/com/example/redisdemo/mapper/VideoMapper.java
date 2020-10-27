package com.example.redisdemo.mapper;


import com.example.redisdemo.domain.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface VideoMapper {
    /**
     * 查询视频列表
     * @return
     */
    List<Video> videoList();


    /**
     * 根据ID查询Video
     * @return
     */
    Video findDetailById(@Param("videoId") int videoId);

    /**
     * 简单查询视频详情
     * @param videoId
     * @return
     */
    Video findById(@Param("videoId") int videoId);
}
