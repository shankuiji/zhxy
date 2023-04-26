package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.ActivityMapper;
import com.example.myzhxy.pojo.Activity;
import com.example.myzhxy.pojo.Student;
import com.example.myzhxy.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Override
    public Activity getActivityById(Long notificationId){
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", notificationId);
        return baseMapper.selectOne(queryWrapper);
    }
    public List<Activity> getActivityListByReceiver(String name){
        QueryWrapper<Activity> qw = new QueryWrapper<>();//设置查找
        qw.eq("receiver",name);
        List<Activity> list_activity=baseMapper.selectList(qw);
        return list_activity;
    }
    public List<Activity> getActivityListByPublisher(String name){
        QueryWrapper<Activity> qw = new QueryWrapper<>();//设置查找
        qw.eq("publisher",name);
        List<Activity> list_activity=baseMapper.selectList(qw);
        return list_activity;
    }

    public List<Activity> getActivityListByName(String name){
        QueryWrapper<Activity> qw = new QueryWrapper<>();//设置查找
        qw.eq("name",name);
        List<Activity> list_activity=baseMapper.selectList(qw);
        return list_activity;
    }
}
