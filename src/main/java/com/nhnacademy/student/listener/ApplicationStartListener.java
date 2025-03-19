package com.nhnacademy.student.listener;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.impl.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@WebListener
public class ApplicationStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();
        Random random = new Random();

        for(int i=1; i<=20; i++){
            String id = "student%d".formatted(i);
            String name = "학생%d".formatted(i);
            Student.Gender gender = i % 2 ==0 ? Student.Gender.M : Student.Gender.F;
            int age = random.nextInt(20,31); // 20~30 사이의 랜덤 나이

            Student student = new Student(id, name, gender, age);
            log.info("created-student:{}",student);

            studentRepository.save(student);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        context.setAttribute(MapStudentRepository.BEAN_NAME, studentRepository);
    }
}
