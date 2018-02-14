package com.postalweb.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement
@JsonInclude(Include.NON_DEFAULT)
public class Client {
private String mobileNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private String age;
	
	private String doorNo;
	
	private String streetName;
	
	private String areaName;
	
	private String taluk;
	
	private String  city;
	
	private String pincode;
	
	private String state;
	
	private String status;
	
	private String clientid;
	 
	private String loginid;
	private String leadst;
	private String totalcount;
	private String count;
	private String total;
	private String approve;
	private String billv;
	public String getBillv() {
		return billv;
	}

	public void setBillv(String billv) {
		this.billv = billv;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getLeadst() {
		return leadst;
	}

	public void setLeadst(String leadst) {
		this.leadst = leadst;
	}

	private String count2;
	private String cityname;
	private String statename;
   private String leadstatus;
   private String rmarks; 
	public String getRmarks() {
	return rmarks;
}

public void setRmarks(String rmarks) {
	this.rmarks = rmarks;
}

	public String getLeadstatus() {
	return leadstatus;
}

public void setLeadstatus(String leadstatus) {
	this.leadstatus = leadstatus;
}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCount2() {
		return count2;
	}

	public void setCount2(String count2) {
		this.count2 = count2;
	}
	
	@Override
	public String toString() {
		return "Client [mobileNumber=" + mobileNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", age=" + age + ", doorNo=" + doorNo + ", streetName="
				+ streetName + ", areaName=" + areaName + ", taluk=" + taluk
				+ ", city=" + city + ", pincode=" + pincode + ", state="
				+ state + ", status=" + status + ", clientid=" + clientid
				+ ", loginid=" + loginid + ", leadst=" + leadst
				+ ", totalcount=" + totalcount + ", count=" + count
				+ ", total=" + total + ", approve=" + approve + ", billv="
				+ billv + ", count2=" + count2 + ", cityname=" + cityname
				+ ", statename=" + statename + ", leadstatus=" + leadstatus
				+ ", rmarks=" + rmarks + ", lead_id=" + lead_id
				+ ", lead_created_date=" + lead_created_date
				+ ", lead_verified_date=" + lead_verified_date
				+ ", lead_approved_date=" + lead_approved_date
				+ ", lead_assigned_to=" + lead_assigned_to + ", remarks="
				+ remarks + ", approved_status=" + approved_status
				+ ", billing_price=" + billing_price + ", online_price="
				+ online_price + ", offline_price=" + offline_price
				+ ", lead_status=" + lead_status + ", lead=" + lead
				+ ", clientname=" + clientname + ", statusa=" + statusa
				+ ", sno=" + sno + ", password=" + password + "]";
	}

	private String lead_id;
	private Date lead_created_date;
	private Date lead_verified_date;
	private Date lead_approved_date;
	private String lead_assigned_to;
	private int remarks;
	private int approved_status;
	private String billing_price;
	private String online_price;
	private String offline_price;

	public String getOnline_price() {
		return online_price;
	}

	public void setOnline_price(String online_price) {
		this.online_price = online_price;
	}

	public String getOffline_price() {
		return offline_price;
	}

	public void setOffline_price(String offline_price) {
		this.offline_price = offline_price;
	}

	public String getBilling_price() {
		return billing_price;
	}

	public void setBilling_price(String billing_price) {
		this.billing_price = billing_price;
	}

	public Date getLead_verified_date() {
		return lead_verified_date;
	}

	public void setLead_verified_date(Date lead_verified_date) {
		this.lead_verified_date = lead_verified_date;
	}

	public Date getLead_approved_date() {
		return lead_approved_date;
	}

	public void setLead_approved_date(Date lead_approved_date) {
		this.lead_approved_date = lead_approved_date;
	}

	public String getLead_assigned_to() {
		return lead_assigned_to;
	}

	public void setLead_assigned_to(String lead_assigned_to) {
		this.lead_assigned_to = lead_assigned_to;
	}

	public int getRemarks() {
		return remarks;
	}

	public void setRemarks(int remarks) {
		this.remarks = remarks;
	}

	public int getApproved_status() {
		return approved_status;
	}

	public void setApproved_status(int approved_status) {
		this.approved_status = approved_status;
	}

	public String getLead_id() {
		return lead_id;
	}

	public void setLead_id(String lead_id) {
		this.lead_id = lead_id;
	}

	

	public Date getLead_created_date() {
		return lead_created_date;
	}

	public void setLead_created_date(Date lead_created_date) {
		this.lead_created_date = lead_created_date;
	}

	public String getLead_status() {
		return lead_status;
	}

	public void setLead_status(String lead_status) {
		this.lead_status = lead_status;
	}

	private String lead_status;
	
	private List<Client> lead = new ArrayList<>();
	
	public String getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}

	public List<Client> getLead() {
		return lead;
	}

	public void setLead(List<Client> lead) {
		this.lead = lead;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	private String clientname;
	private boolean statusa;
	private int sno;
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public boolean isStatusa() {
		return statusa;
	}

	public void setStatusa(boolean statusa) {
		this.statusa = statusa;
	}

	private String password;

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getTaluk() {
		return taluk;
	}

	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
