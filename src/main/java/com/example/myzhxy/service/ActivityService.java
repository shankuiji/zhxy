package com.example.myzhxy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myzhxy.pojo.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService extends IService<Activity> {

    Activity getActivityById(Long activityId);
    List<Activity> getActivityListByReceiver(String name);
    //这里返回的是活动列表

    List<Activity> getActivityListByPublisher(String name);
    List<Activity> getActivityListByName(String name);
    //这里返回的是活动列表
}
