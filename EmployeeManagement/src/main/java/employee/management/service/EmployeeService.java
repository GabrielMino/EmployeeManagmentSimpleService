package employee.management.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.io.ByteArrayInputStream;


import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.management.domain.Employee;
import employee.management.domain.Job;
import employee.management.exceptions.*;
import employee.management.repository.EmployeeRepository;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

//employee service class
@Service
public class EmployeeService {
//We can annotate a constructor, field, setter method, or config method for Spring IoC to auto-inject the dependency.
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository){

	this.employeeRepository = employeeRepository;

	}
	
	//Create
	public Employee addNewEmployeee(String name, Job job) {
		Employee employee = new Employee(name,job);
		employeeRepository.save(employee);
		return employee;
	}
	
	//Read
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		if (employeeRepository.findAll().size()>0) {
		return employeeRepository.findAll();
		}
		throw new NoDataFoundException("No employees data found");		
	}
	
	//Update
	@Transactional
	public Employee updateEmployee(Long employeeId, String name, Job job) {
		//Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException("Employee not found for this id :: " + employeeId));
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with id "+employeeId+" does no exists"));
		//			//(employeeID).orElseThrow() -> new IllegalStateException("student with id"+studentId+" does no exists");
		if (name != null && name.length()>0 && !Objects.equals(employee.getName(),name)){
		employee.setName(name);
		}
		
		employee.setJob(job);	
		return employee;
	}
	
	//Delete
	public void deleteEmployee(Long employeeId){
		boolean exists = employeeRepository.existsById(employeeId);
		if (!exists){
			throw new ResourceNotFoundException("employee with id "+employeeId+" doesn´t exists");
		}
		employeeRepository.deleteById(employeeId);
	}

	//create
	public void saveDataBase(ArrayList<Employee> employeesBD) {
		employeeRepository.saveAll(employeesBD);	
	}


	//read
	public Employee findEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id "+ id+" doesn´t exists"));
		return employee;
	}

	//upload photo
	public void addPhoto(Employee employee) {
		
		byte[] photo = null;	
		  InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("employee.jpg");

			try { photo = IOUtils.toByteArray(inputStream);
			} catch (IOException e) { e.printStackTrace();}
			employee.setPhoto(photo);
			employeeRepository.save(employee);
		}	
	
	//dowload photo
	public ResponseEntity<Resource> downloadPhoto(Employee employee) {
		
    byte[] fotoByte = employee.getPhoto();
		if (fotoByte==null) {throw new ResourceNotFoundException("There is no photo for this employee");}
	    InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(fotoByte));
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Disposition", String.format("attachment; filename=Empleado"));    
	    return ResponseEntity.ok()
				             .headers(headers)
				             .contentLength(fotoByte.length)
				             .contentType(MediaType.IMAGE_JPEG)
				             .body(resource);		  
	}
}
		
	
