package com.console.springboot;

import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface Repo {
    void addEmployeeDetails();
    void displayDetails() throws IOException;
    void searchById();
    void searchByPinCode();
}
