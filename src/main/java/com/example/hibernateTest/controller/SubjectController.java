package com.example.hibernateTest.controller;

import com.example.hibernateTest.entity.Subject;
import com.example.hibernateTest.entity.Teacher;
import com.example.hibernateTest.service.ISubjectService;
import com.example.hibernateTest.service.ITeacherService;
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
    ISubjectService subjectService;

    @Autowired
    ITeacherService teacherService;

    @Operation(summary = "return subject by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned subject by id"),
            @ApiResponse(responseCode = "204", description = "no content")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> getSubjectById(@PathVariable String id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "return all subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned all teachers"),
            @ApiResponse(responseCode = "204", description = "no content")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subject> getAllSubject() {
        return subjectService.getAllSubject();
    }

    @Operation(summary = "create a new subject")
    @ApiResponse(responseCode = "200", description = "subject is saved", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Subject.class))
    })
    @PostMapping(value = "/addSubject/{teacherId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject, @PathVariable int teacherId) {
        Optional<Teacher> teacher = teacherService.getTeacherById(teacherId);
        return teacher.map(value -> new ResponseEntity<>(subjectService.saveSubject(subject, value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "modify subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "subject is modified", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Subject.class))
            }),
            @ApiResponse(responseCode = "404", description = "subject or teacher is not found")
    })
    @PutMapping(value = "/{id}/{teacherId}")
    public ResponseEntity<Subject> modifySubjectById(@PathVariable String id, @PathVariable int teacherId, @RequestBody Subject subjectRequest){
        Optional<Subject> currentSubject = subjectService.getSubjectById(id);
        Optional<Teacher> teacher = teacherService.getTeacherById(teacherId);
        if(currentSubject.isEmpty() || teacher.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subjectService.updateSubject(subjectRequest,currentSubject.get(),teacher.get() ), HttpStatus.OK);
    }

    @Operation(summary = "delete subject by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "This subject not found"),
            @ApiResponse(responseCode = "204", description = "no content")
    })
    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> deleteSubjectById(@PathVariable String id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        if(subject.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        subjectService.deleteSubject(subject.get());
        return ResponseEntity.status(204).build();
    }
}
