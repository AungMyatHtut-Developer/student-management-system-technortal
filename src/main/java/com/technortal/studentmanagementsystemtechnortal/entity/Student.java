package com.technortal.studentmanagementsystemtechnortal.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Student {

    private int id;
    private String name;
    private String address;
    private int age;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createUser;
    private String updateUser;
    private boolean isDelete;

    public Student() {}

}
