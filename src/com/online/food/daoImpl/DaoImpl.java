package com.online.food.daoImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.online.food.bo.LoginBo;
import com.online.food.bo.OrdersBo;
import com.online.food.bo.RegistrationBo;
import com.online.food.daoI.DaoInterface;
import com.online.food.util.DBConnection;

public class DaoImpl implements DaoInterface{
	Connection con=null;
	PreparedStatement pst=null;
	Statement st = null;
	ResultSet rs= null;
	String verifyflag = null;
	int flag=0;
	int cityId = 0;
	int loginId = 0;
	int regId = 0;
	int stateId = 0;
	int countryId = 0;
	int addrId = 0;
	int role = 2;
	public int insert(RegistrationBo regBo){
	System.out.println("3");
	try {
		System.out.println("4");
		con=DBConnection.getDBConnection();
		
			/* String q5 = "insert into mydb.tab_country values(?)"; */
			String q1 = "select countryid from mydb.tab_country where country=?";
			String q2 = "select cityid from mydb.tab_city where city=?";
			String q4 = "select * from mydb.tab_address";
			System.out.println("5");
			if(con!=null){
				
				pst = con.prepareStatement(q1);
				pst.setString(1, regBo.getCountry());
				rs=pst.executeQuery();
				while(rs.next())
				{
					countryId = rs.getInt(1);
				}
				pst = con.prepareStatement("insert into mydb.tab_state (state,tab_country_countryid) values (?,(select countryid from mydb.tab_country where country=?))");
				pst.setString(1,regBo.getState());
				pst.setString(2,regBo.getCountry());
				int c1=pst.executeUpdate();
				pst = con.prepareStatement("insert into mydb.tab_city(city,tab_state_stateid)values(?,(select stateid from mydb.tab_state where state=?))");
				pst.setString(1,regBo.getCity());
				pst.setString(2,regBo.getState());
				int c2=pst.executeUpdate();
				pst = con.prepareStatement(q2);
				pst.setString(1, regBo.getCity());
				rs=pst.executeQuery();
				while(rs.next())
				{
					cityId = rs.getInt(1);
				}
				
				pst = con.prepareStatement("insert into mydb.tab_login(email,password,tab_role_role_id)values(?,?,?)");
				
				pst.setString(1,regBo.getEmail());
				pst.setString(2,regBo.getPass());
				pst.setInt(3, role);
				int c3=pst.executeUpdate();
				
				pst = con.prepareStatement("select loginid from mydb.tab_login where email=?");
				pst.setString(1, regBo.getEmail());
				rs=pst.executeQuery();
				while(rs.next()) {
					loginId = rs.getInt(1);
				}
				pst=con.prepareStatement("insert into mydb.tab_address(flatno,appartment,landmark,pincode,tab_city_cityid) values (?,?,?,?,(select cityid from mydb.tab_city where city=?))");
				pst.setString(1, regBo.getFlatNum());
				pst.setString(2,regBo.getApartmentNum());
				pst.setString(3, regBo.getLandmark()); 
				pst.setInt(4, regBo.getPincode());
				pst.setString(5, regBo.getCity());
				int c4 = pst.executeUpdate();
				
				pst = con.prepareStatement(q4);
				rs = pst.executeQuery();
				while(rs.next()) {
					addrId = rs.getInt(1);
				}
				
				pst = con.prepareStatement("insert into mydb.tab_register(fname,lname,dob,mobileno,tab_login_loginid,tab_address_addressid) values(?,?,?,?,?,?) ");
				pst.setString(1,regBo.getfName());
				pst.setString(2,regBo.getlName());
				pst.setString(3, regBo.getDob()); 
				pst.setString(4, regBo.getMno());
				pst.setInt(5, loginId);
				pst.setInt(6, addrId);
				int c5= pst.executeUpdate();
				if(c1==1&&c2==1&&c3==1&&c4==1&&c5==1)
					flag=1;
				
			}
			else {
				System.out.println("failed");
			}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
		
	
	return flag;
}


	@Override
	public String verify(LoginBo logBo) {
		String email = logBo.getEmail();
		String password = logBo.getPassword();
		String emailDB = null;
		String passwordDB = null;
		int roleIdDB = 0;
		try {
			
			System.out.println("4");
			con=DBConnection.getDBConnection();
			st = con.createStatement();
			if(con!=null){
				System.out.println("5");
				
				/*
				 * pst.setString(1, logBo.getEmail()); pst.setString(2, logBo.getPassword());
				 */
				rs = st.executeQuery("select email,password,tab_role_role_id from mydb.tab_login");
				System.out.println("5");
				while(rs.next())
				{
					System.out.println("6");
					emailDB = rs.getString("email");
					passwordDB = rs.getString("password");
					roleIdDB = rs.getInt("tab_role_role_id");
					if(email.equals(emailDB)&&password.equals(passwordDB)&& roleIdDB == 1) {
						verifyflag = "admin role";
						return verifyflag;
					}
					else if(email.equals(emailDB)&&password.equals(passwordDB)&& roleIdDB == 2) {
						verifyflag = "user role";
						return verifyflag;
					}
					
				}
				
			}
			else {
				System.out.println("failed");
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		return "invalid user credentials";
	}


	@Override
	public int orderInsert(OrdersBo bo) {
		int flag=0;
		int loginId=0;
		int regId=0;
		int itemid=0;
		try {
			con=DBConnection.getDBConnection();
			if(con!=null) {
			//loginid
			pst=con.prepareStatement("select loginid from mydb.tab_login where email=?");
			pst.setString(1, bo.getEmail());
			rs=pst.executeQuery();
			System.out.println("1");
			while(rs.next()) {
	           loginId=rs.getInt(1);
	           System.out.println("1");
			}
				
			//regid
			pst=con.prepareStatement("select registerid from mydb.tab_register where tab_login_loginid=?");
			pst.setInt(1, loginId);
			rs=pst.executeQuery();
			System.out.println("1");
			while(rs.next()) {
	            regId=rs.getInt(1);
	            System.out.println("1");
			}
			
			//itemid
			pst=con.prepareStatement("select itemid from mydb.tab_items where itemname=?");
			pst.setString(1, bo.getName());
			rs=pst.executeQuery();
			System.out.println("1");
			while(rs.next()) {
	            itemid=rs.getInt(1);
	            System.out.println("1");
			}
			
			//insert into order	
			pst=con.prepareStatement("insert into mydb.tab_orders values(?,?,?)");
			pst.setInt(1, regId);
			pst.setInt(2,itemid);
			pst.setInt(3,bo.getQuantity());
			flag=pst.executeUpdate();
			}
			else {
				System.out.println("failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	
	}


	
	
}
