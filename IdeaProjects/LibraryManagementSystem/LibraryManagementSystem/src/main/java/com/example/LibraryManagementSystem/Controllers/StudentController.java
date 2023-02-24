package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.DTOs.StudentUpdateMobRequestDto;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){

        return studentService.createStudent(student);
    }
    @GetMapping("/get_user")
    public String getNameByEmail(@RequestParam("email") String email){
        return studentService.findNameByEmail(email);
    }

    @PostMapping("/update_mob")
    public String updateMob(@RequestBody StudentUpdateMobRequestDto studentReqDto){
        return studentService.updateMobNo(studentReqDto);
    }

}
