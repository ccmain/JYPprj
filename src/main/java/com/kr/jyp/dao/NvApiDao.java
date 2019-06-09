package com.kr.jyp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kr.jyp.vo.NaverRspVo;

@Repository("NvApiDao")
public class NvApiDao {

    @Autowired
    private SqlSession sql;

    /*******************************************************************
     * 특정 기간의 특정 ID 의 조회량 조회
     * 
     * @param startDt
     * @param endDt
     * @param id
     * @return
     * @throws Exception
     ******************************************************************/
    public HashMap<String, Long> selectSearchCountByDateTerms(String startDt, String endDt, String id) throws Exception{
    	System.out.println("[selectSearchCountByDateTerms] START");
    	
    	Map<String, Long> returnData = new HashMap<String,Long>();
    	HashMap<String,String> param = new HashMap<String,String>();
    	
    	param.put("id", id);
    	param.put("startDt", startDt);
    	param.put("endDt", endDt);
    	
    	Map<String, Object> tmpReturnData = sql.selectMap("naverApiMapper.selectSearchCountByDateTerms",param,"DATE");
    	
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
    
    
    /*******************************************************************
     * 특정일자의 특정 ID의 조회량 조회
     * 
     * @param date
     * @param id
     * @return
     * @throws Exception
     ******************************************************************/
    public Long selectSearchCountBySingleDate(String date, String id) throws Exception{
    	Long returnData = (long) 0;
    	
    	HashMap<String,String> param = new HashMap<String,String>();
    	param.put("id", id);
    	param.put("date", date);

    	returnData = sql.selectOne("naverApiMapper.selectSearchCountBySingleDate",param);
    	
    	// TEST 출력
    	System.out.println("[selectSearchCountBySingleDate] id: "+param.get("id"));
    	System.out.println("[selectSearchCountBySingleDate] date: "+param.get("date"));
    	System.out.println("[selectSearchCountBySingleDate] returnData: "+returnData);
    	
    	return returnData;
    }
    
    /*******************************************************************
     * 특정 ID의 죄회량 입력 최대 일자
     * 
     * @param id
     * @return
     ******************************************************************/
    public String selectKeywordMaxDate(String id) {
    	String returnData ="";
    	
    	HashMap<String,String> param = new HashMap<String,String>();
    	param.put("id", id);

    	returnData = sql.selectOne("naverApiMapper.selectKeywordMaxDate",param);
    	
    	return returnData;
    }
    
    
    public void insertMultiRatioData(ArrayList<NaverRspVo> searchData){
    	
    	for(NaverRspVo insertVo : searchData){
    		sql.insert("naverApiMapper.insertMultiRatioData", insertVo);
    	}
    }
    
    
    public String selectKeywordIdByName(String name) {
    	String returnData ="";
    	
    	HashMap<String,String> param = new HashMap<String,String>();
    	param.put("name", name);

    	returnData = sql.selectOne("naverApiMapper.selectKeywordIdByName",param);
    	
    	return returnData;
    }
    
}
