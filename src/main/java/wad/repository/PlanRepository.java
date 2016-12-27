
package wad.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Plan;


public interface PlanRepository extends JpaRepository<Plan, Long> {
    
    
    @Override
    List<Plan> findAll();
    
    @Override
    Page<Plan> findAll(Pageable pageable);
    
}
