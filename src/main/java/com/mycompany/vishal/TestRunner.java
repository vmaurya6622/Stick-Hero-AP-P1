package com.mycompany.vishal;

/* Junit test runner class */
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println("There was Some Failures : \n");
            System.out.println(failure.toString());
        }
        System.out.println("All the Tests were Passed Successfully\n");
        System.out.println(result.wasSuccessful());

    }
}
