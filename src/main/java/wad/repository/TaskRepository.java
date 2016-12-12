
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
    
    Task findByName(String taskName);
    
}
