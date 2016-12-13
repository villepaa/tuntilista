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
//import wad.domain.Employee;
//
///**
// *
// * @author villepaa
// */
//public class EmployeeControllerIT {
//    
//    public EmployeeControllerIT() {
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
//    public void testListEmployees() {
//        System.out.println("listEmployees");
//        Model model = null;
//        EmployeeController instance = new EmployeeController();
//        String expResult = "";
//        String result = instance.listEmployees(model);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testShowAddForm() {
//        System.out.println("showAddForm");
//        Model model = null;
//        EmployeeController instance = new EmployeeController();
//        String expResult = "";
//        String result = instance.showAddForm(model);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testCreateEmployee() {
//        System.out.println("createEmployee");
//        Model model = null;
//        Employee emp = null;
//        BindingResult bindingResult = null;
//        EmployeeController instance = new EmployeeController();
//        String expResult = "";
//        String result = instance.createEmployee(model, emp, bindingResult);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testShowEmployee() {
//        System.out.println("showEmployee");
//        Model model = null;
//        Long id = null;
//        EmployeeController instance = new EmployeeController();
//        String expResult = "";
//        String result = instance.showEmployee(model, id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testEditEmployee() {
//        System.out.println("editEmployee");
//        Model model = null;
//        Employee emp = null;
//        Long id = null;
//        BindingResult bindingResult = null;
//        EmployeeController instance = new EmployeeController();
//        String expResult = "";
//        String result = instance.editEmployee(model, emp, id, bindingResult);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testDeleteEmployee() {
//        System.out.println("deleteEmployee");
//        Long id = null;
//        EmployeeController instance = new EmployeeController();
//        String expResult = "";
//        String result = instance.deleteEmployee(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//    
//}
