package com.example.myzhxy.controller;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:50
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.Activity;
import com.example.myzhxy.pojo.Notificationpub;
import com.example.myzhxy.pojo.Student;
import com.example.myzhxy.pojo.Teacher;
import com.example.myzhxy.pojo.Vo.para_activity;
import com.example.myzhxy.pojo.Vo.para_notification;
import com.example.myzhxy.service.ActivityService;
import com.example.myzhxy.service.NotificationpubService;
import com.example.myzhxy.service.StudentService;
import com.example.myzhxy.service.TeacherService;
import com.example.myzhxy.utils.JwtHelper;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private NotificationpubService notificationpubService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private StudentService studentService;


    /** 分页查询教师信息
     * @Param:
     * @Return:
     */
    @ApiOperation("分页查询教师信息")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachersByOpr(@ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
                                   @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
                                   Teacher teacher){
        Page<Teacher> page = new Page<>(pageNo,pageSize);
        IPage<Teacher> iPage = teacherService.getTeachersByOpr(page,teacher);
        return Result.ok(iPage);
    }

    /** 添加与修改教师信息
     * @Param:
     * @Return:
     */
    @ApiOperation("添加与修改教师信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@ApiParam("JSON的Teacher格式对象，如果有id属性则为修改，否则为添加") @RequestBody Teacher teacher){
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    /** 删除与批量删除教师信息
     * @Param:
     * @Return:
     */
    @ApiOperation("删除与批量删除教师信息")
    @DeleteMapping("/deleteTeachers")
    public Result deleteTeachers(@ApiParam("要删除的所有teacher的id的JSON集合") @RequestBody List<Integer> ids){
        System.out.println(ids);
        teacherService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("删除与批量删除教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@ApiParam("要删除的所有teacher的id的JSON集合") @RequestBody Map<String,Integer> map){
        System.out.println(map.get("ids"));
        teacherService.removeById(map.get("ids"));
        return Result.ok();
    }

    @ApiOperation("添加通知")
    @RequestMapping("/addOrUpdateNotification")//这是接口
    public Result addOrUpdateNotification(@ApiParam("将JSON数据转为Notification对象") @RequestBody para_notification pnotification){//这里我们要接收参数
        String clazz=pnotification.getReceiver();
        List<Student> students=studentService.getStudentByClazz(clazz);
        for(Student student:students){
            Notificationpub n=new Notificationpub();
            n.setPublisher(pnotification.getPublisher());
            n.setReceiver(student.getName());
            n.setData(pnotification.getData());
            n.setStatus(0);//设置状态
            notificationpubService.save(n);
        }
        return Result.ok();
    }

    private static ArrayList<Notificationpub> removeDuplicateUser(List<Notificationpub> users) {
        Set<Notificationpub> set = new TreeSet<Notificationpub>(new Comparator<Notificationpub>() {
            @Override
            public int compare(Notificationpub o1, Notificationpub o2) {
                // 字符串,则按照asicc码升序排列
                return (o1.getPublisher()+o1.getData()).compareTo(o2.getPublisher()+o2.getData());
            }
        });
        set.addAll(users);
        return new ArrayList<Notificationpub>(set);
    }

    @ApiOperation("教师查看通知")
    @RequestMapping("/teachergetNotification")
    public Result teachergetNotification(@ApiParam("token数据") @RequestHeader("token") String token) {
        //System.out.println(notificationpub);
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Teacher teacher = teacherService.getTeacherById(userId);//获取教师
        //现在要查看对应的通知有什么
        List<Notificationpub> list_notification = notificationpubService.getNotificationpubListByPublisher(teacher.getName());
        list_notification=removeDuplicateUser(list_notification);
        return Result.ok(list_notification);
    }//这里前端传回的数据应该是和那个getinfo差不多的

    @ApiOperation("添加活动")
    @RequestMapping("/addOrUpdateActivity")//这是接口
    public Result addOrUpdateActivity(@ApiParam("将JSON数据转为Activity对象") @RequestBody para_activity pactivity){//这里我们要接收参数
        String clazz=pactivity.getReceiver();
        List<Student> students=studentService.getStudentByClazz(clazz);
        for(Student student:students){
            Activity a=new Activity();
            a.setPublisher(pactivity.getPublisher());
            a.setReceiver(student.getName());
            a.setName(pactivity.getName());
            a.setDetail(pactivity.getDetail());
            a.setStatus(0);//设置状态
            activityService.save(a);
        }
        return Result.ok();
    }


    @ApiOperation("教师查看活动")
    @RequestMapping("/teachergetActivity")
    public Result getActivity(@ApiParam("token数据") @RequestHeader("token") String token) {
        //System.out.println(notificationpub);
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Teacher teacher = teacherService.getTeacherById(userId);//获取教师
        //现在要查看对应的活动有什么
        List<Activity> list_activity = activityService.getActivityListByPublisher(teacher.getName());
        return Result.ok(list_activity);
    }//这里前端传回的数据应该是和那个getinfo差不多的

    @ApiOperation("查看活动报名情况")
    @RequestMapping("/checkActivity")//这是接口
    public Result checkActivity(@ApiParam("将JSON数据转为Activity对象") @RequestBody Activity activity){//这里我们要接收参数
        String name=activity.getName();
        List<Activity> activities=activityService.getActivityListByName(name);
        List<Student> studentList=new ArrayList<Student>();
        for(Activity activity1:activities){
            if (activity1.getStatus()==1){
                String student_name=activity1.getReceiver();
                studentList.add(studentService.getStudentByName(student_name));
            }
        }
        return Result.ok(studentList);
    }
}
