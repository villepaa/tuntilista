
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlannedEmployeeTest {
    
    private PlannedEmployee pEmp;
    
    public PlannedEmployeeTest() {
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
        Plan plan = new Plan();
        plan.setName("plan");
        Task t = new Task();
        t.setName("VP");
        
        PlannedTask pt = new PlannedTask();
        pt.setTask(t);
        
        List<PlannedTask> l = new ArrayList<>();
        
        l.add(pt);
        
        pEmp = new PlannedEmployee();
        pEmp.setPlan(plan);
        pEmp.setEmployee(emp);
        pEmp.setTasks(l);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        PlannedEmployee instance = new PlannedEmployee();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testGetEmployee() {
        System.out.println("getEmployee");
        Employee result = pEmp.getEmployee();
        assertEquals(result.getUsername(),"user");
        
    }

    @Test
    public void testSetEmployee() {
        System.out.println("setEmployee");
        Employee emp  = new Employee();
        emp.setUsername("new");
        
        pEmp.setEmployee(emp);
        assertEquals(pEmp.getEmployee().getUsername(),"new");
    }

    @Test
    public void testGetTasks() {
        System.out.println("getTasks");
        
        List<PlannedTask> result = pEmp.getTasks();
        assertEquals(result.get(0).getTask().getName(),"VP");
        
    }

    @Test
    public void testSetTasks() {
        System.out.println("setTasks");
        
        List<PlannedTask> l = new ArrayList<>();
        
        Task t = new Task();
        t.setName("KV");
        
        PlannedTask pt = new PlannedTask();
        pt.setTask(t);
                
        l.add(pt);
        
        pEmp.setTasks(l);
        l = pEmp.getTasks();
        assertEquals(l.get(0).getTask().getName(),"KV");
    }

    @Test
    public void testGetPlan() {
        System.out.println("getPlan");
        
        Plan result = pEmp.getPlan();
        
        assertEquals(result.getName(),"plan");
        
    }

    @Test
    public void testSetPlan() {
        System.out.println("setPlan");
        Plan plan = new Plan();
        plan.setName("new");
        pEmp.setPlan(plan);
        assertEquals(plan.getName(),"new");
    }

         

    
}
