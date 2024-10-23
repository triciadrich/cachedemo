package com.example.cachedemo.Services;

import com.example.cachedemo.Custom.CustomExceptions;
import com.example.cachedemo.Models.Employee;
import com.example.cachedemo.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Cacheable(value = "employees", key = "#employeeId", sync = true)
    public Employee getEmployee(Integer employeeId) {
        fakeDelay("Fetching");
        return employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new CustomExceptions.ResourceNotFoundException(
                                "Employee not found:" + employeeId));
    }
    @Cacheable(value = "employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @CachePut(value = "employees", key = "#employee.id")
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    @CacheEvict(value = "employees", allEntries = true)
    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public void fakeDelay(String message){
        for (int i = 0; i < 5; i++){
            System.out.println(message);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
