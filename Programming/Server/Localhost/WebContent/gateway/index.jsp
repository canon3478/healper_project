<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="./util/JsonToMap.jsp" %>
<%@ include file="./svc/MT0001.jsp" %>
<%@ include file="./svc/MT0002.jsp" %>
<%@ include file="./svc/MT0003.jsp" %>

<%
	Class.forName("org.postgresql.Driver");

	String UNKNOWN_ERROR_MSG = "알 수 없는 오류가 발생하였습니다.";
	String strJsonData = "";
	String result = "";
	
	long start = System.currentTimeMillis(); 

	//JSON 결과
	Map<String, Object> mapResJson = new HashMap<String, Object>();
	Map<String, Object> mapRsltJson = new HashMap<String, Object>();
	ArrayList<Map<String, Object>> arrayRsltJson = new ArrayList<Map<String, Object>>();
		
	try{
		strJsonData = new String(request.getParameter("JSONData").getBytes("8859_1"),"UTF-8");

		//JOSN 파싱
		Map<String, Object> mapReqJson = jsonTomap(strJsonData); // JSON REQUEST DATA 
		String strSvcCd = getSvcCd(); // JSON 서비스 코드
		
		System.out.println("################# Request Data #################");
		System.out.println("Address::"+request.getRemoteAddr());
		System.out.println("JSON::"+strJsonData);
		System.out.println("Service::"+strSvcCd);
		System.out.println("Data::"+mapReqJson.toString());
		
		if(strSvcCd.equals("MT0001")){ // LogIn
			mapResJson = MT0001execute(mapReqJson);
		} else if (strSvcCd.equals("MT0002")) { // Sign Up
			mapResJson = MT0002execute(mapReqJson);
		} else if (strSvcCd.equals("MT0003")) { // 중복확인
			mapResJson = MT0003execute(mapReqJson);
		}
		
	} catch (Exception ex) {
		ex.printStackTrace();
		mapResJson.put("error", UNKNOWN_ERROR_MSG);
	}

	arrayRsltJson.add(mapResJson);
	mapRsltJson.put("res_data", arrayRsltJson);
	mapRsltJson.put("svccd", strSvcCd);
	
	JSONObject jsonOut = JSONObject.fromObject(mapRsltJson);
	
	//out.print(jsonOut.toString());
	out.print(jsonOut.get("res_data"));
	
	long end = System.currentTimeMillis(); 
	
	System.out.println();
	System.out.println("################# Response Data #################");
	System.out.println("Data::"+mapResJson.toString());
	System.out.println("소요시간::"+(end-start)+"ms");
	System.out.println();
	System.out.println();
	
%>