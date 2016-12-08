
package wad.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wad.domain.Plan;


public interface PlanRepository extends JpaRepository<Plan, Long> {
    
}
