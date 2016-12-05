
package wad.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class PlannedTask extends AbstractPersistable<Long>{
    
    @Id
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;
    
    @NotNull
    private LocalDate dateof;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public LocalDate getDateof() {
        return dateof;
    }

    public void setDateof(LocalDate dateof) {
        this.dateof = dateof;
    }

        
    
    public Long getId() {
        return id;
    }

}
