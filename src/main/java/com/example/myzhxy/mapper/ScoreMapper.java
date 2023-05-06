package com.example.myzhxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myzhxy.pojo.Score;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {
}
