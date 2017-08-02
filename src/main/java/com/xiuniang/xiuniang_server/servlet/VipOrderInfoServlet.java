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


public class VipOrderInfoServlet extends HttpServlet {

//	private DianyuanInfoServlet wxNetAuthOperateBO;
	 Logger logger = Logger.getLogger(VipOrderInfoServlet.class);
     
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String start_time = request.getParameter("start_time");
			String sqlStr = getOrderByLastTimeStr(start_time);
			logger.debug("doGet 开始");
			DBHelper help = new DBHelper();
			ResultSet result = help.GetResultSet(sqlStr, null);
			String reString = null;
			if(result != null){
				reString = resultSetToJson(result);
				logger.info("the number of vip orders is "+result.getRow());
			}else{
				reString = "get vip orders data error.";
			}
			
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
			logger.error(e.toString());
		}
	}
	
	private String getOrderByLastTimeStr(String last_time){
		StringBuffer sqlstr = new StringBuffer();
		sqlstr.append("select a.vVIPcard as vipCard,a.vMBillID as vmbillid, a.dtDate as tradeDate,c.GKMC as gkmc,c.sex as sex, a.fGetMoney as getMoney, c.SJ as telephone,a.vShop as vshop,a.vEmpCode as vempcode,a.vSPCode as vspcode")
		.append(" from sg_gathering a,V_VIPSET b , V_CUSTOMER c ")
		.append(" where a.vVIPcard = b.DM and b.GKDM = c.DM and a.dtDate>'")
		.append(last_time)
		.append("' and a.vVIPcard!='null' and c.SJ != 'null';");
		return sqlstr.toString();
	}
	
//	public static void main(String args[]){
//		System.out.println(getOrderByLastTimeStr("2017-06-20 16:34:31.000"));
//	}

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
