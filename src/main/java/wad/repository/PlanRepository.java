
package wad.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wad.domain.Plan;


public interface PlanRepository extends JpaRepository<Plan, Long> {
    
    @Query(value = "SELECT MAX(END_DATE) FROM Plan", nativeQuery= true)
    public LocalDate findOneByLatestEndDate();
}
