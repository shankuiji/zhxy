package com.example.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myzhxy.pojo.*;
import com.example.myzhxy.service.ActivityService;
import com.example.myzhxy.service.NotificationpubService;
import com.example.myzhxy.service.StudentService;
import com.example.myzhxy.service.ScoreService;
import com.example.myzhxy.utils.JwtHelper;
import com.example.myzhxy.utils.MD5;
import com.example.myzhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(tags = "成绩控制器")
@RestController
@RequestMapping("/sms/scoreController")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 分页查询学生成绩信息
     *
     * @Param: Integer pageNo , Integer pageSize , Score score
     * @Return: Result
     */
    @ApiOperation("分页查询学生成绩信息")
    @GetMapping("/getScoreByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
                                  @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
                                  Score score) {//传给了这个score
        Page<Score> scorePage = new Page<>(pageNo, pageSize);
        IPage<Score> iPage = scoreService.getScoreByOpr(score, scorePage);
        return Result.ok(iPage);
    }

    /**
     * 添加与修改学生成绩信息
     *
     * @Param: Score score
     * @Return: Result
     */
    @ApiOperation("添加与修改学生成绩信息")
    @PostMapping("/addOrUpdateScore")
    public Result addOrUpdateScore(@ApiParam("将JSON数据转为Score对象") @RequestBody Score score) {
        scoreService.saveOrUpdate(score);
        return Result.ok();
    }

    /**
     * 删除与批量删除学生成绩信息
     *
     * @Param: List<Integer> ids
     * @Return: Result
     */
    @ApiOperation("删除与批量删除学生成绩信息")
    @DeleteMapping("/delScoreByIds")
    public Result deleteScores(@ApiParam("要删除学生的id的JSON集合") @RequestBody List<Integer> ids) {
        scoreService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("删除与批量删除学生成绩信息")
    @DeleteMapping("/delScoreById")
    public Result deleteScore(@ApiParam("要删除学生的id的JSON集合") @RequestBody Map<String, Integer> map) {
        System.out.println(map.get("ids"));
        scoreService.removeById(map.get("ids"));
        return Result.ok();
    }
}