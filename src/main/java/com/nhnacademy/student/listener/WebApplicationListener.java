package com.nhnacademy.student.listener;

import com.nhnacademy.student.Gender;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.repository.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.Random;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();
        Random random = new Random();

        for(int i=1; i<=10; i++){
            String id = "student" + i;
            String name = "아카데미" + i;
            Gender gender = (i % 2 == 0) ? Gender.M : Gender.F;
            int age = random.nextInt(11) + 20; // 20~30 사이의 랜덤 나이

            Student student = new Student(id, name, gender, age);
            studentRepository.save(student);
        }

        context.setAttribute("studentRepository", studentRepository);
    }
}
