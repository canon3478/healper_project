<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "javax.naming.*,javax.sql.*" %>

<%!

	public Map<String, Object> MT0003execute(Map<String, Object> mapRes){
	Map<String, Object> mapResData = new HashMap<String, Object>();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	try {
		
		String sqlquery = "SELECT * FROM Users WHERE UId = ?";

		String strUid = (String)mapRes.get("uid");
		conn = DriverManager.getConnection("jdbc:postgresql://dororocinstance.c6efxm6vekfm.ap-northeast-1.rds.amazonaws.com:5432/healperDB", "healper", "healperqwer1234");
    	pstm = conn.prepareStatement(sqlquery);
    	pstm.setString(1, strUid);
    	rs = pstm.executeQuery();
    	
    	if(rs.next()) 
    		mapResData.put("isOK", "Impossible");
    	else
    		mapResData.put("isOK", "Possible");
    	
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
		
	}
	mapResData.put("svccd","MT0003");
	
	return mapResData;
	
}

%>
