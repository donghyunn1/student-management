package com.nhnacademy.student.repository.impl;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.StudentAlreadyExistException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.repository.StudentRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRepository implements StudentRepository {
    public static final String BEAN_NAME = "mapStudentRepository";
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        if (existById(student.getId())) {
            throw new StudentAlreadyExistException(student.getId());
        }
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        Student selectedStudent = studentsMap.get(student.getId());
        if (Objects.isNull(selectedStudent)) {
            throw new StudentNotFoundException(student.getId());
        }

        selectedStudent.update(student.getName(), student.getGender(), student.getAge());
    }

    @Override
    public void deleteById(String id) {
        if (!existById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentsMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        Student selectedstudent = studentsMap.get(id);
        if (Objects.isNull(selectedstudent)) {
            throw new StudentNotFoundException(id);
        }
        return selectedstudent;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();
//        Collection<Student> studentCollection = studentsMap.values();
//        for (Student student : studentCollection) {
//            studentList.add(student);
//        }

        studentList.addAll(studentsMap.values());
        Comparator<Student> comparator = (((o1, o2) -> {
            return o1.getCreatedAt().isBefore(o2.getCreatedAt()) ? 1 : -1;
        }));
        Collections.sort(studentList, comparator);
        return studentList;
    }

    @Override
    public boolean existById(String id) {
        return studentsMap.containsKey(id);
    }
}
