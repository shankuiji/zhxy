package com.example.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myzhxy.pojo.LoginForm;
import com.example.myzhxy.pojo.Student;

import java.util.List;

/**
 * @Author hongxiaobin
 * @Time 2022/10/11-20:46
 */
public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);

    Student getStudentById(Long userId);

    Student getStudentByName(String name);

    IPage<Student> getStudentByOpr(Student student, Page<Student> studentPage);

    Student getOnePwd(Long userId, String oldPwd);

    List<Student> getStudentByClazz(String clazz);
}
