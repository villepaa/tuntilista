///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package wad.controller;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import wad.domain.Task;
//
///**
// *
// * @author villepaa
// */
//public class TaskControllerIT {
//    
//    public TaskControllerIT() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void testListTasks() {
//        System.out.println("listTasks");
//        Model model = null;
//        TaskController instance = new TaskController();
//        String expResult = "";
//        String result = instance.listTasks(model);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testShowAddForm() {
//        System.out.println("showAddForm");
//        Model m = null;
//        TaskController instance = new TaskController();
//        String expResult = "";
//        String result = instance.showAddForm(m);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testCreateTask() {
//        System.out.println("createTask");
//        Task task = null;
//        BindingResult bindingResult = null;
//        TaskController instance = new TaskController();
//        String expResult = "";
//        String result = instance.createTask(task, bindingResult);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testShowTask() {
//        System.out.println("showTask");
//        Model model = null;
//        Long id = null;
//        TaskController instance = new TaskController();
//        String expResult = "";
//        String result = instance.showTask(model, id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testEditTask() {
//        System.out.println("editTask");
//        Task task = null;
//        Long id = null;
//        BindingResult bindingResult = null;
//        TaskController instance = new TaskController();
//        String expResult = "";
//        String result = instance.editTask(task, id, bindingResult);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteTask() {
//        System.out.println("deleteTask");
//        Long id = null;
//        TaskController instance = new TaskController();
//        String expResult = "";
//        String result = instance.deleteTask(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//    
//}
