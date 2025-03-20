package com.nhnacademy.student.listener;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.impl.JsonStudentRepository;
import com.nhnacademy.student.repository.impl.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;

@Slf4j
@WebListener
public class ApplicationStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

            log.info("애플리케이션 시작 리스너 초기화 시작");
            StudentRepository studentRepository = new JsonStudentRepository();
            ServletContext context = sce.getServletContext();
            Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            try {
                String id = "student%d".formatted(i);
                String name = "학생%d".formatted(i);
                Student.Gender gender = i % 2 == 0 ? Student.Gender.M : Student.Gender.F;
                int age = random.nextInt(20, 31);

                Student student = new Student(id, name, gender, age);
                log.info("created-student:{}", student);

                studentRepository.save(student);
                log.info("저장완료: {}", id);
            } catch (Exception e) {
                log.error("학생 {} 저장 중 오류 발생: {}", i, e.getMessage(), e);
                // 전체 리스너가 중단되지 않도록 다음 학생으로 계속 진행
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

            context.setAttribute(MapStudentRepository.BEAN_NAME, studentRepository);
            log.info("애플리케이션 시작 리스너 초기화 완료");

    }
}


