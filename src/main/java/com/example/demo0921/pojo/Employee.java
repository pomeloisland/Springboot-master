package com.example.demo0921.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//员工表
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    private int id;
    private String lastName;
    private String email;
    private int gender; //0:女，1：男
    private Department department;
    private int departmentId;
    private String birth;
}