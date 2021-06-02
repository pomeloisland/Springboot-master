package com.example.demo0921.service;

import com.example.demo0921.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface DepartmentService {
    //获取所有部门信息
    Collection<Department> getDepartments();
    //通过id得到部门
    Department getDepartmentById(int id);
}
