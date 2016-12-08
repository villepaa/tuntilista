
package wad.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import wad.domain.Task;
import wad.repository.TaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {
    
     
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private WebApplicationContext webAppContext;
    
    private MockMvc mockMvc;
    
    private Task t;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        
        t = new Task();
        t.setEndTime(LocalTime.parse("19:00"));
        t.setStartTime(LocalTime.parse("08:00"));
        t.setInformation("info");
        t.setName("KV");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void modelHasAttributesTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(model().attributeExists("tasks"));
    }
    
    @Test
    public void addTaskTest() throws Exception {
        
        taskRepository.save(t);
        List <Task> l = taskRepository.findAll();
        Task task = new Task();
        for(Task t:l){
            if(t.getName().equals("KV")){
                task = t;
            }
        }
        assertTrue(task.getName().equals("KV"));
        assertTrue(task.getInformation().equals("info"));
    }
    
    public void editTaskTest() throws Exception {
        List <Task> l = taskRepository.findAll();
        assertTrue(l.isEmpty());
        taskRepository.save(t);
        
        Task uusi = taskRepository.findByName(t.getName());
        assertTrue(uusi.getId().equals(t.getId()));
        uusi.setName("OL");
        taskRepository.save(uusi);
        l = taskRepository.findAll();
        assertTrue(l.size() == 1);
    }
    
    
}
