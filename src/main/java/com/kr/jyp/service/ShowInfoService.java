package com.kr.jyp.service;

import java.util.List;

import com.kr.jyp.vo.LineChartVo;

public interface ShowInfoService {

	public String showChart();
	
	public LineChartVo getLineChartData(String startDt, String endDt, List<String> nameArr);

}
