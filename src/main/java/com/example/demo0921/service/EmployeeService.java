package com.example.demo0921.service;

import com.example.demo0921.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public interface EmployeeService {

    //查询全部员工信息
    Collection<Employee> getAll();

    //查询全部员工部门信息
    Employee getEmployeeById(int id);

    //修改员工信息
    int update(Employee employee);

    //删除员工通过id
    int delete(int id);

    //添加员工
    int save(Employee employee);

    Employee queryUserByName(@Param("userName") String userName);
}
