
package wad.service.validator;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import wad.domain.PlannedEmployee;


public class PlanForm{
    
    @NotNull(message = "Päivämäärää ei valittu")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @NotNull(message = "Päivämäärää ei valittu")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
        
    @NotEmpty(message = "Valitse työntekijät")
    private List<Long> employeeIds;

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

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

   

   
    
    
}
