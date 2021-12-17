package employee.management.repository;

import java.util.*;
import employee.management.domain.Employee;
import employee.management.domain.Job;


public class BaseDeDatos {
	
	private ArrayList<Employee>	employees;

			public void start() {
				
			employees = new ArrayList<>();
			employees.add( new Employee("Gabriel Rodriguez",Job.Engineer));
			employees.add( new Employee("Diego Rodriguez",Job.Administrative));
			employees.add( new Employee("German Pezella ",Job.Administrative));
			employees.add( new Employee("María Hernandez ",Job.Informatic));
			employees.add( new Employee("Lucía Pezella ",Job.Salesman));
			employees.add( new Employee("Natalia Pezella ",Job.Accounter));
			
			}

			public ArrayList<Employee> getEmployeesBD() {
				return employees;
			}


	}


