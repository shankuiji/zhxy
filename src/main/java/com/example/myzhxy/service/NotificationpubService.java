package com.example.myzhxy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myzhxy.pojo.Notificationpub;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationpubService extends IService<Notificationpub> {

    Notificationpub getNotificationpubById(Long notificationId);
    List<Notificationpub> getNotificationpubListByReceiver(String name);
    //这里我返回的是通知列表
    List<Notificationpub> getNotificationpubListByPublisher(String name);
}
