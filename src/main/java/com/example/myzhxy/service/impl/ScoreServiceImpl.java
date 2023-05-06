package com.example.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myzhxy.mapper.ScoreMapper;
import com.example.myzhxy.pojo.Score;
import com.example.myzhxy.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {


    @Override
    public IPage<Score> getScoreByOpr(Score score, Page<Score> scorePage) {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        String courseName = score.getCourse();
        String name = score.getName();
        if (!StringUtils.isEmpty(courseName)) {
            scoreQueryWrapper.like("course", courseName);
        }
        if (!StringUtils.isEmpty(name)) {
            scoreQueryWrapper.like("name", name);
        }
        scoreQueryWrapper.orderByDesc("id");
        scoreQueryWrapper.orderByAsc("name");
        return baseMapper.selectPage(scorePage, scoreQueryWrapper);
    }

    public List<Score> getScoreBycourse(String course){
        QueryWrapper<Score> qw = new QueryWrapper<>();//设置查找
        qw.eq("course",course);
        List<Score> list_score=baseMapper.selectList(qw);
        return list_score;
    }

    public List<Score> getScoreByname(String name){ //获取对应学生的成绩
        QueryWrapper<Score> qw = new QueryWrapper<>();//设置查找
        qw.eq("name",name);
        List<Score> list_score=baseMapper.selectList(qw);
        return list_score;
    }
}
