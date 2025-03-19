package com.nhnacademy.student;

import com.nhnacademy.student.repository.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        for(int i=1; i<=10; i++){
            // ... student 1 ~ 10 생성하기
            // 나이 : random 처리 : 20~30

            studentRepository.save(student);
        }
        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기

    }
}
