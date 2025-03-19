package com.nhnacademy.student.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String id) {
        super("Student with id %s not found".formatted(id));
    }
}
