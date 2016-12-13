
package wad.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wad.domain.Plan;


public interface PlanRepository extends JpaRepository<Plan, Long> {
    
    @Query("SELECT MAX(p.endDate) FROM Plan p")
    public LocalDate findOneByLatestEndDate();
}
