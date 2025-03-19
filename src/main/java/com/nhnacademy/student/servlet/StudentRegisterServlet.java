package com.nhnacademy.student.servlet;

import com.nhnacademy.student.Gender;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/student/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String genderValue = req.getParameter("gender");
        String ageStr = req.getParameter("age");

        if (Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(genderValue) || Objects.isNull(ageStr)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터가 누락되었습니다.");
            return;
        }

        if (id.trim().isEmpty() || name.trim().isEmpty() || genderValue.trim().isEmpty() || ageStr.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터가 비어있습니다.");
            return;
        }

        if (studentRepository.existById(id)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "이미 존재하는 학생 ID입니다.");
            return;
        }


        Gender gender = "남".equals(genderValue) ? Gender.M : Gender.F;
        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 0) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "나이는 양수여야 합니다.");
                return;
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "나이는 숫자여야 합니다.");
            return;
        }

        Student student = new Student(id, name, gender, age);
        studentRepository.save(student);

        resp.sendRedirect("/student/view?id=" + id);
    }
}
