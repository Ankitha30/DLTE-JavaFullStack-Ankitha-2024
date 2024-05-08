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
        int ack = jdbcTemplate.update("insert into personal_details values(?,?,?,?,?,?)",
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
        Employee employee= jdbcTemplate.queryForObject("select dmployee_id from employee where employee_id=?",
                new Object[]{id},
                new EmployeeMapper());
        return employee!=null;
    }




    @Override
    public Employee apiGetEmployeeById(String employeeId) {
        Employee employee = jdbcTemplate.queryForObject("select e.employee_id,e.first_name,e.middle_name,e.last_name,e.email,e.mobile-num,"+
                        "t.house_number as temporaryHouse,t.street as temporaryStreet,t.city as temporaryCity,t.state as temporaryState,t.pin as temporaryPin,"+
                        "p.house_number as permanentHouse,p.street as permanentStreet,p.city as permanentCity ,p.state as permanentState,p.pin  as permanentPin,"+
                        "from personal_details e"+
                        "inner join address_details t on e.employee_id=t.employee_id and t.is_permanent=0"+
                        "inner join address_details p on e.employee_id =p.employee_id and p.is_permanent=0"+
                        "where e.employee_id=?",
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
                employee = jdbcTemplate.query("select e.employee_id,e.first_name,e.middle_name,e.last_name,e.email,e.mobile-num,"+
                        "t.house_number as temporaryHouse,t.street as temporaryStreet,t.city as temporaryCity,t.state as temporaryState,t.pin as temporaryPin,"+
                        "p.house_number as permanentHouse,p.street as permanentStreet,p.city as permanentCity ,p.state as permanentState,p.pin  as permanentPin,"+
                        "from personal_details e"+
                        "inner join address_details t on e.employee_id=t.employee_id and t.is_permanent=0"+
                        "inner join address_details p on e.employee_id =p.employee_id and p.is_permanent=0"+
                        "where t.pin=? or p.pin=?",
                new Object[]{pinCode},
                new EmployeeMapper());
        if(employee.size()==0)
            throw new EmployeeException("employee not found");
        else
            return employee;
    }





    @Override
    public List<Employee> apiGetEmployees() {
        List<Employee> employeeDetail;
        employeeDetail =jdbcTemplate.query("select e.employee_id,e.first_name,e.middle_name,e.last_name,e.email,e.mobile-num,"+
                "t.house_number,t.street,t.city,t.state,t.pin ,"+
                "p.house_number,p.street,p.city,p.state,p.pin ,"+
                "from personal_details e"+
                "inner join address_details t on e.employee_id=t.employee_id and t.is_permanent=0"+
                "inner join address_details p on e.employee_id =p.employee_id and p.is_permanent=0",
                new Object[]{},
                new EmployeeMapper());
        if(employeeDetail.size()==0)
            throw new EmployeeException("empty details");
        return employeeDetail;
    }




//    @Override
//    public void closeResources() {
//
//    }





    private class EmployeeMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee =  new Employee();
            AddressDetails permanent = new AddressDetails();
            AddressDetails temporary = new AddressDetails();
            employee.setEmployeeId(rs.getString(1));
            employee.setEmployeeFirstName(rs.getNString(2));
            employee.setEmployeeMiddleName(rs.getString(3));
            employee.setEmployeeLastName(rs.getString(4));
            employee.setEmployeeMobileNumber(rs.getNString(5));
            employee.setEmployeeEmail(rs.getString(6));
            employee.setEmployeeMobileNumber(rs.getNString(7));
            permanent.setHouseNumber(rs.getString(8));
            permanent.setStreetAddress(rs.getString(9));
            permanent.setCityName(rs.getString(10));
            permanent.setState(rs.getString(11));
            permanent.setAddressPin(rs.getString(12));
            temporary.setHouseNumber(rs.getString(13));
            temporary.setStreetAddress(rs.getString(14));
            temporary.setCityName(rs.getString(15));
            temporary.setState(rs.getString(16));
            temporary.setAddressPin(rs.getString(17));
            employee.setPermanentAddress(permanent);
            employee.setTemporaryAddress(temporary);
            return employee;
        }
    }
}