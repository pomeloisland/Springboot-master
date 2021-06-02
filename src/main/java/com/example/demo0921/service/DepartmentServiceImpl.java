package com.example.demo0921.service;
import com.example.demo0921.dao.DepartmentDao;
import com.example.demo0921.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    DepartmentDao departmentDao;

    @Override
    public Collection<Department> getDepartments() {
        return departmentDao.getDepartments();
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentDao.getDepartmentById(id);
    }
}
