package demo.boot.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import demo.boot.model.Employee;
  

  
// @Repository is a Spring annotation that
// indicates that the decorated class is a repository.
@RepositoryRestResource(itemResourceRel = "employee", path = "employee")
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
   
}
