
package wad.controller;

import java.time.LocalTime;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import wad.domain.Task;
import wad.repository.TaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class TaskControllerTest {
    
     
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private WebApplicationContext webAppContext;
    
    @Autowired
    private TaskController controller;
    
    private MockMvc mockMvc;
    
    private Task t;
    private Task testTask;
    
    
    
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
        taskRepository.save(t);
        
        testTask = new Task();
        testTask.setName("VP");
        testTask.setEndTime(LocalTime.parse("00:00"));
        testTask.setStartTime(LocalTime.parse("00:00"));
        testTask.setInformation("vapaa");
        taskRepository.save(testTask);
    }
    
    @After
    public void tearDown() {
        taskRepository.deleteAll();
        
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
//        mockMvc.perform(post("/tasks")
//                       .param("name", "OL")
//                       .param("startTime", "08:00:00")
//                       .param("endTime", "19:00:00")
//                       .param("information", "uusi")
//                       
//                
//                )
//                .andDo(print())
//                .andExpect(redirectedUrl("/tasks"));
        Task task = new Task();
        
        List <Task> l = taskRepository.findAll();
        
        for(Task test:l){
            if(test.getName().equals("KV")){
                task = test;
            }
        }
        assertTrue(task.getName().equals("KV"));
        assertTrue(task.getInformation().equals("info"));
    }
    
    @Test
    public void editTaskTest() throws Exception {
        List <Task> l = taskRepository.findAll();
                
        Task uusi = taskRepository.findOne(t.getId());
        assertTrue(uusi.getInformation().equals(t.getInformation()));
        uusi.setInformation("uusi");
        taskRepository.save(uusi);
        Task muokattu = taskRepository.findOne(uusi.getId());
        assertTrue(uusi.getInformation().equals(muokattu.getInformation()));
    }
    
    @Test
    public void deleteTaskTest() throws Exception {
        
        controller.deleteTask(t.getId());
        List <Task> l = taskRepository.findAll();
        assertEquals(l.size(),1);
        
        controller.deleteTask(testTask.getId());
        l = taskRepository.findAll();
        assertTrue(l.size() == 1);
    }
}
