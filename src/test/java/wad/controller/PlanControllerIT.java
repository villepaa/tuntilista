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
//import wad.service.validator.PlanForm;
//
///**
// *
// * @author villepaa
// */
//public class PlanControllerIT {
//    
//    public PlanControllerIT() {
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
//    public void testShowPlans() {
//        System.out.println("showPlans");
//        Model model = null;
//        PlanController instance = new PlanController();
//        String expResult = "";
//        String result = instance.showPlans(model);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testShowPlan_Long() {
//        System.out.println("showPlan");
//        Long id = null;
//        PlanController instance = new PlanController();
//        String expResult = "";
//        String result = instance.showPlan(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testCreatePlan() {
//        System.out.println("createPlan");
//        PlanForm planForm = null;
//        BindingResult result_2 = null;
//        Model model = null;
//        PlanController instance = new PlanController();
//        String expResult = "";
//        String result = instance.createPlan(planForm, result_2, model);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testShowPlan_Model_Long() {
//        System.out.println("showPlan");
//        Model model = null;
//        Long id = null;
//        PlanController instance = new PlanController();
//        String expResult = "";
//        String result = instance.showPlan(model, id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdatePlan() {
//        System.out.println("updatePlan");
//        Model model = null;
//        Long employeeId = null;
//        Long ptaskId = null;
//        String taskName = "";
//        Long id = null;
//        PlanController instance = new PlanController();
//        String expResult = "";
//        String result = instance.updatePlan(model, employeeId, ptaskId, taskName, id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//    
//}
