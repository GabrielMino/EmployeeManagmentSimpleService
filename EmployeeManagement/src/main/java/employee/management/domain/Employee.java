package employee.management.domain;

import javax.persistence.*;



@Entity
@Table (name = "EMPLOYEES")
public class Employee {
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name="employee_sequence",sequenceName="employee_sequence",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="employee_sequence")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name="job")
	private Job job;
	
	@Column(name="salary")
	private double salary;
	

	@Column(name = "photo", columnDefinition = "BLOB")
	private byte[] photo;
	
	public Employee() {}
	
	
	public Employee(String name, Job job) {
		
		this.name = name;
		this.job = job;
		this.salary=job.getSalary();
	}
	

	


	public Long getId() {
		return id;
	}


	public void setId( Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary() {
		this.salary = job.getSalary();
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", job=" + job + ", salary=" + salary+ "]";
	}
	
	
	
}
