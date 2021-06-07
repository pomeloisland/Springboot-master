package com.example.demo0921.service;

import com.example.demo0921.dao.EmployeeDao;
import com.example.demo0921.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public Collection<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }


    @Override
    public int update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public int delete(int id) {
        return employeeDao.delete(id);
    }

    @Override
    public int save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee queryUserByName(String lastName,String email) {
        return employeeDao.queryUserByName(lastName,email);
    }


}
