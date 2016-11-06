package com.xiuniang.xiuniang_server.po;

import java.util.Date;

/**
 * @author wj
 *
 */
public class DianyuanInfo {

	public static final String TABLE_NAME = "DIANYUAN";
	private String dydm;
	private String dymc;
	
	/**
	 * 员工性别  '0':女   '1':男
	 */
	private String dyxb;
	
	/**
	 * 所属渠道，取YUDAO表中的qddm
	 */
	private String qddm;
	
	/**
	 * 所属商店，去kehu表中的xzdm=2的khdm
	 */
	private String khdm;
	
	/**
	 * 员工性质 0：普通店员 
	 * 1：店长
	 */
	private String xzdm;
	
	/**
	 * 停止使用字段
	 * 值 1.0000 停止使用
	 *  NULL 或 0.0000 不停止使用
	 */
	private int byzd4;
	
	/**
	 * 是否已经离职，
	 * 1：已经离职
	 *  NULL 或 0 不离职
	 */
	private int out;
	private String phone;
	private String mobile;
	private String email;
	private Date birthday;
	private String address;
	private Date in_date;
	private Date out_date;
	private Date lastChanged;
	
	public String getDydm() {
		return dydm;
	}



	public void setDydm(String dydm) {
		this.dydm = dydm;
	}



	public String getDymc() {
		return dymc;
	}


	public void setDymc(String dymc) {
		this.dymc = dymc;
	}


	public String getDyxb() {
		return dyxb;
	}


	public void setDyxb(String dyxb) {
		this.dyxb = dyxb;
	}

	public String getQddm() {
		return qddm;
	}

	public void setQddm(String qddm) {
		this.qddm = qddm;
	}

	public String getKhdm() {
		return khdm;
	}

	public void setKhdm(String khdm) {
		this.khdm = khdm;
	}

	public String getXzdm() {
		return xzdm;
	}

	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}

	public int getByzd4() {
		return byzd4;
	}

	public void setByzd4(int byzd4) {
		this.byzd4 = byzd4;
	}

	public int getOut() {
		return out;
	}



	public void setOut(int out) {
		this.out = out;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getIn_date() {
		return in_date;
	}

	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

	public Date getOut_date() {
		return out_date;
	}


	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}


	public Date getLastChanged() {
		return lastChanged;
	}

	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}


	public static String getTableName() {
		return TABLE_NAME;
	}


	@Override
	public String toString() {
		return "DIANYUAN [dydm=" + dydm + ", dymc=" + dymc
				+ ", dyxb=" + dyxb 
				+ ",qddm=" + qddm
				+ ", khdm=" + khdm
				+ ", xzdm=" + xzdm
				+ ", byzd4=" + byzd4
				+ ", out=" + out
				+ ", phone=" + phone
				+ ", mobile=" + mobile
				+ ", email=" + email
				+ ", birthday=" + birthday
				+ ", lastChanged=" + lastChanged
				+ ", in_date=" + in_date
				+ ", out_date=" + out_date
				+ ", address=" + address
				 + "]";
	}
}
