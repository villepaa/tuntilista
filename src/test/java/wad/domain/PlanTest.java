
package wad.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import wad.repository.EmployeeRepository;


public class PlanTest {
    
    private Plan instance;
    
  
    public PlanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Set<PlannedEmployee> li = new HashSet<>();
        
        PlannedEmployee pEmp = new PlannedEmployee();
        
        Employee emp  = new Employee();
        
        emp.setUsername("user");
        
        pEmp.setEmployee(emp);
        li.add(pEmp);
        
        instance = new Plan();
        instance.setName("2016-12-12 - 2016-12-18");
        instance.setStartDate(LocalDate.parse("2016-12-12"));
        instance.setEndDate(LocalDate.parse("2016-12-18"));
        instance.setEmployees(li);
        
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        
        String expResult = "2016-12-12 - 2016-12-18";
        String result = instance.getName();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "uusi";
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        
        LocalDate expResult = LocalDate.parse("2016-12-12");
        LocalDate result = instance.getStartDate();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        LocalDate startDate = LocalDate.parse("2016-12-13");
        
        instance.setStartDate(startDate);
        LocalDate result = instance.getStartDate();
        assertEquals(startDate, result);
    }

    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
       
        LocalDate expResult = LocalDate.parse("2016-12-18");
        LocalDate result = instance.getEndDate();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        LocalDate endDate = LocalDate.parse("2016-12-19");
        
        instance.setEndDate(endDate);
        LocalDate result = instance.getEndDate();
        assertEquals(endDate, result);
    }

    @Test
    public void testGetEmployees() {
        System.out.println("getEmployees");
        PlannedEmployee pEmp = new PlannedEmployee();
        
        Employee emp  = new Employee();
        
        emp.setUsername("user");
        
        pEmp.setEmployee(emp);
        instance.addEmployee(pEmp);
        
        
        Set<PlannedEmployee> result = instance.getEmployees();
        assertTrue(result.contains(pEmp));
        
    }

    @Test
    public void testSetEmployees() {
        System.out.println("setEmployees");
        Set<PlannedEmployee> li = new HashSet<>();
        PlannedEmployee pEmp = new PlannedEmployee();
        
        Employee emp  = new Employee();
        
        emp.setUsername("user");
        
        pEmp.setEmployee(emp);
        li.add(pEmp);
        
        
        instance.setEmployees(li);
        Set<PlannedEmployee> result = instance.getEmployees();
        assertTrue(result.contains(pEmp));
    }

    @Test
    public void testAddEmployee() {
        System.out.println("addEmployee");
        PlannedEmployee pEmp = new PlannedEmployee();
        
        Employee emp  = new Employee();
        emp.setUsername("new");
       
        
        pEmp.setEmployee(emp);
        instance.addEmployee(pEmp);
        assertTrue(instance.getEmployees().contains(pEmp));
    }

    @Test
    public void testRemoveEmployee() {
        System.out.println("removeEmployee");
        PlannedEmployee pEmp = new PlannedEmployee();
        instance.getEmployees().clear();
        
        Employee emp  = new Employee();
        emp.setUsername("user");
        
        pEmp.setEmployee(emp);
        
        instance.addEmployee(pEmp);
        
        
        instance.removeEmployee(pEmp);
        assertTrue(instance.getEmployees().isEmpty());
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        
    }
    
}
