
package wad.domain;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class PlannedEmployee extends AbstractPersistable<Long> implements Comparable<PlannedEmployee>{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plannedEmployee")
    private List<PlannedTask> tasks;
        
       
    @Override
    public Long getId() {
        return id;
    }
    

 
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<PlannedTask> getTasks() {
        Collections.sort(tasks);
        return tasks;
    }

    public void setTasks(List<PlannedTask> tasks) {
        this.tasks = tasks;
    }

    

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
    public void addTask(PlannedTask t){
        if(this.tasks == null){
            this.tasks = new ArrayList<>();
        }
        this.tasks.add(t);
       
            
    }
    
     public void removeTask(PlannedTask t){
        if(this.tasks.contains(t)){
            this.tasks.remove(t);
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final PlannedEmployee other = (PlannedEmployee) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(PlannedEmployee o) {
        return this.employee.compareTo(o.employee);
    }
     
    
    
    
}
