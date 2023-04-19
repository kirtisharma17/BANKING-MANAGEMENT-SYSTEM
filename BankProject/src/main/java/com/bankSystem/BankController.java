package com.bankSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	
	@PostMapping("/Account_validation/")
	public String account(int accno,String password) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con   =   DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root","Kirti@123");
		Statement stmt= con.createStatement();
		String query = "select password from Account where accno = '"+accno+"'";
	     ResultSet rs = stmt.executeQuery(query);
	     if(rs.next()) {
	    	 String pwd= rs.getString("password");
	    	 
	    	 
	    	 if(pwd.equals(password)) {
	    		 return ("you are valid user");
	    	 }else {
	    		 return "password is wrong";
	    	 }
	     }else {
	    		 return "you do not have Account....opened new Account";
	    	 }
	     
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "this is login api";
	
	}
	
	@PostMapping("/Account/login")
	public String login(String accno,String password) {
		 int accno1= Integer.parseInt(accno);
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con   =   DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root","Kirti@123");
		
		String query = "select * from Account where accno=( "+accno1+") and password=( '"+password+"')";
		PreparedStatement stmt= con.prepareStatement(query);
		//stmt.setInt(1, Integer.parseInt(accno));
		//stmt.setString(2, password);
	     ResultSet rs = stmt.executeQuery(query);
	     
	     
	     if(rs.next()) {
	    	 
	    	 
			return "accno="+rs.getInt("accno")+"  name="+rs.getString("name")+"   city="+rs.getString("city")+"   balance="+rs.getInt("balance");
	    	 
	   }else
		   return "Check your password and accno";
	    
	  
	     
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "open new account...!!";
	
	}
	
	
	@PostMapping("create/account")
	public String loginpost(String name, String password, String city) {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root","Kirti@123");
			 Statement stmt = con.createStatement();
			 Random random=new Random(10000);
				int accno =random.nextInt(9999);
				System.out.println("accno: "+accno);
		     String query = "insert into Account (name,password,city,accno)values(' "+name+"', '"+password+"', '"+city+"', '"+accno+"')";
		     int i =  stmt.executeUpdate(query);
		      System.out.println("Numbers of rows inserted"+i);
			
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return "your account is opened";
	}
	
	@GetMapping("/forgot/password")
	public String forgot(String accno) {
		String newUrl="";
		int accno1=Integer.parseInt(accno);
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con   =   DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root","Kirti@123");
		Statement stmt= con.createStatement();
		String query = "select * from Account where accno = "+accno1+" ";
	     ResultSet rs = stmt.executeQuery(query);
	     if(rs.next()) {
	    	 newUrl=createNewUrl(6);
	    	
	    	System.out.println("password: "+newUrl);
	    	String query2="update Account set password=? where accno=( "+accno1+")";
	    	 PreparedStatement st = con.prepareStatement(query2);
	    	  st.setString(1, newUrl);
	    	  int i= st.executeUpdate();
	    	  System.out.println("no of statement:"+i);
	    	  if(i>0)
	    		  return "password generated Successfully..!!"+newUrl;
	     }
	     else
	    	 return "invalid accno no";
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Don't have Account";
		
	}

	public String createNewUrl(int targetStringLength) {
				
				int leftLimit = 48; // letter '0'
				    int rightLimit = 122; // letter 'z'
				    
	  Random random = new Random();
      StringBuilder buffer = new StringBuilder(targetStringLength);
	     for (int i = 0; i < targetStringLength; i++) {
				        int randomLimitedInt = leftLimit + (int) 
				          (random.nextFloat() * (rightLimit - leftLimit + 1));
				       buffer.append((char)randomLimitedInt);
				    }
				    String generatedString = buffer.toString();

				     return generatedString;
			   }
	
	
	@GetMapping("/fetch/ Accounts /Details")
	public Map States() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con   =   DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root","Kirti@123");
		PreparedStatement stmt= con.prepareStatement("select * from Account");
		
	     ResultSet rs = stmt.executeQuery();
	         List l= new ArrayList();
	     while(rs.next()) {
	    	 Map map = new HashMap();
	    	 map.put("accno", rs.getInt("accno"));  // key name And coloum name that value i fetch
	    	 map.put("name", rs.getString("name"));
	    	 map.put("city", rs.getString("city"));
	    	 map.put("balance", rs.getInt("balance"));
	    	 l.add(map);
	     }
	     Map data = new HashMap();
	     data.put("Account Details", l); // states key  And List ki all values l is here list reference variable
	     data.put("tt1", 24);
	     return data;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
		
	
	
	@PostMapping("/withdraw money")
	public String withdraw(String accno, String password, String money) {
		
		int accno1=Integer.parseInt(accno);
		int money1=Integer.parseInt(money);
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con   =   DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root","Kirti@123");
		Statement stmt= con.createStatement();
		String query = "select * from Account where accno =( "+accno1+" ) and password=('"+password+"' )";
	     ResultSet rs = stmt.executeQuery(query);
	     if(rs.next()) {
	    	
	    	
	    	
	    	String query2="update Account set balance=? where accno=( "+accno1+")";
	    	 PreparedStatement st = con.prepareStatement(query2);
	    	  st.setInt(1, rs.getInt("balance")-money1);
	    	  int i= st.executeUpdate();
	    	  System.out.println("no of statement:"+i);
	    	  if(i>0)
	    		  return "withdraw money Successfully..!!"+money1;
	     }
	     else
	    	 return "invalid accno no";
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Don't have Account";
	}
	
	
	@PostMapping("/Deposit money")
	public String Deposit(String accno, String password, String money) {
		
		int accno1=Integer.parseInt(accno);
		int money1=Integer.parseInt(money);
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con   =   DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root","Kirti@123");
		Statement stmt= con.createStatement();
		String query = "select * from Account where accno =( "+accno1+" ) and password=('"+password+"' )";
	     ResultSet rs = stmt.executeQuery(query);
	     if(rs.next()) {
	    	
	    	
	    	
	    	String query2="update Account set balance=? where accno=( "+accno1+")";
	    	 PreparedStatement st = con.prepareStatement(query2);
	    	  st.setInt(1, rs.getInt("balance")+money1);
	    	  int i= st.executeUpdate();
	    	  System.out.println("no of statement:"+i);
	    	  if(i>0)
	    		  return "Deposit money Successfully..!!"+money1+"your new balance="+(  rs.getInt("balance")+money1);
	     }
	     else
	    	 return "invalid accno no";
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Don't have Account";
	}
	
	
	@PostMapping("otp/generate")
	public String otp(String accno, String password) {
		int accno1=Integer.parseInt(accno);
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root","Kirti@123");
			 Statement stmt = con.createStatement();
			 Random random=new Random(10000);
				int otp =random.nextInt(9999);
				System.out.println("otp: "+otp);
		     String query = "insert into otp_gen (otp, accno)values( "+otp+", "+accno1+")";
		     int i =  stmt.executeUpdate(query);
		      System.out.println("Numbers of rows inserted"+i);
		      return "otp generated successfully!!"+otp;
		      
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return "invalid user name and password!!!";
	}
	
}
		
	
	

