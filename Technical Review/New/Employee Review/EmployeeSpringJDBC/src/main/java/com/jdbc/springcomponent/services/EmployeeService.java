package com.jdbc.springcomponent.services;

import com.jdbc.springcomponent.entity.AddressDetails;
import com.jdbc.springcomponent.entity.Employee;
import com.jdbc.springcomponent.exceptions.EmployeeException;
import com.jdbc.springcomponent.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Employee apiSave(Employee employee) {
        int ack = jdbcTemplate.update("insert into personal_detail values(?,?,?,?,?,?)",
                new Object[]{
                  employee.getEmployeeId(),
                  employee.getEmployeeFirstName(),
                  employee.getEmployeeMiddleName(),
                  employee.getEmployeeLastName(),
                  employee.getEmployeeEmail(),
                  employee.getEmployeeMobileNumber()
                }
                );
        int ack1 = jdbcTemplate.update("insert into ADDRESS_DETAILS  values(?,?,?,?,?,?,1)",
                new Object[]{
                   employee.getEmployeeId(),
                  employee.getPermanentAddress().getHouseNumber(),
                        employee.getPermanentAddress().getStreetAddress(),
                        employee.getPermanentAddress().getCityName(),
                        employee.getPermanentAddress().getState(),
                        employee.getPermanentAddress().getAddressPin()

                });
        int ack2=jdbcTemplate.update("insert into ADDRESS_DETAILS  values(?,?,?,?,?,?,0)",
                new Object[]{
                        employee.getEmployeeId(),
                        employee.getTemporaryAddress().getHouseNumber(),
                        employee.getTemporaryAddress().getStreetAddress(),
                        employee.getTemporaryAddress().getCityName(),
                        employee.getTemporaryAddress().getStreetAddress(),
                        employee.getTemporaryAddress().getAddressPin()
                });
        if(ack== ack1 && ack1==ack2  && ack!=0)
            return employee;
        else
            throw  new EmployeeException("Insertion failed");

    }



    @Override
    public boolean apiDoesEmployeeExists(String id) {
        Employee employee= jdbcTemplate.queryForObject("select employee_id from employee where employee_id=?",
                new Object[]{id},
                new EmployeeMapper());
        return employee!=null;
    }




    @Override
    public Employee apiGetEmployeeById(String employeeId) {
        Employee employee = jdbcTemplate.queryForObject("SELECT e.employee_id, e.first_name, e.middle_name, e.last_name, e.email, e.mobile_number, " +
                "t.house_number AS temporaryHouse, t.street AS temporaryStreet, t.city AS temporaryCity, t.state AS temporaryState, t.pin AS temporaryPin, " +
                "p.house_number AS permanentHouse, p.street AS permanentStreet, p.city AS permanentCity, p.state AS permanentState, p.pin AS permanentPin " +
                "FROM personal_detail e " +
                "INNER JOIN address_details t ON e.employee_id = t.employee_id AND t.is_permanent = 0 " +
                "INNER JOIN address_details p ON e.employee_id = p.employee_id AND p.is_permanent = 1 " +
                "WHERE e.employee_id = ?",

        new Object[]{employeeId},
                new EmployeeMapper());
        if(employee==null)
            throw new EmployeeException("employee not found");
        else
        return employee;
    }





    @Override
    public List<Employee> apiEmployeeByPinCode(String pinCode) {
        List<Employee> employee=new ArrayList<>();
                employee = jdbcTemplate.query("SELECT e.employee_id, e.first_name, e.middle_name, e.last_name, e.email, e.mobile_number, " +
                                "t.house_number AS temporaryHouse, t.street AS temporaryStreet, t.city AS temporaryCity, t.state AS temporaryState, t.pin AS temporaryPin, " +
                                "p.house_number AS permanentHouse, p.street AS permanentStreet, p.city AS permanentCity, p.state AS permanentState, p.pin AS permanentPin " +
                                "FROM personal_detail e " +
                                "INNER JOIN address_details t ON e.employee_id = t.employee_id AND t.is_permanent = 0 " +
                                "INNER JOIN address_details p ON e.employee_id = p.employee_id AND p.is_permanent = 1 " +
                                "WHERE t.pin = ? OR p.pin = ?",
                new Object[]{pinCode,pinCode},
                new EmployeeMapper());
        if(employee.size()==0)
            throw new EmployeeException("employee not found");
        else
            return employee;
    }





    @Override
    public List<Employee> apiGetEmployees() {
        List<Employee> employeeDetail;
        employeeDetail =jdbcTemplate.query("SELECT e.employee_id, e.first_name, e.middle_name, e.last_name, e.email, e.mobile_number, " +
                "t.house_number AS temporaryHouse, t.street AS temporaryStreet, t.city AS temporaryCity, t.state AS temporaryState, t.pin AS temporaryPin, " +
                        "p.house_number AS permanentHouse, p.street AS permanentStreet, p.city AS permanentCity, p.state AS permanentState, p.pin AS permanentPin " +
                        "FROM personal_detail e " +
                        "INNER JOIN address_details t ON e.employee_id = t.employee_id AND t.is_permanent = 0 " +
                        "INNER JOIN address_details p ON e.employee_id = p.employee_id AND p.is_permanent = 1 ",
                new Object[]{},
                new EmployeeMapper());
        if(employeeDetail.size()==0)
            throw new EmployeeException("empty details");
        return employeeDetail;
    }




//    @Override
    public void closeResources() {

    }




    public class EmployeeMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            AddressDetails permanent = new AddressDetails();
            AddressDetails temporary = new AddressDetails();

            employee.setEmployeeId(rs.getString("employee_id"));
            employee.setEmployeeFirstName(rs.getString("first_name"));
            employee.setEmployeeMiddleName(rs.getString("middle_name"));
            employee.setEmployeeLastName(rs.getString("last_name"));
            employee.setEmployeeEmail(rs.getString("email"));
            employee.setEmployeeMobileNumber(rs.getString("mobile_number"));

            temporary.setHouseNumber(rs.getString("temporaryHouse"));
            temporary.setStreetAddress(rs.getString("temporaryStreet"));
            temporary.setCityName(rs.getString("temporaryCity"));
            temporary.setState(rs.getString("temporaryState"));
            temporary.setAddressPin(rs.getString("temporaryPin"));

            permanent.setHouseNumber(rs.getString("permanentHouse"));
            permanent.setStreetAddress(rs.getString("permanentStreet"));
            permanent.setCityName(rs.getString("permanentCity"));
            permanent.setState(rs.getString("permanentState"));
            permanent.setAddressPin(rs.getString("permanentPin"));

            employee.setPermanentAddress(permanent);
            employee.setTemporaryAddress(temporary);

            return employee;
        }
    }

}