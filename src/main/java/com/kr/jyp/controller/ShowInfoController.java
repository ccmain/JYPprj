package com.kr.jyp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kr.jyp.service.ShowInfoService;
import com.kr.jyp.vo.LineChartVo;

@Controller
public class ShowInfoController {
    
    private ShowInfoService chartService;
	
    @Autowired
	public ShowInfoController(ShowInfoService chartService) {
		this.chartService = chartService;
	}
	
	@RequestMapping(value = "/baseChart", method = RequestMethod.GET)
	public String baseChart(@RequestParam Map<String, Object> paramMap,Locale locale, Model model) {

		// http://localhost:8080/jypil/baseChart?startDt=20180101&endDt=20180201&names=아이유
		try {
			//----------------------
			// set input parameter
			//----------------------
			String startDt ="";
			String endDt ="";
			String names = "";
			List<String> nameArr = null;
			
			try {
				startDt = (String)paramMap.get("startDt");
				endDt = (String)paramMap.get("endDt");
				names = (String)paramMap.get("names");
				nameArr = Arrays.asList(names.split(","));
			}
			catch(NullPointerException ne){
				return "err";
			}
			
			//----------------------
			// Line chart data
			//----------------------
			LineChartVo lineChart = chartService.getLineChartData(startDt, endDt, nameArr);
			
			
			System.out.println("[lineChart] "+lineChart.getLineColumnArr());
			System.out.println("[lineChart] "+lineChart.getLineChartDataArr());
			
			
			model.addAttribute("lineColArr", lineChart.getLineColumnArr());
			model.addAttribute("lineChartData", lineChart.getLineChartDataArr().toString());
			
			//----------------------
			// Bar chart data
			//----------------------
//			String barChartData =
//				"[" + 
//				"            ['박진영', 50],\n" + 
//				"            ['수지', 200],\n" + 
//				"            ['2PM', 50],\n" + 
//				"            ['트와이스', 1000]\n" + 
//				"          ]";
//			model.addAttribute("barChartData", barChartData);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "chartTest";
	}

}
