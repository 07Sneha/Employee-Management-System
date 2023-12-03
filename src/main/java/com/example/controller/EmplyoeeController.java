package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.model.EmployeePojo;
import com.example.services.EmplyoeeServiceImp;

@Controller
public class EmplyoeeController {
      @Autowired
	  EmplyoeeServiceImp empService;
	
	@GetMapping("/homeview")
	public String viewHomePage(Model model)
	{ //without pagination code is
	  //model. addAttribute("ListAllEmplyoee", empService.listAllEmplyoee());
	  //return "index";
		return findPaginated(1,"firstName", "asc", model);//this method is defined below 
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String addNewEmpyoee(Model model)
	{  EmployeePojo emp= new EmployeePojo();
		model.addAttribute("addEmplyoee", emp);
		return "newEmplyoeedataEntry";
	}
	
	 @PostMapping("/saveEmployee")
	    public String saveEmployee(@ModelAttribute("employee") EmployeePojo employee) {
	        // save employee to database
	        empService.addEmplyoee(employee);
	        return "redirect:/homeview";
	 }
	 
	 @GetMapping("/showFormForUpdate/{id}")
	 //@PathVariable(value = "id") to get id from get mapping since data in mysql is searched with id only 
	 public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
              // get employee from the service
	        EmployeePojo employee = empService.getEmployeeById(id);
             // same as adding new Emplyoee but need to give it on particular id 
	        // set employee as a model attribute to pre-populate the form
	        model.addAttribute("employee", employee);
	        return "update_employee";
	    }
	 
	 @GetMapping("/deleteEmployee/{id}")
	    public String deleteEmployee(@PathVariable(value = "id") long id) {

	        // call delete employee method 
		     empService.deleteEmployeeById(id);
	        return "redirect:/";
	    }
	 
	 @GetMapping("/page/{pageNo}")
	 public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
	     @RequestParam("sortField") String sortField,
	     @RequestParam("sortDir") String sortDir,
	     Model model) {
	     int pageSize = 5;

	     Page < EmployeePojo > page = empService.findPaginated(pageNo, pageSize, sortField, sortDir);
	    java.util.List<EmployeePojo> listEmployees = page.getContent();

	     model.addAttribute("currentPage", pageNo);
	     model.addAttribute("totalPages", page.getTotalPages());
	     model.addAttribute("totalItems", page.getTotalElements());

	     model.addAttribute("sortField", sortField);
	     model.addAttribute("sortDir", sortDir);
	     model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	     model.addAttribute("listEmployees", listEmployees);
	     return "index";
	 }
	
}
