
package wad.domain;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlannedTaskTest {
    
    private PlannedTask instance;
    
    public PlannedTaskTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Employee emp  = new Employee();
        emp.setUsername("user");
        
        PlannedEmployee pEmp = new PlannedEmployee();
        pEmp.setEmployee(emp);
        Task t = new Task();
        t.setName("VP");
        
        instance = new PlannedTask();
        instance.setDateof(LocalDate.parse("2016-12-20"));
        instance.setEmployee(pEmp);
        instance.setTask(t);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetEmployee() {
        System.out.println("getEmployee");
        
        PlannedEmployee result = instance.getEmployee();
        assertEquals(result.getEmployee().getUsername(),"user");
        
    }

    @Test
    public void testSetEmployee() {
        System.out.println("setEmployee");
        Employee emp  = new Employee();
        emp.setUsername("new");
        
        PlannedEmployee pEmp = new PlannedEmployee();
        pEmp.setEmployee(emp);
        
        instance.setEmployee(pEmp);
        PlannedEmployee result = instance.getEmployee();
        assertEquals(result.getEmployee().getUsername(),"new");
    }

    @Test
    public void testGetTask() {
        System.out.println("getTask");
      
        Task result = instance.getTask();
        assertEquals(result.getName(),"VP");
        
    }

    @Test
    public void testSetTask() {
        System.out.println("setTask");
        Task t = new Task();
        t.setName("KV");
        instance.setTask(t);
        
        Task result = instance.getTask();
        assertEquals(result.getName(),"KV");
    }

    @Test
    public void testGetDateof() {
        System.out.println("getDateof");
        
        LocalDate result = instance.getDateof();
        assertEquals(result,LocalDate.parse("2016-12-20"));
        
    }

    @Test
    public void testSetDateof() {
        System.out.println("setDateof");
        
        LocalDate dateof = LocalDate.parse("2016-12-21");
        instance.setDateof(dateof);
        LocalDate result = instance.getDateof();
        assertEquals(result,LocalDate.parse("2016-12-21"));
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        
    }

    
    
}
