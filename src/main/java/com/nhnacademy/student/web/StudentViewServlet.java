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

import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        studentRepository = (StudentRepository) servletContext.getAttribute(MapStudentRepository.BEAN_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (Objects.isNull(id) || id.isBlank()) {
            throw new BadRequestException("id is required");
        }

        Student student = studentRepository.getStudentById(id);
        req.setAttribute("student", student);
        req.setAttribute("view", "/WEB-INF/views/student/view.jsp");

//        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student/view.jsp");
//        rd.forward(req, resp);
    }
}
