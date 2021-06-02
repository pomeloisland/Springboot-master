package com.example.demo0921.controller;


import com.example.demo0921.pojo.Department;
import com.example.demo0921.pojo.Employee;
import com.example.demo0921.service.DepartmentService;
import com.example.demo0921.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    //Model 起到返回给前端的作用
    @GetMapping("/emps")
    public Object list(Model model) {
        Collection<Employee> employees = employeeService.getAll();
        model.addAttribute("emps", employees);
        System.out.println(employees);
        return "emp/list";
    }

    @RequestMapping("/emp")
    //添加一个Model返回到前端来传递参数
    public String toAddpage(Model model) {
    //查出所有部门数据
        Collection<Department> departments = departmentService.getDepartments();
        model.addAttribute("departments", departments);
        System.out.println(departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println("save=>" + employee);
        //添加员工
        employeeService.save(employee);
        //redirect重定向到emps
        return "redirect:/emps";
    }

    //到员工修改页面
    @RequestMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") int id, Model model) {
        //查出选中的员工数据
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("emp", employee);
        //查出所有部门数据
        Collection<Department> departments = departmentService.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        employeeService.update(employee);
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") int id, Model model) {
        employeeService.delete(id);
        return "redirect:/emps";
    }

}
