package com.controllers.spring.controllers;

import com.jdbc.springcomponent.entity.Employee;
import com.jdbc.springcomponent.exceptions.EmployeeException;
import com.jdbc.springcomponent.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@ComponentScan("com.jdbc.springcomponent.services")
public class EmployeeController extends HttpServlet {


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
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") String empId){
//        return employeeService.apiGetEmployeeById(empId);
        try{
            Employee employee = employeeService.apiGetEmployeeById(empId);
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/pincode/{pin}")
    public ResponseEntity<List<Employee>> getEmployeeByPinCode(@PathVariable("pin") String pin){
//        return employeeService.apiEmployeeByPinCode(pin);
        try{
            List<Employee> employeeList = employeeService.apiEmployeeByPinCode(pin);
            return  new ResponseEntity<>(employeeList, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/view")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        try{
            employeeList = employeeService.apiGetEmployees();
            return new ResponseEntity<>(employeeList, HttpStatus.OK);

        }
        catch (EmployeeException e){
            logger.error(String.valueOf(new EmployeeException()));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }


    @PostMapping("/input")
    public ResponseEntity<Object> insert(@Valid @RequestBody Employee employee){
        Employee employee1=null;
        try{
            employee1=employee;
            employee1 = employeeService.apiSave(employee);
            return ResponseEntity.ok(employee1);


        }
        catch (EmployeeException cardException){
            logger.warn(cardException.toString());
            return new ResponseEntity<>(employee1,HttpStatus.SERVICE_UNAVAILABLE);

        }
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
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
