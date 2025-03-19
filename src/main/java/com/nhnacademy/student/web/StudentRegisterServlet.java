package com.nhnacademy.student.web;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.BadRequestException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.repository.impl.MapStudentRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        studentRepository = (StudentRepository) config.getServletContext().getAttribute(MapStudentRepository.BEAN_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", "/student/register");
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student/register.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String genderString = req.getParameter("gender");
        String ageString = req.getParameter("age");

        if (StringUtils.isEmpty(id)) {
            throw new BadRequestException("id is required");
        } else if (StringUtils.isEmpty(name)) {
            throw new BadRequestException("name is required");
        } else if (StringUtils.isEmpty(ageString)) {
            throw new BadRequestException("age is required");
        } else if (StringUtils.isEmpty(genderString)) {
            throw new BadRequestException("gender is required");
        }

        Student.Gender gender = Student.Gender.valueOf(genderString);
        int age = Integer.parseInt(ageString);

        Student student = new Student(id, name, gender, age);
        studentRepository.save(student);

//        resp.sendRedirect("/student/view?id=%s".formatted(id));
        req.setAttribute("view", "redirect:/student/view.do?id=%s".formatted(id));
    }
}
