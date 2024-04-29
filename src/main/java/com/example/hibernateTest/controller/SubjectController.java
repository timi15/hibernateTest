package com.example.hibernateTest.controller;

import com.example.hibernateTest.entity.Subject;
import com.example.hibernateTest.entity.Teacher;
import com.example.hibernateTest.service.SubjectService;
import com.example.hibernateTest.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    TeacherService teacherService;

    @Operation(summary = "return subject by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned subject by id"),
            @ApiResponse(responseCode = "204", description = "no content", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))
            })
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> getSubjectById(@PathVariable String id){
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "return all subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned all teachers"),
            @ApiResponse(responseCode = "204", description = "no content", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))
            })
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @Operation(summary = "create a new subject")
    @ApiResponse(responseCode = "200", description = "subject is saved", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Subject.class))
    })
    @PostMapping(value = "/addSubject/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject, @PathVariable int id){
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(value -> new ResponseEntity<>(subjectService.saveSubject(subject, value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "delete subject by id")
    @ApiResponse(responseCode = "404", description = "not found", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Subject.class))
    })
    @DeleteMapping("/{id}")
    private void deleteSubjectById(@PathVariable String id){
        subjectService.deleteSubjectById(id);
    }
}
