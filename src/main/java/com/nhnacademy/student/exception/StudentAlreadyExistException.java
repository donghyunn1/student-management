package com.nhnacademy.student.exception;

public class StudentAlreadyExistException extends RuntimeException {
    public StudentAlreadyExistException(String id) {
        super("Student with id %s already exists.".formatted(id));
    }
}