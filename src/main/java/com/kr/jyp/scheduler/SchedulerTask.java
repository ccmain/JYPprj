package com.kr.jyp.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kr.jyp.constant.CommonConst;
import com.kr.jyp.service.NvApiService;
import com.kr.jyp.service.impl.NvApiServiceImpl;
import com.kr.jyp.vo.NaverRspVo;

@Service
public class SchedulerTask {

   	private Date today;
   	private SimpleDateFormat dateTimeFormat;
	
   	// Autowired: 네이버 검색 결과 조회
    private NvApiService nvApiService;
	
    //--------------------------------------
    // Constructor
    //--------------------------------------
	@Autowired
	public SchedulerTask(NvApiService nvApiService) {
   		this.today = new Date();
   		this.dateTimeFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
   		this.nvApiService = nvApiService;
	}
	
//    @Scheduled(fixedDelay = 999999*1000)
//    public void scheulerFixedDelay(){
//        System.out.println("##### fixedDelay 스케줄링 테스트 ["+ dateTimeFormat.format(today) +"]");
//    }
//
//    @Scheduled(cron = " 0 58 21 * * * ")
//    public void scheulerCron(){
//        System.out.println("##### cron 스케줄링 테스트 ["+ dateTimeFormat.format(today) +"]");
//    }
    
	/*******************************************************************
	 * 
	 * 
	 * 
	 ******************************************************************/
//    @Scheduled(fixedDelay = 999999*1000)
//    public void scheulerSetBaseData(){
//    	System.out.println("[scheulerSetBaseData] START START START START START START START START");
//    	System.out.println("[scheulerSetBaseData] 스케줄링 ["+ dateTimeFormat.format(today) +"]");
//    	
//    	nvApiService.setBaseDataService();
//    	
//    	System.out.println("[scheulerSetBaseData] END END END END END END END END END END END");
//    }
    
    
//    @Scheduled(fixedDelay = 999999*1000)
//    public void scheulerSetSearchData(){
//    	System.out.println("[scheulerSetSearchData] START START START START START START START START");
//    	System.out.println("[scheulerSetSearchData] 스케줄링 ["+ dateTimeFormat.format(today) +"]");
//    	
//    	String startDt = "20180101";
//    	String endDt = "20180131";
//    	ArrayList<String> searchKeywordArr = new ArrayList<String>();
//    	searchKeywordArr.add(CommonConst.BASE_KEYWORD_NAME);
//    	searchKeywordArr.add("한승연");
//    	
//    	
//    	nvApiService.setSearchDataService(startDt, endDt, searchKeywordArr);
//    	
//    	
//    	System.out.println("[scheulerSetSearchData] END END END END END END END END END END END");
//    }
    
}
