package com.xiuniang.xiuniang_server.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.xiuniang.xiuniang_server.helper.DBHelper;


public class SyncVipInfo2EfastServlet extends HttpServlet {

//	private DianyuanInfoServlet wxNetAuthOperateBO;
	 Logger logger = Logger.getLogger(SyncVipInfo2EfastServlet.class);
     
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
//			//顾客代码  随机数
//			String dm = getDMbyTimeRadom();
//			//顾客名称
//			String gkmc = request.getParameter("gkmc");
//			//手机号
//			String sj = request.getParameter("phone");
//			//性别
//			String sex = request.getParameter("sex");
//			//生日
//			String sr = request.getParameter("birthday");
//			//创建日期
//			String JDRQ = getCurrentTime();
//			//门店代码
//			String CKDM = request.getParameter("shopid");
//			//地址
//			String DZ = request.getParameter("address");
			
			Map<String,String> customMap = new HashMap<String,String>();
			
			//顾客代码  随机数
			customMap.put("card_id", request.getParameter("card_id"));
			//顾客代码  随机数
			customMap.put("dm", getDMbyTimeRadom());
			//顾客名称
			customMap.put("gkmc", request.getParameter("gkmc"));
			//手机号
			customMap.put("phone", request.getParameter("phone"));
			//性别
			customMap.put("sex", request.getParameter("sex"));
			//生日
			customMap.put("birthday", request.getParameter("birthday"));
			//创建日期
			customMap.put("jdrq", getCurrentTime());
			//门店代码
			customMap.put("shopid", request.getParameter("shopid"));
			//地址
			customMap.put("address", request.getParameter("address"));
			//会员卡折扣
			customMap.put("rate", request.getParameter("rate"));
			String insertCustomerSql = getInsertGKInfoSql(customMap);
			
			logger.debug("doGet 开始");
			DBHelper help = new DBHelper();
			int insertCustomerCount = help.UpdateData(insertCustomerSql, null);
			
			String reString=null;
			if(insertCustomerCount > 0){
				String InsertVipSqlStr = getInsertVipjInfoSql(customMap);
				int insertVIPCount = help.UpdateData(InsertVipSqlStr, null);
				logger.info("the number of insert vip " + insertVIPCount);
			}else{
				reString = "insert customer info error";
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
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
	/**
	 * @param customMap
	 * @return
	 */
	private String getInsertGKInfoSql(Map<String,String> customMap){
		StringBuffer sqlstr = new StringBuffer();
		sqlstr.append("INSERT INTO V_CUSTOMER (DM,GKMC,SEX ,SR,QYDM,QDDM,CKDM,JDRQ,XFJE,XFSL,DQJF,DZ,SJ,DH) VALUES (")
		.append(customMap.get("dm"))
		.append(",")
		.append(customMap.get("gkmc"))
		.append(",")
		.append(customMap.get("sex"))
		.append(",")
		.append(customMap.get("birthday"))
		.append(",")
		.append("'000'")
		.append(",")
		.append("'000'")
		.append(",")
		.append(customMap.get("shopid"))
		.append(",")
		.append(customMap.get("jdrq"))
		.append(",")
		.append("'0'")
		.append(",")
		.append("'0'")
		.append(",")
		.append("'0'")
		.append(",")
		.append(customMap.get("address"))
		.append(",")
		.append(customMap.get("phone"))
		.append(",")
		.append(customMap.get("phone"))
		.append(")");
		return sqlstr.toString();
	}
	
	private String getInsertVipjInfoSql(Map<String,String> customMap) {
		StringBuffer sqlstr = new StringBuffer();
		sqlstr.append("INSERT INTO V_CUSTOMER (DM,MC,GKDM ,XLDM,KLDM,QDDM,QYDM,CKDM,QYBJ,TYBJ ,QYRQ,JDRQ,TYRQ,ZK,XFJE,XFSL,QYJF,DQJF,BYZD2) VALUES (")
		.append(customMap.get("card_id"))
		.append(",")
		.append(customMap.get("gkmc"))
		.append(",")
		.append(customMap.get("dm"))
		.append(",'006','004','000','000',")
		.append(customMap.get("shopid"))
		.append(",")
		.append("'1','0',")
		.append(getCurrentTime()) //启用日期
		.append(",")
		.append(getCurrentTime()) //建档日期
		.append(",")
		.append("'2057-08-02 00:00:00.000'") //停用日期
		.append(",")
		.append(customMap.get("rate"))	//ZK会员卡折扣（0到1）
		.append(",'0','0','0','0','0'")			//会员消费金额,会员消费数量,会员卡启用积分,会员卡当前积分,VIP卡适用范围（全店通用为0）
		.append(")");
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
	
	
	
	private static String getDMbyTimeRadom() {
		return String.valueOf(Calendar.getInstance().getTimeInMillis());
	}
	
	
	private String getCurrentTime() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(new Date());
	}
	
	
	public static void main(String args[]) {
		System.out.println(getDMbyTimeRadom());
	}
}
