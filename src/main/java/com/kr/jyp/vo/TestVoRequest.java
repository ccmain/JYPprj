package com.kr.jyp.vo;

import org.springframework.stereotype.Component;

@Component()
public class TestVoRequest {

	private String req1="111";
	private String req2="222";
	private String req3="333";
	
	public String getReq1() {
		return req1;
	}
	public void setReq1(String req1) {
		this.req1 = req1;
	}
	public String getReq2() {
		return req2;
	}
	public void setReq2(String req2) {
		this.req2 = req2;
	}
	public String getReq3() {
		return req3;
	}
	public void setReq3(String req3) {
		this.req3 = req3;
	}
	public String getReq4() {
		return req4;
	}
	public void setReq4(String req4) {
		this.req4 = req4;
	}
	private String req4;
}
