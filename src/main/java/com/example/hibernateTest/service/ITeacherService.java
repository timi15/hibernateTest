package com.example.hibernateTest.service;

import com.example.hibernateTest.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {

    Optional<Teacher> getTeacherById(Integer id);

    List<Teacher> getAllTeacher();

    Teacher getTheYoungestTeacher();

    List<String> subjectList(Integer id);

    Teacher saveTeacher(Teacher teacher);

    Teacher updateTeacher(Teacher teacherRequest, Teacher currentTeacher);

    void deleteTeacher(Teacher teacher);

}
