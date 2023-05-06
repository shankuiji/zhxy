package com.example.myzhxy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myzhxy.pojo.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService extends IService<Course> {

    List<Course> getCourseByName();  //获取课程名称

}
