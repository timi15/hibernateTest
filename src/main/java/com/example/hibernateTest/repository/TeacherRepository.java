package com.example.hibernateTest.repository;

import com.example.hibernateTest.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

    @Query("SELECT t FROM Teacher t WHERE t.dateOfBirth = (SELECT MIN(t.dateOfBirth) FROM Teacher t)")
    Teacher findTheYoungestTeacher();

    @Query("SELECT s.title FROM Teacher t INNER JOIN Subject s ON t.id = s.teacher_id.id WHERE t.id = :id")
    List<String> listOfSubject(@Param("id") int id);

}
