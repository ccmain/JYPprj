package com.kr.jyp.service;

import java.util.ArrayList;

import com.kr.jyp.vo.ResultVo;

public interface NvApiService {

	public ResultVo setBaseDataService();
	
	public ResultVo setSearchDataService(String startDt, String endDt, ArrayList<String> searchKeywordArr);
	


}
