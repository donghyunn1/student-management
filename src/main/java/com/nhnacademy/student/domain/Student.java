package com.nhnacademy.student.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student {

    public enum Gender {
        M,F
    }

    //아이디
    private  String id;
    //이름
    private  String name;
    //성별
    private Gender gender;
    //나이
    private  int age;
    //생성일
    private LocalDateTime createdAt;

    public Student(String id, String name, Gender gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void update(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getCreatedAtString() {
        return createdAt.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", createdAt=" + createdAt +
                '}';
    }
}
