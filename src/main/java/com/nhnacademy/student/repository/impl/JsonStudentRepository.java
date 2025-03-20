package com.nhnacademy.student.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.StudentAlreadyExistException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;
    public static final String BEAN_NAME = "jsonStudentRepository";

    //json file 저장 경로
    private static final String JSON_FILE_PATH = "C:/Users/82102/Desktop/NHN아카데미/student/src/main/json/student.json";
    private List<Student> students;

    public JsonStudentRepository() {
        objectMapper = new ObjectMapper();
        //LocalDatetime json 직열화/역직렬화 가능하도록 설정
        objectMapper.registerModule(new JavaTimeModule());
        //todo JSON_FILE_PATH 경로에 json 파일이 존재하면 삭제 합니다.
        File file = new File(JSON_FILE_PATH);
        if (file.exists()) {
            if (file.delete()) {
                log.debug("Deleted JSON file");
            } else {
                log.error("Failed to delete JSON file");
            }
        } else {
            log.debug("JSON file not found");
        }

    }

    private synchronized List<Student> readJsonFile() {
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            students = new ArrayList<>();
            return students;
        }
        //json read & 역직렬화 ( json string -> Object )
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {
            });
            return students;
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 오류: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.info("readjsonfile오류");
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<Student> studentList) {
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH);
        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter, studentList);
        } catch (IOException e) {
            log.info("writejsonfile오류");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        //json String -> Object 형태로 변화 (직렬화)
        List<Student> students = readJsonFile();
        //List에 student 추가
        if (existById(student.getId())) {
            // 예외 처리, 학생 id 중복
            throw new StudentAlreadyExistException(student.getId());
        }
        try {
            students.add(student);
            writeJsonFile(students);
        } catch (RuntimeException e) {
            log.error("학생 저장 중 오류 발생: {}", e.getMessage(), e);
            throw e; // 또는 좀 더 구체적인 예외로 변환
        }
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();
        boolean updated = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) { // ID 비교
                students.get(i).update(student.getName(), student.getGender(), student.getAge()); // 직접 리스트의 객체 수정
                updated = true;
                break;
            }
        }
        if (!updated) {
            throw new StudentNotFoundException(student.getId());
        }
        writeJsonFile(students);
    }

    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();
        boolean removed = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                Student student = students.remove(i);
                removed = true;
                break; // 삭제 후 루프 종료
            }
        }

        if (removed) {
            writeJsonFile(students);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        Student student = null;
        for (Student s : students) {
            if (s.getId().equals(id)) {
                student = s;
            }
        }
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException(id);
        }
        return student;
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> students = readJsonFile();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
