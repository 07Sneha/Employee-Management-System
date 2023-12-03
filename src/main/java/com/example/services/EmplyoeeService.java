package com.example.services;
import java.util.List;

import org.springframework.data.domain.Page;

//import org.springframework.stereotype.Service;

import com.example.model.EmployeePojo;

public interface EmplyoeeService {
	List<EmployeePojo> listAllEmplyoee();
	void addEmplyoee(EmployeePojo emp);
	EmployeePojo getEmployeeById(long id);
	void deleteEmployeeById(long id);
	 Page < EmployeePojo > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
