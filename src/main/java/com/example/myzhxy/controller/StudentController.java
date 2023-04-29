package com.example.myzhxy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Activity;
import com.example.myzhxy.pojo.Notificationpub;
import com.example.myzhxy.pojo.Student;
import com.example.myzhxy.service.ActivityService;
import com.example.myzhxy.service.NotificationpubService;
import com.example.myzhxy.service.StudentService;
import com.example.myzhxy.utils.JwtHelper;
import com.example.myzhxy.utils.MD5;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.management.Notification;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:50
 */
@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private NotificationpubService notificationpubService;

    @Autowired
    private ActivityService activityService;

    /**
     * 分页查询学生信息
     *
     * @Param: Integer pageNo , Integer pageSize , Student student
     * @Return: Result
     */
    @ApiOperation("分页查询学生信息")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
                                  @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
                                  Student student) {
        Page<Student> studentPage = new Page<>(pageNo, pageSize);
        IPage<Student> iPage = studentService.getStudentByOpr(student, studentPage);
        return Result.ok(iPage);
    }

    /**
     * 添加与修改学生信息
     *
     * @Param: Student student
     * @Return: Result
     */
    @ApiOperation("添加与修改学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@ApiParam("将JSON数据转为Student对象") @RequestBody Student student) {
        Integer id = student.getId();
        System.out.println(student);
        if (null == id || 0 == id) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    /**
     * 删除与批量删除学生信息
     *
     * @Param: List<Integer> ids
     * @Return: Result
     */
    @ApiOperation("删除与批量删除学生信息")
    @DeleteMapping("/delStudentByIds")
    public Result deleteStudents(@ApiParam("要删除学生的id的JSON集合") @RequestBody List<Integer> ids) {
        studentService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("删除与批量删除学生信息")
    @DeleteMapping("/delStudentById")
    public Result deleteStudent(@ApiParam("要删除学生的id的JSON集合") @RequestBody Map<String, Integer> map) {
        System.out.println(map.get("ids"));
        studentService.removeById(map.get("ids"));
        return Result.ok();
    }

    @ApiOperation("查看通知")
    @RequestMapping("/getNotification")
    public Result getNotification(@ApiParam("token数据") @RequestHeader("token") String token) {
        //System.out.println(notificationpub);
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Student student = studentService.getStudentById(userId);//获取学生
        //现在要查看对应的通知有什么
        List<Notificationpub> list_notification = notificationpubService.getNotificationpubListByReceiver(student.getName());
        return Result.ok(list_notification);
    }//这里前端传回的数据应该是和那个getinfo差不多的

    @ApiOperation("确认收到通知")
    @RequestMapping("/receiveNotification")
    public Result receiveNotification(@ApiParam("将JSON数据转为Notificationpub对象") @RequestBody Notificationpub notification) {
        notification.setStatus(1);
        notificationpubService.saveOrUpdate(notification);
        return Result.ok();
    }




    @ApiOperation("查看活动")
    @RequestMapping("/getActivity")
    public Result getActivity(@ApiParam("token数据") @RequestHeader("token") String token) {
        //System.out.println(notificationpub);
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Student student = studentService.getStudentById(userId);//获取学生
        //现在要查看对应的活动有什么
        List<Activity> list_activity = activityService.getActivityListByReceiver(student.getName());
        //list_activity=removeDuplicateUser(list_activity);
        return Result.ok(list_activity);
    }//这里前端传回的数据应该是和那个getinfo差不多的

    @ApiOperation("确认参加活动")
    @RequestMapping("/joinActivity")
    public Result joinActivity(@ApiParam("将JSON数据转为Activity对象") @RequestBody Activity activity) {
        if(activity.getStatus()==1){
            activity.setStatus(0);
            activityService.saveOrUpdate(activity);
        }
        else{
            activity.setStatus(1);
            activityService.saveOrUpdate(activity);
        }

        return Result.ok();
    }
}