package com.kr.jyp.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kr.jyp.constant.CommonConst;
import com.kr.jyp.dao.NvApiDao;
import com.kr.jyp.service.NvApiService;
import com.kr.jyp.util.CommonUtil;
import com.kr.jyp.vo.NaverRspVo;
import com.kr.jyp.vo.ResultVo;

@Component
public class NvApiServiceImpl implements NvApiService {
	
    private NvApiDao nvApiDao;
	
    @Autowired
	public NvApiServiceImpl(NvApiDao nvApiDao) {
    	this.nvApiDao = nvApiDao;
	}

    
    /*******************************************************************
     * 
     * 
     ******************************************************************/
    public ResultVo setBaseDataService() {
    	ResultVo returnData = new ResultVo();
    	
    	try {
    		// 테스트용
    		Thread.sleep(5*1000);
    		
    		
    		Calendar calendar = new GregorianCalendar();
    		calendar.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
    		SimpleDateFormat sfyyyymmdd = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷 
    		
    		String startDt = nvApiDao.selectKeywordMaxDate(CommonConst.BASE_KEYWORD_ID);
    		String endDt = sfyyyymmdd.format(calendar.getTime());
    		
    		String reqStartDt = CommonUtil.transDateFormat(startDt,"-");
    		String reqEndDt = CommonUtil.transDateFormat(endDt,"-");
    		
    		System.out.println(reqStartDt);
    		System.out.println(reqEndDt);
    		
    		if(reqStartDt.equals(reqEndDt)) {
    			throw new Exception("조회 날짜 동일 오류!!!");
    		}
    		
    		ArrayList<String> searchKeywordArr = new ArrayList<String>();
    		searchKeywordArr.add(CommonConst.BASE_KEYWORD_NAME);
    		
    		JSONObject rslt = this.reqNvDataLab(reqStartDt, reqEndDt, searchKeywordArr);
    		ArrayList<NaverRspVo> searchResult = this.parsNaverDataUsingStandardDate(rslt, startDt);
    		
    		// 테스트 출력
    		for(int i=0;i<searchResult.size();i++) {
    			NaverRspVo rrrr = (NaverRspVo)searchResult.get(i);
    			
    			System.out.println("getKeyword   ["+rrrr.getKeyword()+"]");
    			System.out.println("getKeywordId ["+rrrr.getKeywordId()+"]");
    			System.out.println("getPeriod    ["+rrrr.getPeriod()+"]");
    			System.out.println("getRatio     ["+rrrr.getRatio()+"]");
    			System.out.println("getBaseRatio ["+rrrr.getBaseRatio()+"]");
    			System.out.println("getBaseCount ["+rrrr.getBaseCount()+"]");
    			System.out.println("getCalCount  ["+rrrr.getCalCount()+"]");
    			System.out.println("----------------------------------------");
    			
    		}
    		
    		nvApiDao.insertMultiRatioData(searchResult);
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return returnData;
    }
    
    
    /*******************************************************************
     * 
     * @return
     ******************************************************************/
    public ResultVo setSearchDataService(String startDt, String endDt, ArrayList<String> searchKeywordArr) {
    	ResultVo returnData = new ResultVo();
    	
    	try {
    		// 테스트용
    		Thread.sleep(5*1000);
    		
    		if(startDt.equals(endDt)) {
    			throw new Exception("조회 날짜 동일 오류!!!");
    		}
    		
    		String reqStartDt = CommonUtil.transDateFormat(startDt,"-");
    		String reqEndDt = CommonUtil.transDateFormat(endDt,"-");
    		
    		JSONObject rslt = this.reqNvDataLab(reqStartDt, reqEndDt, searchKeywordArr);
    		ArrayList<NaverRspVo> searchResult = this.parsNaverDataUsingBase(rslt);
    		
    		// 테스트 출력
    		for(int i=0;i<searchResult.size();i++) {
    			NaverRspVo rrrr = (NaverRspVo)searchResult.get(i);
    			
    			System.out.println("getKeyword   ["+rrrr.getKeyword()+"]");
    			System.out.println("getKeywordId ["+rrrr.getKeywordId()+"]");
    			System.out.println("getPeriod    ["+rrrr.getPeriod()+"]");
    			System.out.println("getRatio     ["+rrrr.getRatio()+"]");
    			System.out.println("getBaseRatio ["+rrrr.getBaseRatio()+"]");
    			System.out.println("getBaseCount ["+rrrr.getBaseCount()+"]");
    			System.out.println("getCalCount  ["+rrrr.getCalCount()+"]");
    			System.out.println("----------------------------------------");
    			
    		}
    		
    		nvApiDao.insertMultiRatioData(searchResult);
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    		
    	return returnData;
    }
    
    
	/*******************************************************************
	 * 네이버에 검색량 요청
	 * 
	 * @param startDt
	 * @param endDt
	 * @param searchDataArr
	 * @return
	 * 
	 *******************************************************************/
	private JSONObject reqNvDataLab(String startDt, String endDt, ArrayList<String> searchDataArr){
		JSONObject returnData = null;
		
		JSONParser parser = new JSONParser();
		JSONObject reqJson = new JSONObject();
		JSONArray keywordJsonArr = new JSONArray();
		
		String requestStr = "";

		try{
			
			for(int i=0;i<searchDataArr.size();i++){
				String tmpKeyworkdata = (String)searchDataArr.get(i);
				JSONObject tmpKeywordJson = new JSONObject();
				ArrayList<String> tmpDataArr = new ArrayList<String>();
				tmpDataArr.add(tmpKeyworkdata);
				tmpKeywordJson.put("groupName", tmpKeyworkdata);
				tmpKeywordJson.put("keywords", tmpDataArr);
				keywordJsonArr.add(tmpKeywordJson);
			}
			
			reqJson.put("startDate", startDt);
			reqJson.put("endDate", endDt);
			reqJson.put("timeUnit", "date");
			reqJson.put("keywordGroups", keywordJsonArr);
			
			requestStr = reqJson.toString();
			
			System.out.println("##################################################");
			System.out.println("[reqNvDataLab] REQUEST DATA \n"+ requestStr);
			System.out.println("##################################################");
//			CommonUtil.encodingTest(requestStr);
												
            URL url = new URL(CommonConst.NAVER_API_URL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", CommonConst.CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", CommonConst.CLIENT_SECRET);
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(requestStr.getBytes("UTF-8"));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200){// 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            }
            else {// 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            returnData = (JSONObject) parser.parse( response.toString() ); 
            
			System.out.println("##################################################");
			System.out.println("[reqNvDataLab] RESPONSE DATA \n"+ response.toString());
			System.out.println("##################################################");
//			CommonUtil.encodingTest(response.toString());
        }
		catch (Exception e) {
            e.printStackTrace();
        }
		
		return returnData;
	}
	
	
	/*******************************************************************
	 * BASE keyword 검색량 조회 & 셋팅
	 * 
	 * @param nvRsponsJson
	 * @return
	 * 
	 ******************************************************************/
	private ArrayList<NaverRspVo> parsNaverDataUsingStandardDate(JSONObject nvRsponsJson, String standardDate){
		ArrayList<NaverRspVo> returnData = new ArrayList<NaverRspVo>();
		
		String title = "";
		String titleKeywordId = ""; //임시 셋팅
		Long standardCount = (long) 0;
		Double standardRatio = (double) 0;
		
		try {
			String startDate = ((String)nvRsponsJson.get("startDate")).replace("-", "");
			String endDate = ((String)nvRsponsJson.get("endDate")).replace("-", "");
			JSONArray resultsJsonArr = (JSONArray)nvRsponsJson.get("results");
			JSONObject resultsJson = (JSONObject) resultsJsonArr.get(0);
			JSONArray dataJsonArr = (JSONArray) resultsJson.get("data");
			
			title = (String)resultsJson.get("title");
			titleKeywordId = nvApiDao.selectKeywordIdByName(title);
			
			if( 1 != resultsJsonArr.size()) {
				throw new Exception("BASE KEYWORD 갯수 제한 오류");
			}
			
			// BASE KEYWORD 일치 여부 확인
			if(CommonConst.BASE_KEYWORD_NAME.equals(title) == false) {
				throw new Exception("BASE KEYWORD 불일치");
			}
			
			// 기준일자의 조회 카운트 설정
			standardCount = nvApiDao.selectSearchCountBySingleDate(standardDate, CommonConst.BASE_KEYWORD_ID);
			
			// 기준 일자의 조회된 Ratio 설정
			for(int t=0;t<dataJsonArr.size();t++) {
				JSONObject tmpDtJson = (JSONObject)dataJsonArr.get(t);
				if(standardDate.equals(((String)tmpDtJson.get("period")).replace("-", ""))) {
					standardRatio = Double.parseDouble(String.valueOf(tmpDtJson.get("ratio")));
					break;
				}
			}
			
			
			System.out.println("standardDate  ["+standardDate+"]");
			System.out.println("standardRatio ["+standardRatio+"]");
			System.out.println("standardCount ["+standardCount+"]");
			System.out.println("--------------------------------------------");
			
			
			for(int i=0;i<dataJsonArr.size();i++) {
				JSONObject tmpDataJson = (JSONObject)dataJsonArr.get(i);
				
				NaverRspVo tmpRspVo = new NaverRspVo();
				String tmpPeriod = ((String)tmpDataJson.get("period")).replace("-", "");
				Double tmpRatio = Double.parseDouble(String.valueOf(tmpDataJson.get("ratio")));
				Long tmpCalCount = (long)(tmpRatio/standardRatio*standardCount);
				
				tmpRspVo.setKeyword(title);//조회 검색어
				tmpRspVo.setKeywordId(titleKeywordId);//키워드 ID
				tmpRspVo.setPeriod(tmpPeriod);//조회일
				tmpRspVo.setRatio(tmpRatio);//조회 키워드 검색률
				tmpRspVo.setBaseRatio(standardRatio);//BASE 검색률
				tmpRspVo.setBaseCount(standardCount);//BASE 검색량
				tmpRspVo.setCalCount(tmpCalCount);//계산된 조회 검색어의 검색량
				
				returnData.add(tmpRspVo);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnData;
	}
	
	
	/*******************************************************************
	 * 조회할 keyword 검색량 조회 & 셋팅
	 * 
	 * @param nvRsponsJson
	 * @return
	 * 
	 *******************************************************************/
	private ArrayList<NaverRspVo> parsNaverDataUsingBase(JSONObject nvRsponsJson){
		ArrayList<NaverRspVo> returnData = new ArrayList<NaverRspVo>();
		HashMap<String, Double> baseKeywordRatio = new HashMap<String, Double>();
		HashMap<String, Long> baseKeywordCount = new HashMap<String, Long>();
		boolean baseExistCheck = false;
		
		try {
			String startDate = ((String)nvRsponsJson.get("startDate")).replace("-", "");
			String endDate = ((String)nvRsponsJson.get("endDate")).replace("-", "");
			JSONArray resultsJsonArr = (JSONArray)nvRsponsJson.get("results");
			
			//--------------------------------------------------
			// BASE 키워드 비율 저장: setting baseKeywordRatio
			//--------------------------------------------------
			for(int t=0;t<resultsJsonArr.size();t++){
				JSONObject tmpBaseResultsJson = (JSONObject)resultsJsonArr.get(t);
				String tmpBaseTitle = (String)tmpBaseResultsJson.get("title");
				
				if(CommonConst.BASE_KEYWORD_NAME.equals(tmpBaseTitle)){
					
					System.out.println("tmpBaseTitle ["+tmpBaseTitle+"]");
					JSONArray tmpBaseResultsJsonArr = (JSONArray)tmpBaseResultsJson.get("data");
					
					for(int ii=0;ii<tmpBaseResultsJsonArr.size();ii++){
						JSONObject tmpBaseJson = (JSONObject)tmpBaseResultsJsonArr.get(ii);
						baseKeywordRatio.put( ((String)tmpBaseJson.get("period")).replace("-","")
								, Double.parseDouble(String.valueOf(tmpBaseJson.get("ratio"))));
						
	//					System.out.println("BASE period ["+(String)tmpBaseJson.get("period")+"]");
	//					System.out.println("BASE ratio  ["+Double.parseDouble(String.valueOf(tmpBaseJson.get("ratio")))+"]");
					}
					
					baseExistCheck = true;
					break;
				}
			}
			
			if(baseExistCheck == false) {
				throw new Exception("BASE KEYWORD 미존재 오류");
			}
			
			//--------------------------------------------------
			// BASE 키워드 카운트 저장: setting baseKeywordCount
			//--------------------------------------------------
			// 서버에 올릴때 사용
			baseKeywordCount = nvApiDao.selectSearchCountByDateTerms(startDate,endDate,CommonConst.BASE_KEYWORD_ID);

			//--------------------------------------------------
			// 조회 키워드 데이터 저장
			//--------------------------------------------------
			for(int i=0;i<resultsJsonArr.size();i++){
				
				JSONObject tmpResultsJson = (JSONObject)resultsJsonArr.get(i);
				String tmpTitle = (String)tmpResultsJson.get("title");
				String tmpTitleKeywordId = nvApiDao.selectKeywordIdByName(tmpTitle);
				JSONArray tmpResultsJsonArr = (JSONArray)tmpResultsJson.get("data");
				
				// BASE 키워드는 제외
				if(CommonConst.BASE_KEYWORD_NAME.equals(tmpTitle)){
					continue;
				}
				
				// Vo setting
				for(int j=0;j<tmpResultsJsonArr.size();j++){
					JSONObject tmpResultsJson2 = (JSONObject)tmpResultsJsonArr.get(j);
					
					NaverRspVo tmpRspVo = new NaverRspVo();
					String tmpPeriod = ((String)tmpResultsJson2.get("period")).replace("-", "");
					Double tmpRatio = Double.parseDouble(String.valueOf(tmpResultsJson2.get("ratio")));
					Double tmpBaseRatio = baseKeywordRatio.get(tmpPeriod);
					Long tmpBaseCount = baseKeywordCount.get(tmpPeriod);
					
					Long tmpCalCount = (long)(tmpRatio/tmpBaseRatio*tmpBaseCount);
					 
					tmpRspVo.setKeyword(tmpTitle);//조회 검색어
					tmpRspVo.setKeywordId(tmpTitleKeywordId);//키워드 ID
					tmpRspVo.setPeriod(tmpPeriod);//조회일
					tmpRspVo.setRatio(tmpRatio);//조회 키워드 검색률
					tmpRspVo.setBaseRatio(tmpBaseRatio);//BASE 검색률
					tmpRspVo.setBaseCount(tmpBaseCount);//BASE 검색량
					tmpRspVo.setCalCount(tmpCalCount);//계산된 조회 검색어의 검색량
					
					returnData.add(tmpRspVo);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return returnData;
	}
	
	
	
	
	//------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------
	
	
	
//	public static void main(String[] args){
//		NvApiUtil util = new NvApiUtil();
//		util.serviceTest2();
//	}
//		
//
//	
//	private void serviceTest1() {
//		System.out.println("START START START START START START START START");
//		NvApiUtil util = new NvApiUtil();
//		
//		String start = "2018-01-01";
//		String end = "2018-01-05";
//		ArrayList<String> arr = new ArrayList<String>();
////		arr.add("아이유");
//		arr.add("구글");
////		arr.add("트와이스");
//		
//		JSONObject rslt = util.reqNvDataLab(start,end,arr);
//		ArrayList<NaverRspVo> result = util.parsNaverDataUsingBase(rslt);
//		
//		for(int i=0;i<result.size();i++) {
//			NaverRspVo rrrr = (NaverRspVo)result.get(i);
//			
//			System.out.println("getKeyword   ["+rrrr.getKeyword()+"]");
//			System.out.println("getPeriod    ["+rrrr.getPeriod()+"]");
//			System.out.println("getRatio     ["+rrrr.getRatio()+"]");
//			System.out.println("getBaseRatio ["+rrrr.getBaseRatio()+"]");
//			System.out.println("getBaseCount ["+rrrr.getBaseCount()+"]");
//			System.out.println("getCalCount  ["+rrrr.getCalCount()+"]");
//			System.out.println("----------------------------------------");
//			
//		}
//		
//		System.out.println("END END END END END END END END END END END");
//	}
//	
//	private void serviceTest2() {
//		System.out.println("[serviceTest2] START START START START START START START START");
//		NvApiUtil util = new NvApiUtil();
//		
//		String start = "2018-01-01";
//		String end = "2018-01-05";
//		ArrayList<String> arr = new ArrayList<String>();
////		arr.add("아이유");
//		arr.add(CommonConst.BASE_KEYWORD_NAME);
////		arr.add("트와이스");
//		
//		JSONObject rslt = util.reqNvDataLab(start,end,arr);
//		ArrayList<NaverRspVo> result = util.parsNaverDataUsingStandardDate(rslt, "20180101");
//		
//		for(int i=0;i<result.size();i++) {
//			NaverRspVo rrrr = (NaverRspVo)result.get(i);
//			
//			System.out.println("getKeyword   ["+rrrr.getKeyword()+"]");
//			System.out.println("getPeriod    ["+rrrr.getPeriod()+"]");
//			System.out.println("getRatio     ["+rrrr.getRatio()+"]");
//			System.out.println("getBaseRatio ["+rrrr.getBaseRatio()+"]");
//			System.out.println("getBaseCount ["+rrrr.getBaseCount()+"]");
//			System.out.println("getCalCount  ["+rrrr.getCalCount()+"]");
//			System.out.println("----------------------------------------");
//			
//		}
//		
//		System.out.println("[serviceTest2] END END END END END END END END END END END");
//	}
	
	
}
