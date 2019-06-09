package com.kr.jyp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kr.jyp.vo.TestVoRequest;

@Component("test")
public class TestClass2 {

	private TestVoRequest req;

	@Autowired
    public TestClass2(TestVoRequest req){
    	this.req = req;
    }

    public String selectTest(){
    	String result = "";
    	
    	System.out.println("req["+req+"]");
    	
    	System.out.println(req.getReq1());
    	System.out.println(req.getReq2());
    	System.out.println(req.getReq3());
    	
        return result;
    }
    
}
