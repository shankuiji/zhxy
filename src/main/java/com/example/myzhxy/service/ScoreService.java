package com.example.myzhxy.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myzhxy.pojo.Score;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService extends IService<Score> {

    IPage<Score> getScoreByOpr(Score score, Page<Score> scorePage);

    List<Score> getScoreBycourse(String course);//获取对应课程的成绩

    List<Score> getScoreByname(String name);  //获取对应学生的成绩

}
