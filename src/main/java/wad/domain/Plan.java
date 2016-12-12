
package wad.domain;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Plan extends AbstractPersistable <Long> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @NotNull
    private LocalDate startDate;
    @NotNull  
    private LocalDate endDate;
        
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="plan")
    private Set<PlannedEmployee> employees;

    
    
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

    public Set<PlannedEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<PlannedEmployee> employees) {
        this.employees = employees;
    }
    
    public void addEmployee(PlannedEmployee e){
        if(this.employees == null){
            this.employees = new TreeSet<>();
        }
        this.employees.add(e);
    }
    
    public void removeEmployee(PlannedEmployee e){
        if(this.employees.contains(e)){
            this.employees.remove(e);
        }
        
    }

    @Override
    public Long getId() {
        return id;
    }

  
   
}
