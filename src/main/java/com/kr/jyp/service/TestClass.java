package com.kr.jyp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestClass {

    @Autowired
    private SqlSession sqlSession;

    public String selectTest(String paramMap) throws Exception{
    	
    	String result = sqlSession.selectOne("base.selTest", paramMap);
    	System.out.println("############### ["+ result +"]");
        return result;
    }
}
