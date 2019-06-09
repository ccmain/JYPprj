package com.kr.jyp.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kr.jyp.vo.TestVoRequest;
import com.kr.jyp.vo.TestVoResponse;

@RestController
@RequestMapping(value = "/rest", method = RequestMethod.POST, headers = "content-type=application/json")
public class RestInfoController {
    
	/**
	 * 테스트용 샘플 메소드
	 * @param inputVo
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public TestVoResponse testCall(@RequestBody TestVoRequest inputVo){
		TestVoResponse respone = new TestVoResponse();
		
		respone.setRes1(inputVo.getReq1()+"-response");
		respone.setRes2(inputVo.getReq2()+"-response");
		respone.setRes3(inputVo.getReq3()+"-response");
		
		return respone;
	}
			
}
