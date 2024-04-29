package com.example.hibernateTest.helper;

import com.example.hibernateTest.entity.Subject;
import com.example.hibernateTest.entity.Teacher;
import com.example.hibernateTest.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectHelper {

    @Autowired
    SubjectRepository subjectRepository;
    @Transactional
    public Subject saveSubject(Subject subject, Teacher teacher){
        Subject newSubject = new Subject();
        newSubject.setId(subject.getId());
        newSubject.setTitle(subject.getTitle());
        newSubject.setCredit(subject.getCredit());
        newSubject.setTeacher_id(teacher);
        return subjectRepository.save(newSubject);
    }

}
