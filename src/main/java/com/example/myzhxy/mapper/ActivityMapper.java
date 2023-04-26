package com.example.myzhxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myzhxy.pojo.Activity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
}
