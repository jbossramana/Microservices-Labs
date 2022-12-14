package demo.boot.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.boot.dao.EmployeeRepository;
import demo.boot.model.Employee;

@Service 
public class EmpServiceImpl implements EmpService {
   
	@Autowired
    EmployeeRepository employeeRepository;
    
    @Override
    public ArrayList<Employee> findAllEmployee() {
        return (ArrayList<Employee>) employeeRepository.findAll();
    }
    
    @Override
    public Employee findAllEmployeeByID(long id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            return null;
    }
    
    @Override
    public Employee addEmployee(Employee emp) {
             employeeRepository.save(emp);
             return emp;
        }
    
    
    @Override
    public void deleteAllData() {
        employeeRepository.deleteAll();
    }
}