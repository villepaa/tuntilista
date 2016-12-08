
package wad.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    Employee findBySurname(String surname);
    
    Employee findByUsername(String username);
}
