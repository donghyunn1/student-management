package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.BadRequestException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.repository.impl.MapStudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class StudentUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute(MapStudentRepository.BEAN_NAME);
        String id = req.getParameter("id");
        if(Objects.isNull(id) || id.isEmpty()) {
            throw new BadRequestException("id is required");
        }
        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)) {
            throw new StudentNotFoundException(id);
        }
        req.setAttribute("action", "/student/update");
        req.setAttribute("student", student);

        return "/WEB-INF/views/student/register.jsp";
    }
}
