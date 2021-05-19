package com.olympic.cis143.m05.student.lab.trycatch;

import javax.management.BadStringOperationException;

public class Main {

    public static void main (String[] a) {
        Main main = new Main();
        main.testNeedsToBeModified();
        
    }

    public void testNeedsToBeModified() {
    	try {
			new ThrowsException().throwAnExceptionForMe();
		} catch (BadStringOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
