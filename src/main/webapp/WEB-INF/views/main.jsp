<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>

<head>
	<meta charset="UTF-8">
	<title>Main</title>
	
	<link rel="stylesheet" href="resources/css/mainStyle.css">
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>

<body>
	<div class="fixedTopLayer">
		<P>  The time on the server is ${serverTime}. </P>
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
				hello world!!
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

