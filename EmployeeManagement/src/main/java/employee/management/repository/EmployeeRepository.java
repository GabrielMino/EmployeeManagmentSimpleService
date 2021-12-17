package employee.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee.management.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	

}
