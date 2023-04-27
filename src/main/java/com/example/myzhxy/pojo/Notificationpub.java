package com.example.myzhxy.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_notification_pub")
public class Notificationpub {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String publisher;

    private String receiver;

    private String data;

    private Integer status;
    //0 ：未读 1：已读
}
