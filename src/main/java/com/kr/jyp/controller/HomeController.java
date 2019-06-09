package com.kr.jyp.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kr.jyp.service.TestClass;
import com.kr.jyp.vo.TestVoRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private TestVoRequest req;

	@Autowired
    public HomeController(TestVoRequest req){
    	this.req = req;
    }
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		System.out.println("Req1# "+req.getReq1());
		System.out.println("Req2# "+req.getReq2());
		System.out.println("Req3# "+req.getReq3());

		
		return "home";
	}
	
	@RequestMapping(value = "/jsTest", method = RequestMethod.GET)
	public String jsTest(Model model) {

		System.out.println("##### [RequestMapping value]["+ "jsTest" +"]");

		return "jsTest";
	}
	
	@RequestMapping(value = "/chartTest", method = RequestMethod.GET)
	public String chartTest(Model model) {

		ArrayList<String> colArr = new ArrayList<String>(); 
		colArr.add("일자");
		colArr.add("JYP");
		colArr.add("SM");
		colArr.add("KAKO");
		
		System.out.println("##### [RequestMapping value]["+ "chartTest" +"]");

		String chartData1 =
		"[\n" + 
		"          ['2018-01',  1000, 1000, 1001],\n" + 
		"          ['2018-02',  10, 300, 500],\n" + 
		"          ['2018-03',  30, 300, 500],\n" + 
		"          ['2018-04',  140, 300, 500],\n" + 
		"          ['2018-05',  50, 300, 500],\n" + 
		"          ['2018-06',  60, 300, 500], \n" + 
		"          ['2018-07',  70, 300, 700], \n" + 
		"          ['2018-08',  80, 300, 500], \n" + 
		"          ['2018-09',  90, 300, 900], \n" + 
		"          ['2018-10',  110, 300, 500], \n" + 
		"          ['2018-11',  120, 300, 500], \n" + 
		"          ['2018-12',  130, 300, 5100], \n" + 
		"        ]";

		
		String chartData2 =
		"[" + 
		"            ['박진영', 50],\n" + 
		"            ['수지', 200],\n" + 
		"            ['2PM', 50],\n" + 
		"            ['트와이스', 1000]\n" + 
		"          ]";
		
		model.addAttribute("colArr", colArr);
		model.addAttribute("chartD1", chartData1);
		model.addAttribute("chartD2", chartData2);
		
		return "chartTest";
	}
	
}
