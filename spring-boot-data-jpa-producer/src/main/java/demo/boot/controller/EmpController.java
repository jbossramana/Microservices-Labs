package demo.boot.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.boot.model.Employee;
import demo.boot.service.EmpServiceImpl;

  
@RestController
@RequestMapping("employee")
public class EmpController {
    
    @Autowired
    EmpServiceImpl empServiceImpl;
  
    @PostMapping
    public Employee add(@RequestBody Employee emp) {
        return empServiceImpl.addEmployee(emp);
    }
  
    @GetMapping
    public ArrayList<Employee> getAllEmployee() {
        return empServiceImpl.findAllEmployee();
    }
  
    @GetMapping("{id}")
    public Employee getEmployeeUsingId(@PathVariable long id) {
        return empServiceImpl.findAllEmployeeByID(id);
    }
  
    @DeleteMapping
    public void delete() {
        empServiceImpl.deleteAllData();
    }
}