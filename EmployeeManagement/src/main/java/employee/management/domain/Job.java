package employee.management.domain;

public enum Job {
	//Each job has assigned a salary
	Engineer(2500), 
	Administrative(1500), 
	Salesman(2000), 
	Accounter(1700), 
	Informatic(2300);

	
	private double salary;
	
	private Job(double salary) {
		this.salary = salary;
	}
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		 this.salary=salary;
	}

	}

	

