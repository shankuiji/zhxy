package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myzhxy.mapper.NotificationpubMapper;
import com.example.myzhxy.pojo.Notificationpub;
import com.example.myzhxy.service.NotificationpubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationpubServiceImpl extends ServiceImpl<NotificationpubMapper, Notificationpub> implements NotificationpubService {
    @Override
    public Notificationpub getNotificationpubById(Long notificationId){
        return null;
    }
    public List<Notificationpub> getNotificationpubListByReceiver(String name){
        QueryWrapper<Notificationpub> qw = new QueryWrapper<>();//设置查找
        qw.eq("receiver",name);
        List<Notificationpub> list_notification=baseMapper.selectList(qw);
        return list_notification;
    }

    public List<Notificationpub> getNotificationpubListByPublisher(String name){
        QueryWrapper<Notificationpub> qw = new QueryWrapper<>();//设置查找
        qw.eq("publisher",name);
        List<Notificationpub> list_notification=baseMapper.selectList(qw);
        return list_notification;
    }
}
