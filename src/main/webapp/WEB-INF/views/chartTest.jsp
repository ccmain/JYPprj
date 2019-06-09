<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>CHARTE</title>
	
	<script src="resources/js/example4.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

  	<script type="text/javascript">
  		/* 라인차트 항목 Array 셋팅 */
		var lineChartColumnArr = new Array;
		<c:forEach items="${lineColArr}" var="col">
			lineChartColumnArr.push("${col}");
		</c:forEach>
	
		/* 구글 차트 설정 */
	  	google.charts.load('current', {'packages':['line','corechart']});
	    google.charts.setOnLoadCallback(drawLineChart);
	    google.charts.setOnLoadCallback(drawBarChart);
	
	    /* function 라인 차트 */
	    function drawLineChart() {
	        var data = new google.visualization.DataTable();
	        
	    	for (var i = 0; i < lineChartColumnArr.length; i++) {
	    		if(i==0){
	    			data.addColumn('string', lineChartColumnArr[i]);
	    		}
	    		else{
	    			data.addColumn('number', lineChartColumnArr[i]);
	    		}
	    	}
	
	        data.addRows( ${lineChartData} );
	        
	        var options = {
	                chart: {
	                  title: '연예인 일별 검색포인트',
	                  subtitle: '(point/일)'
	                },
	                width: 500,
	                height: 300
	              };
	        
	        var chart = new google.charts.Line(document.getElementById('chart_div1'));
	
	        chart.draw(data, google.charts.Line.convertOptions(options));
	      }
	    
	    /* function 바 차트 */
	    function drawBarChart() {
	
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', '이름');
	        data.addColumn('number', '건수');
	        
	        data.addRows( ${barChartData} );
	
	        // Set options for Anthony's pie chart.
	        var options = {title:'해당 기획사 월별 조회현황',
	                       width:400,
	                       height:300};
	
	        // Instantiate and draw the chart for Anthony's pizza.
	        var chart = new google.visualization.BarChart(document.getElementById('chart_div2'));
	        chart.draw(data, options);
	      }
	    
	    /*
	   	document.write( ${chartD1},"<br>" );
	   	document.write( ${chartD2},"<br>" );
	   	*/
    
  	</script>
</head>
<body>
	<td><div id="chart_div1" style="border: 1px solid #ccc"></div></td>
	<td><div id="chart_div2" style="border: 1px solid #ccc"></div></td>
</body>

</html>

