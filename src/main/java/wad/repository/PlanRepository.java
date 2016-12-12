
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Plan;


public interface PlanRepository extends JpaRepository<Plan, Long> {
    

}
