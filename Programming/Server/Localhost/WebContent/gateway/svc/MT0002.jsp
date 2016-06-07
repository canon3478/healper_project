<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "javax.naming.*,javax.sql.*" %>

<%!

	public Map<String, Object> MT0002execute(Map<String, Object> mapRes){
	Map<String, Object> mapResData = new HashMap<String, Object>();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	try {
		
		String sqlquery = "INSERT INTO Users Values (?, ?, ?, ?)";

		String strUid = (String)mapRes.get("uid");
		String strUpw = (String)mapRes.get("upw");
		String strEmail = (String)mapRes.get("uemail");
		String strName = (String)mapRes.get("uname");
		conn = DriverManager.getConnection("jdbc:postgresql://dororocinstance.c6efxm6vekfm.ap-northeast-1.rds.amazonaws.com:5432/healperDB", "healper", "healperqwer1234");
    	pstm = conn.prepareStatement(sqlquery);
    	pstm.setString(1, strUid);
    	pstm.setString(2, strUpw);
    	pstm.setString(3, strName);
    	pstm.setString(4, strEmail);
    	pstm.executeUpdate();
    	
    	mapResData.put("INSERT", "Success");
    	
    	/* 
    	여러개
    	while(rs.next()) {
    		
    	} */
		if(conn!=null)
			conn.close();
		if(pstm!=null)
			pstm.close();
    	
	} catch (Exception ex) {
		ex.printStackTrace();
		mapResData.put("INSERT", "Failed");
	}
	mapResData.put("svccd","MT0002");
	
	return mapResData;
	
}
%>
