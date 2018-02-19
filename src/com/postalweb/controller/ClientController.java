package com.postalweb.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.postalweb.config.Db;
import com.postalweb.model.Client;
import com.postalweb.model.Error;
import com.postalweb.service.ClientService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	

	@RequestMapping(value = "/addlead", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Client> loglast(@RequestParam("file") MultipartFile file,HttpServletRequest request,
				HttpServletResponse response,
				javax.servlet.http.HttpSession session) {
			
		ArrayList<Hashtable<String, String>> al = new ArrayList<Hashtable<String, String>>();

		Map<String, String> mp = new HashMap<String, String>();

		try {
			
			HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
			HSSFSheet sh = (HSSFSheet) workbook.getSheet("Sheet1");
			Iterator<HSSFRow> rowitreator = sh.rowIterator();
			int rowcount = 0;

			while (rowitreator.hasNext()) {

				String lead_id = "";

				String clientid = "";

		

				String first_name = "";

				String last_name = "";

				String gender = "";

				String age = "";

				String door_no = "";

				String street_name = "";

				String area_name = "";

				String taluk = "";

				String city = "";

				String pincode = "";

				String state = "";

				String lead_created_by = "";

				String lead_created_date = "";

				String lead_verified_date = "";


				HSSFRow row = rowitreator.next();

				Iterator<HSSFCell> cellIterator = row.cellIterator();

				int colcount = 0;

				Hashtable<String, String> ht = new Hashtable<String, String>();

				while (cellIterator.hasNext()) {

					HSSFCell cell = cellIterator.next();
					String value = "";
					switch (cell.getCellType()) {

					case HSSFCell.CELL_TYPE_NUMERIC:

						String val = "";

						if ((int) cell.getNumericCellValue() == 0) {

							val = "0";

						} else {

							val = new java.text.DecimalFormat("0").format(cell
									.getNumericCellValue());
						}
						String column = mp.get(colcount + "");
						 if (column.trim().equals("age")) {

							age = val + "";
						} else if (column.trim().equals("door_no")) {

							door_no = val + "";
						}  else if (column.trim().equals("pincode")) {

							pincode = val + "";
						} else if (column.trim().equals("lead_created_by")) {

							lead_created_by = val + "";
						} 
						else if (column.trim().equals("city")) {
							
							city = val + "";
						}

						else if (column.trim().equals("state")) {
						
							state = val + "";
						}
						else if (column.trim().equals("clientid")) {
							clientid = val + "";
						}
						else if (column.trim().equals("gender")) {
							gender = val + "";
						}

						break;

					case HSSFCell.CELL_TYPE_STRING:

						if (cell.getStringCellValue().toString() == null
								|| cell.getStringCellValue().toString() == ""
								|| cell.getStringCellValue().toString().trim()
										.length() == 0) {

							value = "NA";

						} else {

							value = cell.getStringCellValue().toString();

						}

						if (rowcount != 0) {

							column = mp.get(colcount + "");

							if (column.trim().equals("lead_id")) {
								lead_id = value;
							}

							else if (column.trim().equals("clientid")) {
								clientid = value;
							}
							else if (column.trim().equals("last_name")) {

								last_name = value;
							} 
							
						else if (column.trim().equals("first_name")) {
								first_name = value;
							}


						else if (column.trim().equals("street_name")) {
								street_name = value;
							}
						else if (column.trim().equals("area_name")) {

							area_name = value;
						} else if (column.trim().equals("taluk")) {

							taluk = value;
						}
						else if (column.trim().equals("lead_created_date")) {

							lead_created_date = value;
						}
						else if (column.trim().equals("lead_verified_date")) {
							lead_verified_date = value;
						}
						
						}

						break;

					}

					if (rowcount == 0) {

						mp.put(colcount + "", cell.getStringCellValue()
								.toString().trim());

					}

					colcount += 1;

				}

				if (rowcount != 0) {

					ht.put("lead_id", lead_id );

					ht.put("clientid", clientid);

					

					ht.put("first_name", first_name);

					ht.put("last_name", last_name);

					ht.put("gender", gender);

					ht.put("age", age);

					ht.put("door_no", door_no);

					ht.put("street_name", street_name);

					ht.put("area_name", area_name);

					ht.put("taluk", taluk);

					ht.put("city", city);

					ht.put("pincode", pincode);

					ht.put("state", state);

					ht.put("lead_created_by", lead_created_by);

					ht.put("lead_created_date", lead_created_date);

					ht.put("lead_verified_date", lead_verified_date);

					
					al.add(ht);

				}

				rowcount = rowcount + 1;

				

			}
			}
			catch (Exception e){
				System.out.println("erooorrrr=="+e);
				
			}
		    
		try {
			Connection connection = Db.mysqlConnect();

			
			int i =0;			

			for(int it=0;it<(al.size());it++){
			
				Hashtable<String, String> h=(Hashtable<String, String>)al.get(it);
			 
			
				String lead_id=h.get("lead_id");
		   
				String clientid=h.get("clientid");
		    
				String first_name=h.get("first_name");
			   
				String last_name=h.get("last_name");
		   
				String gender=h.get("gender");
				
				String age=h.get("age");
		    
				String door_no=h.get("door_no");
		    
				String street_name=h.get("street_name");
		   
				String area_name=h.get("area_name");
				
				String taluk=h.get("taluk");
		    
				String city=h.get("city");	
		   
				String pincode=h.get("pincode");
		   
				String state=h.get("state");
		    
				String lead_created_by=h.get("lead_created_by");
		    
				String lead_created_date =h.get("lead_created_date");
		    
				String lead_verified_date =h.get("lead_verified_date");
		    
			System.out.println("leadid===="+lead_id);
			System.out.println("clientid===="+clientid);
			System.out.println("first_name===="+first_name);
			System.out.println("last_name===="+last_name);
			System.out.println("gender===="+gender);
			System.out.println("age===="+age);
			System.out.println("door_no===="+door_no);
			System.out.println("street_name===="+street_name);
			System.out.println("area_name===="+area_name);
			System.out.println("taluk===="+taluk);
			System.out.println("city===="+city);
			System.out.println("street_name===="+street_name);
			System.out.println("lead_created_date===="+lead_created_date);
			System.out.println("lead_verified_date===="+lead_verified_date);
			
      String query1="insert into lead_details(lead_id,clientid,first_name,last_name,gender,age,door_no,street_name,area_name,taluk,city,pincode,state,lead_created_by,lead_created_date,lead_verified_date)values('"+lead_id+"','"+clientid+"','"+first_name+"','"+last_name+"','"+gender+"','"+age+"','"+door_no+"','"+street_name+"','"+area_name+"','"+taluk+"','"+city+"','"+pincode+"','"+state+"','"+lead_created_by+"',NOW(),'"+lead_verified_date+"')";

			System.out.println("query====="+query1);

				PreparedStatement ptmt=connection.prepareStatement(query1);
			

				int k=ptmt.executeUpdate();
				if(k>0)
				{
					i++;
				}

				ptmt.close();
				
			}
			
	
System.out.println("count========="+i);
		connection.close();

		}catch(Exception e){
			
			System.out.println(e);
			System.out.println("final========================="+e);
			
		}
			return new ResponseEntity<Client>(HttpStatus.OK);
		}
	
   
			@RequestMapping(value = "/logout", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logout(HttpSession session){
		String clientname=(String)session.getAttribute("clientname");
		System.out.println("Name of the user is"+ clientname);
		if(clientname==null){
			Error error=new Error(1,"Unauthorized access..please login..");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		session.removeAttribute("clientname");
		return new ResponseEntity<Client>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody Client client,HttpSession session){
		Client validUser=clientService.login(client);
		if(validUser==null){
			Error error=new Error(2," Invalid Username/Password...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
	
		session.setAttribute("clientname", validUser.getClientname());
		return new ResponseEntity<Client>(validUser,HttpStatus.OK);
		
	}
	@RequestMapping(value="/getlist",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getlist( HttpSession session){
		String clientname=(String)session.getAttribute("clientname");
		 if(clientname==null){
			Error error=new Error(3,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		 List<Client> list=clientService.list();
		 return new ResponseEntity<List<Client>>(list,HttpStatus.OK);
}
	@RequestMapping(value="/getatelist",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getatelist( HttpSession session){
		String clientname=(String)session.getAttribute("clientname");
		 if(clientname==null){
			Error error=new Error(4,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		 List<Client> atelist=clientService.atelist();
		 return new ResponseEntity<List<Client>>(atelist,HttpStatus.OK);
}
	@RequestMapping(value="/complete",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> complete( HttpSession session){
		String clientname=(String)session.getAttribute("clientname");
		 if(clientname==null){
			Error error=new Error(5,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		 List<Client> complete=clientService.complete();
		 return new ResponseEntity<List<Client>>(complete,HttpStatus.OK);
}
	@RequestMapping(value= "/count",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> newlead(Client client,HttpSession session) {
		String clientname=(String)session.getAttribute("clientname");
		 if(clientname==null){
			Error error=new Error(5,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
			Client yet=clientService.leadcount(client);
			return new ResponseEntity<Client>(yet,HttpStatus.OK);
		}
	@RequestMapping(value="/update",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Client client,HttpSession session){
	
		clientService.update(client);
		return new ResponseEntity<Client>(client,HttpStatus.OK);
		}
	@RequestMapping(value= "/process",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> leadprocess(Client client,HttpSession session) {
			Client yet=clientService.leadprocess(client);
			return new ResponseEntity<Client>(yet,HttpStatus.OK);
		}
	@RequestMapping(value= "/notverify",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> leadnotverify(Client client,HttpSession session) {
			Client yet=clientService.leadnotverify(client);
			return new ResponseEntity<Client>(yet,HttpStatus.OK);
		}
	@RequestMapping(value= "/verify",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> leadverify(Client client,HttpSession session) {
			Client yet=clientService.leadverify(client);
			return new ResponseEntity<Client>(yet,HttpStatus.OK);
		}
	@RequestMapping(value= "/bill",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> bill(Client client,HttpSession session) {
		String clientname=(String)session.getAttribute("clientname");
		 if(clientname==null){
			Error error=new Error(5,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
			Client yet=clientService.bill(client);
			return new ResponseEntity<Client>(yet,HttpStatus.OK);
		}
	/*@RequestMapping(value= "/username",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> user (HttpSession session) {
			List<Client> user=clientService.user();
			return new ResponseEntity<List<Client>>(user,HttpStatus.OK);
		}*/
}
