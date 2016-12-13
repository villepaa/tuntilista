
package wad.domain;

import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TaskTest {
    
    private Task task;
    
    public TaskTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        task = new Task();
        task.setName("VP");
        task.setEndTime(LocalTime.parse("18:00"));
        task.setStartTime(LocalTime.parse("08:00"));
        task.setInformation("jotain");
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetInformation() {
        System.out.println("getInformation");
        String result = task.getInformation();
        assertTrue(result.equals("jotain"));
       
    }

    @Test
    public void testSetInformation() {
        
        System.out.println("setInformation");
        String information = "uusi";
        task.setInformation(information);
        assertEquals(task.getInformation(), information);;
        
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "VP";
        String result = task.getName();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "KV";
        
        task.setName(name);
        assertEquals(task.getName(), name);
    }

    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        
        LocalTime expResult = LocalTime.parse("08:00");
        LocalTime result = task.getStartTime();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        LocalTime startTime = LocalTime.parse("09:00");
        task.setStartTime(startTime);
        assertEquals(task.getStartTime(), startTime);
    }

    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
                       
        LocalTime expResult = LocalTime.parse("18:00");
        LocalTime result = task.getEndTime();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetEndTime() {
        System.out.println("setEndTime");
        LocalTime EndTime = LocalTime.parse("19:00");
        
        task.setEndTime(EndTime);
        assertEquals(task.getEndTime(), EndTime);
    }

    

    @Test
    public void testGetId() {
        System.out.println("getId");
        
        Long expResult = null;
        Long result = task.getId();
        assertEquals(expResult, result);
        
    }
    
}
