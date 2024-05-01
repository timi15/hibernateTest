package com.example.hibernateTest.service;

import com.example.hibernateTest.entity.Subject;
import com.example.hibernateTest.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface ISubjectService {
    Optional<Subject> getSubjectById(String id);

    List<Subject> getAllSubject();

    Subject saveSubject(Subject subject, Teacher teacher);

    Subject updateSubject(Subject subjectRequest, Subject currentSubject, Teacher teacher);

    void deleteSubject(Subject subject);
}
