package com.assignment.crudmicroservice.crudmicroservice.controller;

import com.assignment.crudmicroservice.crudmicroservice.dao.EmployeeDAO;
import com.assignment.crudmicroservice.crudmicroservice.list.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
public class EmployeeController {
    @Autowired
    EmployeeDAO repository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${crud.insert}")
    private boolean isInsert;


    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){

        if(!isInsert){
            logger.error("Unable to update. Employee with id {} not found.");
            return new ResponseEntity(new Exception("Unable to insert since insert opration is disabled "),
                    HttpStatus.NOT_ACCEPTABLE);
        }

        Integer status = repository.insert(employee);

        return new ResponseEntity<Integer>(status, HttpStatus.OK);
    }
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployees(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
        Employee currentEmployee = repository.findById(id);
        if (currentEmployee == null) {
            logger.error("Unable to update. Employee with id {} not found.", id);
            return new ResponseEntity(new Exception("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentEmployee.setName(employee.getName());
        currentEmployee.setAddress(employee.getAddress());

        repository.update(currentEmployee);

        return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
    }
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id){
        Employee currentEmployee = repository.findById(id);
        if (currentEmployee == null) {
            logger.error("Unable to update. Employee with id {} not found.", id);
            return new ResponseEntity(new Exception("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
