/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class PlannedEmployee extends AbstractPersistable<Long>{
    
    
    @Id
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
        return tasks;
    }

    public void setTasks(List<PlannedTask> tasks) {
        this.tasks = tasks;
    }
}
