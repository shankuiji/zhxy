package com.example.myzhxy.pojo.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class para_notification {
    String publisher;//发布者
    String receiver;//接收班级
    String data;//通知内容
}
