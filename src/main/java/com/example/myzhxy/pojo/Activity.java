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
@TableName("tb_activity")
public class Activity {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String publisher;//发布者
    private String receiver;//接收者（为某一位学生）
    private String name; //活动名称
    private String detail; //活动详情
    private Integer status;//参加状态  0 ：不参加 1：参加
}
