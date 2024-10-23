package com.example.cachedemo.Repositories;

import com.example.cachedemo.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
