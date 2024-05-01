package com.example.hibernateTest.controller;

import com.example.hibernateTest.entity.Teacher;
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
@RequestMapping(value = "/api/v1/teacher")
public class TeacherController {

    @Autowired
    ITeacherService teacherService;

    @Operation(summary = "return teacher by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned teacher by id"),
            @ApiResponse(responseCode = "204", description = "no content", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))
            })
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "return all teachers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned all teachers"),
            @ApiResponse(responseCode = "204", description = "no content", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))
            })
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @Operation(summary = "return the younest teacher")
    @ApiResponse(responseCode = "200", description = "returned the youngest teacher", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))
    })
    @GetMapping(value = "/youngestTeacher", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getTheYoungestTeacher() {
        return new ResponseEntity<>(teacherService.getTheYoungestTeacher(), HttpStatus.OK);
    }

    @Operation(summary = "return list of subject that the teacher has")
    @ApiResponse(responseCode = "200", description = "returned list of subject", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))
    })
    @GetMapping("/subject/{id}")
    @Deprecated
    public List<String> getSubjectList(@PathVariable int id){
        return teacherService.subjectList(id);
    }

    @Operation(summary = "create a new teacher")
    @ApiResponse(responseCode = "200", description = "teacher is saved", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Teacher.class))
    })
    @PostMapping(value = "/addTeacher", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        return new ResponseEntity<>(teacherService.saveTeacher(teacher), HttpStatus.OK);
    }
    @Operation(summary = "modify teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "teacher is modified", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Teacher.class))
            }),
            @ApiResponse(responseCode = "404", description = "teacher is not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> modifyTeacherById(@PathVariable int id, @RequestBody Teacher teacherRequest){
        Optional<Teacher> currentTeacher = teacherService.getTeacherById(id);
        return currentTeacher.map(teacher -> new ResponseEntity<>(teacherService.updateTeacher(teacherRequest, teacher), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "delete teacher by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "no content", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Teacher.class))
            }),
            @ApiResponse(responseCode = "500", description = "internal server error", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Teacher.class))
            })
    })
    @DeleteMapping("/{id}")
    public HttpStatus deleteTeacherById(@PathVariable Integer id){
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        if(!teacherService.subjectList(id).isEmpty() || teacher.isEmpty()){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        teacherService.deleteTeacher(teacher.get());
        return HttpStatus.NO_CONTENT;
    }

}
