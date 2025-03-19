package com.nhnacademy.student.servlet;

import com.nhnacademy.student.Student;
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
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // id null check
        String id = req.getParameter("id");
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "학생 ID가 필요합니다.");
            return;
        }

        // student 조회
        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 ID의 학생이 존재하지 않습니다: " + id);
            return;
        }

        log.info("Student found: {}", student);
        req.setAttribute("student", student);

        // /student/view.jsp <-- forward
        req.getRequestDispatcher("/student/view.jsp").forward(req, resp);
    }
}
