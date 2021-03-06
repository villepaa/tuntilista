package wad.controller;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import wad.domain.Employee;
import wad.repository.EmployeeRepository;
import wad.repository.TaskRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class EmployeeControllerTest {
    
     @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private WebApplicationContext webAppContext;
    
   
    
    
    private MockMvc mockMvc;
    
   
 
        
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                            .webAppContextSetup(webAppContext)
                            .build();
                                    
        
    }
    
    @After
    public void tearDown() {
    }

   @Test
   public void addEmployee() throws Exception{
       String[] l = new String[1];
        l[0]="ADMIN";
        mockMvc.perform(post("/employees")
                       .param("forename", "Ville")
                       .param("surname", "Paavola")
                       .param("address", "Skip")
                       .param("phoneNumber", "040-3948398")
                       .param("email", "ville.paavola@gmail.com")
                       .param("qualification","")
                       .param("username","eka")
                       .param("password","vekara")
                       .param("userRoles",l)
                       
                       
                )
                       
                .andExpect(redirectedUrl("/employees"));
                         

        
        List <Employee> li = employeeRepository.findAll();
        Employee emp = new Employee();
        for(Employee e:li){
            if(e.getSurname().equals("Paavola")){
                emp = e;
            }
        }
        assertTrue(emp.getSurname().equals("Paavola"));
    }
    
    @Test
    public void deleteEmployee() throws Exception{
        String[] l = new String[1];
        l[0]="ADMIN";
        mockMvc.perform(post("/employees")
                       .param("forename", "Ville")
                       .param("surname", "Paavola")
                       .param("address", "Skip")
                       .param("phoneNumber", "040-3948398")
                       .param("email", "ville.paavola@gmail.com")
                       .param("qualification","")
                       .param("username","eka")
                       .param("password","vekara")
                       .param("userRoles",l)
                )
                .andExpect(redirectedUrl("/employees"));
        
        List <Employee> li = employeeRepository.findAll();
        Employee emp = new Employee();
        for(Employee e:li){
            if(e.getSurname().equals("Paavola")){
                emp = e;
            }
        }
        
        assertTrue(emp.getSurname().equals("Paavola"));
        
        employeeRepository.delete(emp.getId());
        li = employeeRepository.findAll();
        assertTrue(li.isEmpty());
    }
    
    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void modelHasAttributesEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(model().attributeExists("employees"));
    }
    
}
