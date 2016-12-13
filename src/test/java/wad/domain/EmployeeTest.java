
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class EmployeeTest {

    private Employee instance;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        instance = new Employee();
        instance.setAddress("Kuminakuja");
        instance.setEmail("example@something.com");
        instance.setForename("Teppo");
        instance.setPassword("pass");
        instance.setPhoneNumber("03");
        instance.setSurname("Testi");
        instance.setUsername("Teppo");
        List<String> l = new ArrayList<>();
        l.add("VP");
        instance.setQualifications(l);
        
        List<String> li = new ArrayList<>();
        li.add("ADMIN");
        instance.setUserRoles(li);
    }

    @After
    public void tearDown() throws Exception {
        instance = new Employee();
    }

    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        
        String expResult = "Teppo";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "Kalle";
        
        instance.setUsername(username);
        assertEquals(instance.getUsername(),"Kalle");
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        
        String expResult = "pass";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "uusi";
        instance.setPassword(password);
        assertEquals(instance.getPassword(), password);
    }

    @Test
    public void testGetForename() {
        System.out.println("getForename");
      
        String expResult = "Teppo";
        String result = instance.getForename();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetForename() {
        System.out.println("setForename");
        String forename = "Seppo";
        instance.setForename(forename);
        
        assertEquals(instance.getForename(), forename);
    }

    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        
        String expResult = "Testi";
        String result = instance.getSurname();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String surname = "uusi";
        
        instance.setSurname(surname);
        assertEquals(instance.getSurname(), surname);
    }

    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        
        String expResult = "Kuminakuja";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "uusi";
        
        instance.setAddress(address);
        assertEquals(instance.getAddress(), address);
        
    }

    @Test
    public void testGetPhoneNumber() {
        System.out.println("getPhoneNumber");
        
        String expResult = "03";
        String result = instance.getPhoneNumber();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetPhoneNumber() {
        System.out.println("setPhoneNumber");
        String phoneNumber = "05";
        instance.setPhoneNumber(phoneNumber);
        assertEquals(instance.getPhoneNumber(), phoneNumber);
    }

    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        
        String expResult = "example@something.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "uusi";
        instance.setEmail(email);
        assertEquals(instance.getEmail(), email);
    }

    @Test
    public void testGetQualifications() {
        System.out.println("getQualifications");
              
        assertTrue(instance.getQualifications().contains("VP"));
        
    }

    @Test
    public void testSetQualifications() {
        System.out.println("setQualifications");
        List<String> qualifications = new ArrayList<>();
        qualifications.add("new");
        instance.setQualifications(qualifications);
        assertTrue(instance.getQualifications().contains("new"));
    }

    @Test
    public void testGetUserRoles() {
        System.out.println("getUserRoles");
        
        List<String> result = instance.getUserRoles();
        assertTrue(result.contains("ADMIN"));
        
    }

    @Test
    public void testSetUserRoles() {
        System.out.println("setUserRoles");
        List<String> userRoles = null;
        Employee instance = new Employee();
        instance.setUserRoles(userRoles);
        
    }

   
   

    @Test
    public void testGetId() {
        System.out.println("getId");
        
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        
    }
    
}
