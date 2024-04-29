package com.example.hibernateTest.helper;

import com.example.hibernateTest.entity.Teacher;
import com.example.hibernateTest.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherHelper {

    @Autowired
    TeacherRepository teacherRepository;

    @Transactional
    public Teacher saveTeacher(Teacher teacher) {
        Teacher newTeacher = new Teacher();
        newTeacher.setId(teacher.getId());
        newTeacher.setFirstname(teacher.getFirstname());
        newTeacher.setLastname(teacher.getLastname());
        newTeacher.setDateOfBirth(teacher.getDateOfBirth());
        newTeacher.setPlaceOfBirth(teacher.getPlaceOfBirth());
        return teacherRepository.save(newTeacher);
    }
}
