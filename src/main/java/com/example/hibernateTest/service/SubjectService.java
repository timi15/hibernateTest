package com.example.hibernateTest.service;

import com.example.hibernateTest.entity.Subject;
import com.example.hibernateTest.entity.Teacher;
import com.example.hibernateTest.helper.SubjectHelper;
import com.example.hibernateTest.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectHelper subjectHelper;

    public Optional<Subject> getSubjectById(String id){
        return subjectRepository.findById(id);
    }

    public List<Subject> getAllSubject(){
        return (List<Subject>) subjectRepository.findAll();
    }

    public Subject saveSubject(Subject subject, Teacher teacher){
        return subjectHelper.saveSubject(subject, teacher);
    }

    public void deleteSubjectById(String id){
        Optional<Subject> subject = getSubjectById(id);
        if(subject.isEmpty()){
            throw new RuntimeException("Id is invalid datum");
        }
        subjectRepository.delete(subject.get());
    }
}
