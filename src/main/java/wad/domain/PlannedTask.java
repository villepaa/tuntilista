
package wad.domain;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class PlannedTask extends AbstractPersistable<Long> implements Comparable<PlannedTask>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private LocalDate dateof;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private PlannedEmployee plannedEmployee;
    
   

    public PlannedEmployee getEmployee() {
        return plannedEmployee;
    }

    public void setEmployee(PlannedEmployee employee) {
        this.plannedEmployee = employee;
    }
    
    

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.dateof);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlannedTask other = (PlannedTask) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
      
        return true;
    }

    

    @Override
    public int compareTo(PlannedTask o) {
       
        return this.id.compareTo(o.getId());
       
    }

   
    
    

}
