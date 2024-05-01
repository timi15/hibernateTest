package com.example.hibernateTest.service.impl;

import com.example.hibernateTest.entity.Subject;
import com.example.hibernateTest.entity.Teacher;
import com.example.hibernateTest.helper.SubjectHelper;
import com.example.hibernateTest.repository.SubjectRepository;
import com.example.hibernateTest.service.ISubjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements ISubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectHelper subjectHelper;

    @Override
    public Optional<Subject> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }

    @Override
    public List<Subject> getAllSubject() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public Subject saveSubject(Subject subject, Teacher teacher) {
        return subjectHelper.saveSubject(subject, teacher);
    }

    @Transactional
    @Override
    public Subject updateSubject(Subject subjectRequest, Subject currentSubject, Teacher teacher) {
        currentSubject.setTitle(subjectRequest.getTitle());
        currentSubject.setCredit(subjectRequest.getCredit());
        currentSubject.setTeacher_id(teacher);
        return subjectHelper.saveSubject(currentSubject, teacher);
    }

    @Override
    public void deleteSubject(Subject subject) {
        subjectRepository.delete(subject);
    }
}
