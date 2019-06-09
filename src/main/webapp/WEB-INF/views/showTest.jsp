<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<link rel="stylesheet" href="../resources/css/mainStyle.css">
	
	<title>Home</title>
	
  	<script type="text/javascript">
  		var columnArr = new Array;
  		columnArr = ${colArr};
  		
	  	google.charts.load('current', {'packages':['line','corechart']});
	    google.charts.setOnLoadCallback(drawChart1);
	    
	    google.charts.setOnLoadCallback(drawChart2);
	
	    function drawChart1() {
	        var data = new google.visualization.DataTable();
	        
	        data.addColumn('string', columnArr[0]);
	        data.addColumn('number', columnArr[1]);
	        data.addColumn('number', columnArr[2]);
	        data.addColumn('number', columnArr[3]);
	
	        data.addRows( ${chartD1} );
	        
	        var options = {
	                chart: {
	                  title: '기획사별 일별 검색량',
	                  subtitle: '(건/일)'
	                },
	                width: 500,
	                height: 300
	              };
	        
	        var chart = new google.charts.Line(document.getElementById('chart_div1'));
	
	        chart.draw(data, google.charts.Line.convertOptions(options));
	      }
	    
	    function drawChart2() {
	
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', '이름');
	        data.addColumn('number', '건수');
	        
	        data.addRows( ${chartD2} );
	
	        // Set options for Anthony's pie chart.
	        var options = {title:'해당 기획사 월별 조회현황',
	                       width:400,
	                       height:300};
	
	        // Instantiate and draw the chart for Anthony's pizza.
	        var chart = new google.visualization.BarChart(document.getElementById('chart_div2'));
	        chart.draw(data, options);
	      }
    
	</script>
	
</head>
<body>

<div class="fixedTopLayer">
	<P>  The time on the server is ${serverTime}. </P>
	<P>  The time on the server is ${selData}. </P>
</div>

<div class="container">
    <header>
        <h1>그래프</h1>
    </header>
    <section class="content">
        <nav>
            <ul>
                <li>SM</li>
                <li>JYP</li>
                <li>kakao M</li>
            </ul>
        </nav>
        <main>
        	<div id="chart_div1" style="border: 1px solid #ccc"></div>
			<div id="chart_div2" style="border: 1px solid #ccc"></div>
        </main>
        <aside>
            (기타 컨텐츠)
        </aside>
    </section>
    <footer>
        <a href="http://localhost:8080/jypil/chartTest">샘플 차트 보기</a>
    </footer>
</div>




</body>
</html>
