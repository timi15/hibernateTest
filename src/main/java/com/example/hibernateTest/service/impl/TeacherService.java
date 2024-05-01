package com.example.hibernateTest.service.impl;


import com.example.hibernateTest.entity.Teacher;
import com.example.hibernateTest.helper.TeacherHelper;
import com.example.hibernateTest.repository.TeacherRepository;
import com.example.hibernateTest.service.ITeacherService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherHelper teacherHelper;

    @Override
    public Optional<Teacher> getTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return (List<Teacher>) teacherRepository.findAll();
    }

    @Override
    public Teacher getTheYoungestTeacher() {
        return teacherRepository.findTheYoungestTeacher();
    }

    @Override
    public List<String> subjectList(Integer id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new RuntimeException("Id is invalid datum");
        }
        return teacherRepository.listOfSubject(teacher.get().getId());
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherHelper.saveTeacher(teacher);
    }

    @Transactional
    @Override
    public Teacher updateTeacher(Teacher teacherRequest, Teacher currentTeacher) {
        currentTeacher.setFirstname(teacherRequest.getFirstname());
        currentTeacher.setLastname(teacherRequest.getLastname());
        return teacherRepository.save(currentTeacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }
}
