package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTOs.StudentUpdateMobRequestDto;
import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Models.Card;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String createStudent(Student student) {

        Card card = new Card();

        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCardStatus(card);
        studentRepository.save(student);
        return "Student & Card created successfully";
    }

    public String findNameByEmail(String email) {
        Student student = studentRepository.findNameByEmail(email);
        return student.getName();
    }


    public String updateMobNo(StudentUpdateMobRequestDto studentReqDto) {
        Student originalStudent = studentRepository.findById(studentReqDto.getId()).get();

        originalStudent.setMobileNumber(studentReqDto.getMobNo());

        studentRepository.save(originalStudent);
        return "Student has been updated successfully";
    }
}
