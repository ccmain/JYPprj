package com.kr.jyp.vo;

import java.util.ArrayList;

public class LineChartVo {

	ArrayList<String> lineColumnArr = new ArrayList<String>();
	ArrayList<ArrayList<Object>> lineChartDataArr = new ArrayList<ArrayList<Object>>();
	
	public ArrayList<String> getLineColumnArr() {
		return lineColumnArr;
	}
	public void setLineColumnArr(ArrayList<String> lineColumnArr) {
		this.lineColumnArr = lineColumnArr;
	}
	public ArrayList<ArrayList<Object>> getLineChartDataArr() {
		return lineChartDataArr;
	}
	public void setLineChartDataArr(ArrayList<ArrayList<Object>> lineChartDataArr) {
		this.lineChartDataArr = lineChartDataArr;
	}

}
