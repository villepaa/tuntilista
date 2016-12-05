
package wad.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Plan extends AbstractPersistable <Long> {
    
    @Id
    private Long id;
    
    private String name;
    
    
    private LocalDate startDate;
      
    private LocalDate endDate;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PlannedEmployee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<PlannedEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<PlannedEmployee> employees) {
        this.employees = employees;
    }


    @Override
    public Long getId() {
        return id;
    }

  
   
}
