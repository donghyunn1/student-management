package com.nhnacademy.student.web;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.BadRequestException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.repository.impl.MapStudentRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@WebServlet(urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        studentRepository = (StudentRepository) servletContext.getAttribute(MapStudentRepository.BEAN_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET /student/update?id=student1
        String id = req.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            throw new BadRequestException("id is required");
        }

        Student student = studentRepository.getStudentById(id);

        req.setAttribute("student", student);
        req.setAttribute("action","/student/update");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/student/register.jsp");
        dispatcher.forward(req, resp);

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
        studentRepository.update(student);

//        resp.sendRedirect("/student/view?id=%s".formatted(id));
        req.setAttribute("view", "redirect:/student/view.do?id=%s".formatted(id));
    }
}
