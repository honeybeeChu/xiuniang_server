package com.xiuniang.xiuniang_server.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xiuniang.xiuniang_server.helper.DBHelper;


public class DianyuanInfoServlet extends HttpServlet {

//	private DianyuanInfoServlet wxNetAuthOperateBO;
	 Logger logger = Logger.getLogger(DianyuanInfoServlet.class);
     
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			logger.debug("doGet 开始");
			DBHelper help = new DBHelper();
			ResultSet result = help.GetResultSet("select * from dianyuan where OUT =0 or OUT is null;", null);
			String reString = null;
			if(result != null){
				reString = resultSetToJson(result);
			}else{
				reString = "get dianyuan data error.";
			}
			logger.info("the number of dianyuan is "+result.getRow());
			
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8"); 
			// 浏览器cache处理
//			response.setHeader("Pragma", "no-cache");
//			response.setHeader("Cache-Control", "no-cache");
//			response.setDateHeader("Expires", -10);
			logger.info(reString);
			response.getWriter().print(reString);
			logger.debug("doGet结束");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	public void init() {
		logger.error("servlet init....");
		
//		this.wxNetAuthOperateBO = (WXNetAuthOperateBO) BeanUtil.getBean("wxNetAuthOperateBO");
	}
	
	
	private String resultSetToJson(ResultSet rs) throws SQLException,JSONException  
	{  
	   // json数组  
	   JSONArray array = new JSONArray();  
	    
	   // 获取列数  
	   ResultSetMetaData metaData = rs.getMetaData();  
	   int columnCount = metaData.getColumnCount();  
	    
	   // 遍历ResultSet中的每条数据  
	    while (rs.next()) {  
	        JSONObject jsonObj = new JSONObject();  
	         
	        // 遍历每一列  
	        for (int i = 1; i <= columnCount; i++) {  
	            String columnName =metaData.getColumnLabel(i);
	            String value = rs.getString(columnName);
	            if(value == null)
	            	jsonObj.put(columnName, "null");
	            else
	            	jsonObj.put(columnName, value);
	            
	        }   
	        array.put(jsonObj);   
	    }  
	    
	   return array.toString();  
	}  
}
