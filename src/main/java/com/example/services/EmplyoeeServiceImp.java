package com.example.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.model.EmployeePojo;
import com.example.repository.EmplyoeeRepo;


@Service
public class EmplyoeeServiceImp implements EmplyoeeService{

	@Autowired
	private EmplyoeeRepo emplyoeeRepo;
	//listing all employee
	@Override
	public List<EmployeePojo> listAllEmplyoee() {
		System.out.println("EmployeeServiceImp");
		return emplyoeeRepo.findAll();
	}
     //adding new employee
	@Override
	public void addEmplyoee(EmployeePojo emp) {
		this.emplyoeeRepo.save(emp);
	}
	//getting employee object by id 
	@Override
	public EmployeePojo getEmployeeById(long id) {
		Optional < EmployeePojo > optional =  emplyoeeRepo.findById(id);

		EmployeePojo employee = null;
	    if (optional.isPresent()) {
	        employee = optional.get();
	    } else {
	        throw new RuntimeException(" Employee not found for id :: " + id+"  Enter valid id");
	    }
	    return employee;
	    
	}
	//in delete we does not make any effort to find corresponding emplyoeepojo object
	// beacuse JPArepo has inbuilt method to delete by id
	
	@Override
	public void deleteEmployeeById(long id) {
		emplyoeeRepo.deleteById(id);
	}
	
	@Override
    public Page <EmployeePojo > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();

        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.emplyoeeRepo.findAll(pageable);
    }
}


