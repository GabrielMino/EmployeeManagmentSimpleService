package employee.management.controller;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import employee.management.domain.*;
import employee.management.repository.*;
import employee.management.service.*;
import io.swagger.annotations.ApiOperation;


@Component
@RestController
@WebFilter("/employee/*")
@RequestMapping(path = "/employee")

public class EmployeeController implements Filter {
	
	private EmployeeService employeeService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	    httpServletResponse.setHeader("IT-Academy-Exercise", "Simple Service");
	    chain.doFilter(request, response);
	}
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	//Add employees to the database
	@ApiOperation(value = "Fill the database")
	@GetMapping("/fill")
	public ResponseEntity<String> fillDataBase() {
			BaseDeDatos baseDatos = new BaseDeDatos();
			baseDatos.start();
			ArrayList<Employee> employeesBD = baseDatos.getEmployeesBD();
			employeeService.saveDataBase(employeesBD);
			return ResponseEntity.ok("Employees succesfully added");
		}
	
	//read
	@ApiOperation(value = "Get all employees")
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees(){
	//Manage empty array
		return ResponseEntity.ok(employeeService.getEmployees());
	}
	
	//read
	@ApiOperation(value = "Search employees by job")
	@GetMapping(path="/{job}")
	
	public ResponseEntity<List<Employee>> getEmployeesFiltredByJob(@PathVariable(name="job")Job job){
		List<Employee> filteredList = employeeService.getEmployees().stream()
									.filter(j->j.getJob().toString().equalsIgnoreCase((job.toString())))
									.collect(Collectors.toList());
		
		
	return ResponseEntity.ok(filteredList);	
		
	}
	
	//Read
	
	@ApiOperation(value = "Search employees by id")
	@GetMapping(path="/getid/{id}")
		
	public ResponseEntity<Employee> getEmployee(@PathVariable(name="id") Long id) {
		return ResponseEntity.ok(employeeService.findEmployeeById(id));
			
		}
		
	
	
	//Create a new employee
	@ApiOperation(value = "Add an employee")
	@PostMapping(path="/{name}/{job}")

	public ResponseEntity<Employee> registerNewEmployee(@PathVariable(name="name") String name, @PathVariable(name="job") Job job) {
			
		Employee employee = employeeService.addNewEmployeee(name,job);
		
		return ResponseEntity.ok(employee);
		}

	
	//Update
	@ApiOperation(value = "Update employee")
	@PutMapping(path="/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(
		@PathVariable("employeeId") Long employeeId,
		@RequestParam(required = false) String name,
		@RequestParam(required = false) Job job){
		
		Employee employee= employeeService.updateEmployee(employeeId ,name,job);
		return ResponseEntity.ok(employee);}
	
	
	//Delete
	@ApiOperation(value = "Delete employee")
	@DeleteMapping(path="{employeeId}")

	public ResponseEntity<String> deleteStudent(@PathVariable("employeeId") Long employeeId){

	employeeService.deleteEmployee(employeeId);
	return ResponseEntity.ok("The employee with id: "+ employeeId + ", has been deleted");
	
	}
	
	
	//Upload photo
	
	@ApiOperation(value = "Upload photo")
	@PutMapping("/uploadPhoto/{id}")
	public ResponseEntity<String> updateEmployeePhoto(@PathVariable(name = "id") long id) {
		
	  Employee employee = employeeService.findEmployeeById(id);
	      
		  employeeService.addPhoto(employee);
		  return ResponseEntity.ok("The photo has been correctly uploaded");
	}
	
	//Download photo
	@ApiOperation(value = "Download photo")
	@GetMapping("/downloadphoto/{id}")
	public ResponseEntity<Resource> getEmployeePhoto(@PathVariable(name = "id") long id) {
	

	Employee employee = employeeService.findEmployeeById(id);

	return employeeService.downloadPhoto(employee);
	}

	
}	