package com.kr.jyp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kr.jyp.dao.NvApiDao;
import com.kr.jyp.dao.ShowInfoDao;
import com.kr.jyp.service.ShowInfoService;
import com.kr.jyp.vo.LineChartVo;
import com.sun.xml.internal.ws.api.Cancelable;

@Service("ShowInfoService")
public class ShowInfoServiceImpl implements ShowInfoService {

	private ShowInfoDao InfoDao;
	private NvApiDao nvDao;
	
	@Autowired
	public ShowInfoServiceImpl(ShowInfoDao InfoDao, NvApiDao nvDao) {
		this.InfoDao = InfoDao;
		this.nvDao = nvDao;
	}
	
	
	/********************************************************************
	 * 
	 * 
	 * 
	 *******************************************************************/
	public String showChart() {
		String rslt ="";
		System.out.println("[START] showChart");
		
		try {
			rslt = InfoDao.getData("");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("[END] showChart");
		}
		
		return rslt;
	}
	
	
	/********************************************************************
	 * 
	 * 
	 * 
	 *******************************************************************/
	public LineChartVo getLineChartData(String startDt, String endDt, List<String> nameArr) {
		LineChartVo returnData = new LineChartVo();
		
		try {
			
			// 조회날짜 설정 >> util 메소드화 필요
			ArrayList<String> dateTerm = new ArrayList<String>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
			Date settingStartDate = sdf.parse(startDt);
			Date settingEndtDate = sdf.parse(endDt);
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(settingStartDate);
			
			while(true) {
				dateTerm.add(sdf.format(cal.getTime()));
				if(cal.getTime().compareTo(settingEndtDate) == 0 ) {
					break;
				}
				cal.add(Calendar.DATE, 1);
			}
			
			ArrayList<HashMap> tmpList = new ArrayList<HashMap>();
			
			returnData.getLineColumnArr().add("일자");
			for(String name : nameArr) {
				returnData.getLineColumnArr().add(name);
				String tmpId = nvDao.selectKeywordIdByName(name);
				
				System.out.println("[tmpId]"+tmpId);
				
				HashMap<String, Long> tmpCountList = nvDao.selectSearchCountByDateTerms(startDt,endDt,tmpId);
				tmpList.add(tmpCountList);
			}
			
			for(String dt : dateTerm) {
				ArrayList<Object> lineChartDataArrTmp = new ArrayList<Object>();
				lineChartDataArrTmp.add("'"+ dt +"'");
				for(HashMap<String, Long> dataMap : tmpList) {
					lineChartDataArrTmp.add(dataMap.get(dt));
				}
				returnData.getLineChartDataArr().add(lineChartDataArrTmp);
			}
			
			
//			System.out.println("[returnData.getLineChartDataArr]\n"+returnData.getLineChartDataArr());
			

			
//			ArrayList<Object> lineChartDataArr1 = new ArrayList<Object>();
//			lineChartDataArr1.add("'"+ "201801" +"'");
//			lineChartDataArr1.add(10);
//			lineChartDataArr1.add(100);
//			lineChartDataArr1.add(150);
//			lineChartDataArr1.add(1);
//			
//			ArrayList<Object> lineChartDataArr2 = new ArrayList<Object>();
//			lineChartDataArr2.add("'"+ "201802" +"'");
//			lineChartDataArr2.add(20);
//			lineChartDataArr2.add(50);
//			lineChartDataArr2.add(30);
//			lineChartDataArr2.add(2);
//			
//			ArrayList<Object> lineChartDataArr3 = new ArrayList<Object>();
//			lineChartDataArr3.add("'"+ "201803" +"'");
//			lineChartDataArr3.add(30);
//			lineChartDataArr3.add(50);
//			lineChartDataArr3.add(70);
//			lineChartDataArr3.add(30);
//			
//			returnData.getLineChartDataArr().add(lineChartDataArr1);
//			returnData.getLineChartDataArr().add(lineChartDataArr2);
//			returnData.getLineChartDataArr().add(lineChartDataArr3);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

		
	
		
		return returnData;
	}
	
	
}
