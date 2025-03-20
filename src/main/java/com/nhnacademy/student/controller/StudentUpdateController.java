package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.BadRequestException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.repository.impl.JsonStudentRepository;
import com.nhnacademy.student.repository.impl.MapStudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class StudentUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute(MapStudentRepository.BEAN_NAME);
        //아이디
        String id = req.getParameter("id");
        //이름
        String name = req.getParameter("name");
        //성별
        String genderString = req.getParameter("gender");
        //나이
        String ageString = req.getParameter("age");

        if(Objects.isNull(id) || id.isBlank()) {
            throw new BadRequestException("id cannot be blank");
        }
        if(Objects.isNull(name) || name.isBlank()) {
            throw new BadRequestException("name cannot be blank");
        }
        if(Objects.isNull(genderString) || genderString.isBlank()) {
            throw new BadRequestException("gender cannot be blank");
        }
        if(Objects.isNull(ageString) || ageString.isBlank()) {
            throw new BadRequestException("age cannot be blank");
        }

        Student.Gender gender = Student.Gender.valueOf(genderString);

        int age = Integer.parseInt(ageString);

        //생성일
        Student student = new Student(id, name, gender, age);
        studentRepository.update(student);
        return "redirect:/student/view.do?id=%s".formatted(id);
    }
}
