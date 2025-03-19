package com.nhnacademy.student.web;

import com.nhnacademy.student.exception.BadRequestException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.repository.impl.MapStudentRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;
@Slf4j
@WebServlet(urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = getServletContext();
        studentRepository = (StudentRepository) context.getAttribute(MapStudentRepository.BEAN_NAME);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        log.error("{}", id);
        if(Objects.isNull(id) || id.isBlank()) {
            throw new BadRequestException("Invalid ID");
        }
        studentRepository.deleteById(id);
        resp.sendRedirect("/student/list");
    }
}