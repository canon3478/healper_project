<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "javax.naming.*,javax.sql.*" %>


<%!
	public Map<String, Object> MT0001execute(Map<String, Object> mapRes){
		Map<String, Object> mapResData = new HashMap<String, Object>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean log_in_success;
		ArrayList<Float> times = new ArrayList<Float>();
		
		try {
			String sqlquery = "SELECT UPW FROM Users WHERE UID = ?";
			String strUid = (String)mapRes.get("uid");
			String strUpw = (String)mapRes.get("upw");
			conn = DriverManager.getConnection("jdbc:postgresql://dororocinstance.c6efxm6vekfm.ap-northeast-1.rds.amazonaws.com:5432/healperDB", "healper", "healperqwer1234");
	    	pstm = conn.prepareStatement(sqlquery);
	    	pstm.setString(1, strUid);
	    	rs = pstm.executeQuery();
	    	
	    	if(rs.next()) {
		    	if(strUpw.equals(rs.getString("UPW"))) {
		    		try {
			    		sqlquery = "SELECT * FROM data WHERE UID = ?";
			    		pstm = conn.prepareStatement(sqlquery);
				    	pstm.setString(1, strUid);
				    	rs = pstm.executeQuery();
				    	
				    	while(rs.next()) {
				    		times.add(rs.getFloat("utime"));
				    	}
				    	
				    	mapResData.put("data",times);
			    	}
			    	catch (Exception ex) {
			    		ex.printStackTrace();
			    	} 
		    		mapResData.put("Log-In", "Success");
		    	}
	    	}
	    	else {
		    	mapResData.put("Log-in", "Failed");
	    	}

			if(conn!=null)
				conn.close();
			if(pstm!=null)
				pstm.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		mapResData.put("svccd","MT0001");
		return mapResData;
	
    }
%>
