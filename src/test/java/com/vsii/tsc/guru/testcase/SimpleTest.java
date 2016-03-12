package com.vsii.tsc.guru.testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SimpleTest 
{
    private String param = "";
 
    public SimpleTest(String param) {
        this.param = param;
    }
 
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before SimpleTest class executed.");
    }
 
    @Test
    public void testMethod01() {
        System.out.println("testMethod01 parameter value is: " + param);
    }
    @Test 
    public void testMethod02(){
    	System.out.println("testMethod02 parameter value is: " + param);
    }
}
 
 