package com.jdbc.springcomponent.controllers;


import com.jdbc.springcomponent.entity.Employee;
import com.jdbc.springcomponent.exceptions.EmployeeException;
import com.jdbc.springcomponent.services.EmployeeService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/insert")
    public Employee save(@RequestBody Employee employee){
      Employee employee1 = null;
      try{

          employee1 = employeeService.apiSave(employee);
      }
      catch (EmployeeException employeeException){
          logger.warn(employeeException.toString());
      }
      return employee1;
    }

    @GetMapping("/id/{empId}")
    public  Employee getEmployeeById(@PathVariable("empId") String empId){
        return employeeService.apiGetEmployeeById(empId);
    }

    @GetMapping("/pincode/{pin}")
    public List<Employee> getEmployeeByPinCode(@PathVariable("pin") String pin){
        return employeeService.apiEmployeeByPinCode(pin);
    }


    @GetMapping("/view")
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        try{
            employeeList = employeeService.apiGetEmployees();
        }
        catch (EmployeeException e){
            logger.error(String.valueOf(new EmployeeException()));
        }
        return employeeList;
    }






//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//   @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String,String>handlValidationExceptions(MethodArgumentNotValidException ex){
//        Map<String,String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error)-> {
//            String fieldName = ((FieldError) error).getField();
//            String errormessage = error.getDefaultMessage();
//            errors.put(fieldName, errormessage);
//
//
//        });
//        return errors;
//    }


}
