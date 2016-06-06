<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "net.sf.json.*" %>
<%!
	private Map<String, Object> mapJson = null;
	private String strSvcCd = null;
	
	public Map<String, Object> jsonTomap(String JsonData) {
		
		System.out.println("################# Where is the Error 1 #################");
		System.out.println("JsonData : " + JsonData);
		mapJson = JSONObject.fromObject(JsonData);
		System.out.println("################# Where is the Error 2 #################");
		strSvcCd = (String) mapJson.get("svccd");
		System.out.println("################# Where is the Error 3 #################");
		
		return (Map<String, Object>)((List)mapJson.get("req_data")).get(0);
	}
	
	
	public String getSvcCd() {
		return (String) mapJson.get("svccd");
	}
%>