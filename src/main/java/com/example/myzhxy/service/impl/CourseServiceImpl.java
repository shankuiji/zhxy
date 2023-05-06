package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.CourseMapper;
import com.example.myzhxy.pojo.Activity;
import com.example.myzhxy.pojo.Clazz;
import com.example.myzhxy.pojo.Course;
import com.example.myzhxy.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Override

     public List<Course> getCourseByName(){
        return baseMapper.selectList(null);
    }
}
