package com.example.hibernateTest.service;

import com.example.hibernateTest.entity.Subject;
import com.example.hibernateTest.entity.Teacher;
import com.example.hibernateTest.helper.TeacherHelper;
import com.example.hibernateTest.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherHelper teacherHelper;

    public Optional<Teacher> getTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }

    public List<Teacher> getAllTeacher(){
        return (List<Teacher>) teacherRepository.findAll();
    }

    public Teacher getTheYoungestTeacher() {
        return teacherRepository.findTheYoungestTeacher();
    }

    public List<String> subjectList(Integer id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty()){
            throw new RuntimeException("Id is invalid datum");
        }
        return teacherRepository.listOfSubject(teacher.get().getId());
    }

    public Teacher saveTeacher(Teacher teacher) {
        //üzeleti validálások ...
        return teacherHelper.saveTeacher(teacher);
    }

    public void deleteTeacherById(int id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty()){
            throw new RuntimeException("Id is invalid datum or teacher has class");
        }
        teacherRepository.delete(teacher.get());
    }

}
