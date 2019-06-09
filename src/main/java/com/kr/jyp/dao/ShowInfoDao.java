package com.kr.jyp.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ShowInfoDao")
public class ShowInfoDao {

    private SqlSession sql;
    
    @Autowired
    public ShowInfoDao(SqlSession sql) {
    	this.sql = sql;
    }

    public String getData(String paramMap) throws Exception{
    	
    	String result = sql.selectOne("showInfo.selTest", paramMap);
    	System.out.println("############### ["+ result +"]");
        return result;
    }
    
    
    public HashMap<String, Long> selectNamesCount(String startDt, String endDt, String name) throws Exception{
    	Map<String, Long> returnData = new HashMap<String,Long>();
    	HashMap<String,String> param = new HashMap<String,String>();
    	
    	param.put("startDt", startDt);
    	param.put("endDt", endDt);
    	param.put("name", name);
    	
    	Map<String, Object> tmpReturnData = sql.selectMap("showInfo.selectNamesCount", param, "DATE");

    	// HashMap 변환 처리
    	Set<String> tmpKeySet = tmpReturnData.keySet();
    	for(String key:tmpKeySet) {
    		Long tmpVal = (long) 0;
    		
    		HashMap<String,String> tmpMap = (HashMap<String, String>) tmpReturnData.get(key);
    		tmpVal = Long.parseLong(String.valueOf(tmpMap.get("SEARCH_COUNT")));
    		returnData.put(key, tmpVal);
    	}
    	
        return (HashMap<String, Long>) returnData;
    }
    
    
    
}
