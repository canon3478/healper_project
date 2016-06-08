<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./gateway/index.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String param = request.getParameter("jsonData");


	if("its".equals(param)) {
		
		out.print("aaaaa");
	}
	else {
		out.print("");
	}

%>

