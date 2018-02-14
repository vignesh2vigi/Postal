package com.postalweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.postalweb.config.Db;
import com.postalweb.model.Client;

public class ClientDaoImpl implements ClientDao {
	Connection con=null;

	
	@Override
	public Client addlead(Client client)  {
	
	
     return client;
}
	
	@Override
	public Client login(Client client) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		Client login_model = new Client();

		boolean flg= false;
		try {

			Db db = new Db();

			con = db.mysqlConnect();

			stmt = con.createStatement();

			String clientname=client.getClientname();
			String password=client.getPassword();

			String login_query = "SELECT clientname,password FROM client_login_details WHERE clientname='"+ clientname + "'AND password='"+ password + "'";

			System.out.println("beatlogin_query:"+login_query);

			rs = stmt.executeQuery(login_query);

			boolean last = rs.last();

			int row = rs.getRow();

			rs.beforeFirst();

			if (row == 0) {
				login_model.setStatus("No user found");

			} else {

				while (rs.next()) {

					String password1= rs.getString("password");

					if(password1!=null && password1.length()> 0){

						login_model.setStatus("SUCCESS");
						
						login_model.setClientname(rs.getString("clientname"));
						login_model.setClientid(rs.getString("password"));
						
						login_model.setPassword("0");
					}
					else{
						login_model.setStatus("SUCCESS");
						/*login_model.setMessage("First time login");*/
						login_model.setPassword("1");
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				if (rs != null) {

					rs.close();
				} else if (stmt != null) {

					stmt.close();

				} else if (con != null) {

					con.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return login_model;

	}


	
	@Override
	public Client leadcount(Client client) {
		List<Client> addressList = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		
	

		try {

			Db db = new Db();

			con = db.mysqlConnect();

			stmt = con.createStatement();
			
		String postal_area_address_query="SELECT lead_status FROM lead_details WHERE lead_status ='1'";
			
			 System.out.println("postal_address:" + postal_area_address_query);

			 rs = stmt.executeQuery(postal_area_address_query);

			 boolean last = rs.last();

			 int row = rs.getRow();
			
			 rs.beforeFirst();

			

			

				while (rs.next()) {

					Client address_model = new Client();
					
					address_model.setLead_status(rs.getString("lead_status"));
					addressList.add(address_model);

				}
				client.setTotalcount(String.valueOf(row));
				

			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				
				

				if (rs != null) {

					rs.close();
				} else if (stmt != null) {

					stmt.close();

				} else if (con != null) {

					con.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return client;
}
	
	@SuppressWarnings("resource")
	@Override
	public List<Client> list() {
		
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Client> list = new ArrayList<Client>();
		
		try {

			String sql = "SELECT lead_id,clientid,first_name,area_name,city,pincode,state,lead_created_date,lead_status FROM lead_details WHERE lead_status='1'";
			connection = Db.mysqlConnect();
			preparedStatement = connection.prepareStatement(sql);
			System.out.println("query-------------------->"+sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println("row---------->"+resultSet);
			int row = resultSet.getRow();
			System.out.println("row---------->"+row);
			while (resultSet.next()){
				Client outob = new Client();

				outob.setLead_id(resultSet.getString("lead_id"));
				outob.setClientid(resultSet.getString("clientid"));
				outob.setFirstName(resultSet.getString("first_name"));
				outob.setAreaName(resultSet.getString("area_name"));
				String city=resultSet.getString("city");
				System.out.println("city========="+city);
				String query_cityname = "SELECT city_name FROM master_cities WHERE city_id='"+city+"'";
				
				preparedStatement = connection.prepareStatement(query_cityname);
			
				ResultSet rs_statename = preparedStatement.executeQuery();				
				if (rs_statename.next()) {
					outob.setCityname(rs_statename.getString("city_name"));
				}
				
				rs_statename.close();
				outob.setPincode(resultSet.getString("pincode"));
				String state=resultSet.getString("state");
				String query_statename = "SELECT state_name FROM master_states WHERE state_id='"+state+"'";
				
				preparedStatement = connection.prepareStatement(query_statename);
			
				ResultSet rs_cityname = preparedStatement.executeQuery();				
				if (rs_cityname.next()) {
					outob.setStatename(rs_cityname.getString("state_name"));
				}
				
				rs_cityname.close();
				outob.setLead_created_date(resultSet.getDate("lead_created_date"));
				outob.setLead_status(resultSet.getString("lead_status"));
				outob.setTotalcount(String.valueOf(row));
				list.add(outob);
			}
                
		} catch (Exception e) {

			e.printStackTrace();

		} 
		finally{
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	}
	@SuppressWarnings("resource")
	@Override
	public List<Client> atelist() {
		// TODO Auto-generated method stub
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Client> atelist = new ArrayList<Client>();
		try {

			String sql = "SELECT lead_id,clientid,first_name,area_name,city,pincode,state,lead_created_date,lead_status FROM lead_details WHERE lead_status='2'";
			connection = Db.mysqlConnect();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				Client outob = new Client();

				outob.setLead_id(resultSet.getString("lead_id"));
				outob.setClientid(resultSet.getString("clientid"));
				outob.setFirstName(resultSet.getString("first_name"));
				outob.setAreaName(resultSet.getString("area_name"));
				String city=resultSet.getString("city");
				/*System.out.println("city=1========"+city);*/
				String query_cityname = "SELECT city_name FROM master_cities WHERE city_id='"+city+"'";
				
				preparedStatement = connection.prepareStatement(query_cityname);
			
				ResultSet rs_statename = preparedStatement.executeQuery();				
				if (rs_statename.next()) {
					outob.setCityname(rs_statename.getString("city_name"));
				}
				
				rs_statename.close();
				outob.setPincode(resultSet.getString("pincode"));
				
				String state=resultSet.getString("state");
				String query_statename = "SELECT state_name FROM master_states WHERE state_id='"+state+"'";
				
				preparedStatement = connection.prepareStatement(query_statename);
			
				ResultSet rs_cityname = preparedStatement.executeQuery();				
				if (rs_cityname.next()) {
					outob.setStatename(rs_cityname.getString("state_name"));
				}
				
				rs_cityname.close();
				outob.setLead_created_date(resultSet.getDate("lead_created_date"));			
				atelist.add(outob);

			}




		} catch (Exception e) {

			e.printStackTrace();

		} 
		finally{
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return atelist;

	}
	@Override
	public List<Client> complete() {
		// TODO Auto-generated method stub
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Client> complete = new ArrayList<Client>();
		try {

			String sql = "SELECT lead_id,clientid,first_name,area_name,city,pincode,state,lead_created_date,lead_status,lead_verified_date,lead_approved_date,lead_assigned_to,remarks,approved_status,billing_price FROM lead_details WHERE lead_status='3' OR lead_status='4'";
			connection = Db.mysqlConnect();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				Client outob = new Client();
				outob.setLead_id(resultSet.getString("lead_id"));
				outob.setClientid(resultSet.getString("clientid"));
				outob.setFirstName(resultSet.getString("first_name"));
				outob.setAreaName(resultSet.getString("area_name"));
				String city=resultSet.getString("city");
				System.out.println("city2========="+city);
				String query_cityname = "SELECT city_name FROM master_cities WHERE city_id='"+city+"'";
				
				preparedStatement = connection.prepareStatement(query_cityname);
			
				ResultSet rs_statename = preparedStatement.executeQuery();				
				if (rs_statename.next()) {
					outob.setCityname(rs_statename.getString("city_name"));
				}
				
				rs_statename.close();
				outob.setPincode(resultSet.getString("pincode"));
				outob.setState(resultSet.getString("state"));
				outob.setLead_created_date(resultSet.getDate("lead_created_date"));
			String lead=resultSet.getString("lead_status");
				if(lead.equals("3")){
					outob.setLeadst("Verified");
				}
				else if(lead.equals("4")){
					outob.setLeadst("Not Verified");
				}
				outob.setLead_verified_date(resultSet.getDate("lead_verified_date"));
				outob.setLead_approved_date(resultSet.getDate("lead_approved_date"));
				outob.setLead_assigned_to(resultSet.getString("lead_assigned_to"));
				int rmarks=resultSet.getInt("remarks");
				if (rmarks == 1) {
					outob.setRmarks("Customer not available/Address Correct");
				} else if (rmarks == 2) {
					outob.setRmarks("Customer not available/Address InCorrect");
				} else if (rmarks == 3) {
					outob.setRmarks("Customer available/Address InCorrect");
				} else if (rmarks == 4) {
					outob.setRmarks("Request Re-asign");
				}
				int app=resultSet.getInt("approved_status");
				if (app == 0) {
					outob.setApprove("Open");
				} else if (app == 1) {
					outob.setApprove("Approved");
				} else if (app == 2) {
					outob.setApprove("Rejected");
				}
				
				String bill=resultSet.getString("billing_price");
				if(bill.equals("0")){
					outob.setBillv("Mobile");
				}
				else if(bill.equals("1")){
					outob.setBillv("Web");
				}
				complete.add(outob);
			}	

		} catch (Exception e) {

			e.printStackTrace();

		} 
		finally{
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return complete;

	}
	
	@Override
	public Client update(Client client) {
		// TODO Auto-generated method stub
		Client outputObj = new Client();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	

		try {

			connection = Db.mysqlConnect();
			
			
			
			String del = "UPDATE postal_billing_update SET online_price=?,offline_price=?";
			preparedStatement = connection
					.prepareStatement(del);
			preparedStatement.setString(1, client.getOnline_price());
			preparedStatement.setString(2, client.getOffline_price());
			int deleteCount =  preparedStatement.executeUpdate();
			if(deleteCount >0)
			{
				outputObj.setStatus("success");
			}else
			{
				outputObj.setStatus("failure");
			}

		}

		catch (Exception e) {

			e.printStackTrace();

			outputObj.setStatusa(false);
		} 
		finally
		{
			try {
				
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return outputObj;

	}
	@Override
	public Client leadprocess(Client client) {
		// TODO Auto-generated method stub
		List<Client> addressList = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		
	

		try {

			Db db = new Db();

			con = db.mysqlConnect();

			stmt = con.createStatement();
			
		String postal_area_address_query="SELECT lead_status FROM lead_details WHERE lead_status ='2'";
			
			 System.out.println("postal_address:" + postal_area_address_query);

			 rs = stmt.executeQuery(postal_area_address_query);

			 boolean last = rs.last();

			 int row = rs.getRow();
			
			 rs.beforeFirst();

			

			

				while (rs.next()) {

					Client address_model = new Client();
					
					address_model.setLead_status(rs.getString("lead_status"));
					addressList.add(address_model);

				}
				client.setCount(String.valueOf(row));
				

			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				
				

				if (rs != null) {

					rs.close();
				} else if (stmt != null) {

					stmt.close();

				} else if (con != null) {

					con.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return client;
	}
	@Override
	public Client leadverify(Client client) {
		// TODO Auto-generated method stub
		List<Client> addressList = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		
	

		try {

			Db db = new Db();

			con = db.mysqlConnect();

			stmt = con.createStatement();
			
		String postal_area_address_query="SELECT lead_status FROM lead_details WHERE lead_status ='3'";
			
			 System.out.println("postal_address:" + postal_area_address_query);

			 rs = stmt.executeQuery(postal_area_address_query);

			 boolean last = rs.last();

			 int row = rs.getRow();
			
			 rs.beforeFirst();

			

			

				while (rs.next()) {

					Client address_model = new Client();
					
					address_model.setLead_status(rs.getString("lead_status"));
					addressList.add(address_model);

				}
				client.setTotal(String.valueOf(row));;
				

			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				
				

				if (rs != null) {

					rs.close();
				} else if (stmt != null) {

					stmt.close();

				} else if (con != null) {

					con.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return client;
	}
	@Override
	public Client leadnotverify(Client client) {
		// TODO Auto-generated method stub
		List<Client> addressList = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		
	

		try {

			Db db = new Db();

			con = db.mysqlConnect();

			stmt = con.createStatement();
			
		String postal_area_address_query="SELECT lead_status FROM lead_details WHERE lead_status ='4'";
			
			 System.out.println("postal_address:" + postal_area_address_query);

			 rs = stmt.executeQuery(postal_area_address_query);

			 boolean last = rs.last();

			 int row = rs.getRow();
			
			 rs.beforeFirst();

			

			

				while (rs.next()) {

					Client address_model = new Client();
					
					address_model.setLead_status(rs.getString("lead_status"));
					addressList.add(address_model);

				}
				client.setCount2(String.valueOf(row));;
				

			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				
				

				if (rs != null) {

					rs.close();
				} else if (stmt != null) {

					stmt.close();

				} else if (con != null) {

					con.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return client;
	}
	@Override
	public Client bill(Client client) {
		// TODO Auto-generated method stub
		

		Connection con = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		
	

		try {

			Db db = new Db();

			con = db.mysqlConnect();

			stmt = con.createStatement();
			
		String postal_area_address_query="SELECT online_price,offline_price FROM postal_billing_update";
			
			 System.out.println("postal_address:" + postal_area_address_query);

			 rs = stmt.executeQuery(postal_area_address_query);

			 boolean last = rs.last();

			 int row = rs.getRow();
			
			 rs.beforeFirst();

			

			

				while (rs.next()) {

					
					
					
					client.setOnline_price(rs.getString("online_price"));
					client.setOffline_price(rs.getString("offline_price"));
					

				}
				

			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				
				

				if (rs != null) {

					rs.close();
				} else if (stmt != null) {

					stmt.close();

				} else if (con != null) {

					con.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		return client;
	}

	@Override
	public Client getusername(String clientname) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Client client = new Client();
		try {
			
			String us="SELECT clientname FROM client_login_details";
			connection = Db.mysqlConnect();
			
			preparedStatement = connection.prepareStatement(us);
		

			 resultSet = preparedStatement.executeQuery();				
			if (resultSet.next()) {

				client.setClientname(resultSet.getString("clientname"));
			}
			
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return client;
	}

	

	

}
