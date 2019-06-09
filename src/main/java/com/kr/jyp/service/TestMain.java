package com.kr.jyp.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) {
    	ApplicationContext ctx = new GenericXmlApplicationContext("spring/root-context.xml");
    	
    	TestClass2 test = (TestClass2)ctx.getBean("test");
    	
    	test.selectTest();
	}
}
