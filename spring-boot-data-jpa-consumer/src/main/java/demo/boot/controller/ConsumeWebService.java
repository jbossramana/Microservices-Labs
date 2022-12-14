package demo.boot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import demo.boot.model.Employee;

@RestController
@RequestMapping("employee")
public class ConsumeWebService {
   @Autowired
   RestTemplate restTemplate;

   @GetMapping
   public List<Employee> getEmployees() {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);
      
      ResponseEntity<List<Employee>> response = restTemplate.exchange("http://localhost:8080/employee", HttpMethod.GET,entity, 
      new ParameterizedTypeReference<List<Employee>>() {});
      return response.getBody();
     }
   

   @GetMapping("/{id}")
   public Employee getEmployees(@PathVariable("id") String id) {
	  String url = "http://localhost:8080/employee/{id}";
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);
      
      Map<String, String> urlParams = new HashMap<>();
      urlParams.put("id", id);
  
      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
      
      ResponseEntity<Employee> response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET,entity, Employee.class);
      return response.getBody();
  
   }
   
   
   @PostMapping
   public Employee createProducts(@RequestBody Employee employee) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Employee> entity = new HttpEntity<Employee>(employee ,headers);
      
      return restTemplate.exchange(
         "http://localhost:8080/employee", HttpMethod.POST, entity, Employee.class).getBody();
   }
   
   
   @PutMapping
   public String updateProduct(@PathVariable("id") String id, @RequestBody Employee employee) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Employee> entity = new HttpEntity<Employee>(employee,headers);
      
      return restTemplate.exchange(
         "http://localhost:8080/employee/"+id, HttpMethod.PUT, entity, String.class).getBody();
   }
   
}
