package com.olympic.cis143.m05.student.homework.annotations1;

public class HomeworkAnnotations implements IHomeworkAnnotation {
	@Deprecated
    public String sayHello() {
        return "Hello";
    }
	@Deprecated
    public String sayGoodBye() {
        return "Goodbye";
	}
}