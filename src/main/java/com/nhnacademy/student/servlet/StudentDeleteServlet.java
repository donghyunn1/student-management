package com.nhnacademy.student.servlet;

import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new RuntimeException("학생 ID가 필요합니다.");
        }

        // 학생이 존재하는지 확인
        if (!studentRepository.existById(id)) {
            throw new RuntimeException("해당 ID의 학생이 존재하지 않습니다: " + id);
        }

        studentRepository.deleteById(id);
        log.info("Student deleted: {}", id);

        resp.sendRedirect("/student/list");
    }
}
