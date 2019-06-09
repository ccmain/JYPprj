package com.kr.jyp.vo;

public class NaverRspVo {

	private String keywordId = "";
	private String keyword = "";
	private String period = "";
	private double ratio = 0;
	private long calCount = 0;
	
	private double baseRatio = 0;
	private long baseCount = 0;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public long getCalCount() {
		return calCount;
	}

	public void setCalCount(long calCount) {
		this.calCount = calCount;
	}

	public double getBaseRatio() {
		return baseRatio;
	}

	public void setBaseRatio(double baseRatio) {
		this.baseRatio = baseRatio;
	}

	public long getBaseCount() {
		return baseCount;
	}

	public void setBaseCount(long baseCount) {
		this.baseCount = baseCount;
	}

	public String getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(String keywordId) {
		this.keywordId = keywordId;
	}

	@Override
	public String toString(){
		String returnData = "";
		
		return returnData;
	}
}
