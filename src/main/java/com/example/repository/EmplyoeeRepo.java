package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.EmployeePojo;
@Repository
public interface EmplyoeeRepo extends JpaRepository<EmployeePojo, Long>{
   
}
