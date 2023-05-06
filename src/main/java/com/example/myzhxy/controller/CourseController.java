package com.example.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Course;
import com.example.myzhxy.service.CourseService;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "课程控制器")
@RestController
@RequestMapping("/sms/courseController")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /** 成绩管理中回显课程信息
     * @Param: null
     * @Return: Result
     */
    @ApiOperation("成绩管理中回显课程信息")
    @GetMapping("/getCourses")
    public Result getCourse(){
        List<Course> list = courseService.getCourseByName();
        return Result.ok(list);
    }
}
