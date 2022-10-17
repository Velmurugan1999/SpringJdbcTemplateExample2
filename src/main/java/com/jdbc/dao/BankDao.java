package com.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.jdbc.bank.Bank;
import com.jdbc.bank.User;

public class BankDao {
	@Autowired
	NamedParameterJdbcTemplate template;

	public NamedParameterJdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	public void getAllBank() {
		String sql = "select * from banks ";
		List<Bank> list = template.query(sql, new RowMapper<Bank>() {

			public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
				Bank bank = new Bank();

				bank.setBid(rs.getInt(1));
				bank.setBname(rs.getString(2));
				bank.setCity(rs.getString(3));
				bank.setIfsc(rs.getString(4));
				return bank;
			}
		});
		System.out.println(String.format("%-5s %-15s %-15s %-15s","B-ID", "NAME", "CITY","IFSC-CODE"));
		list.forEach(t -> System.out
				.println(String.format("%-5s %-15s %-15s %-15s", t.getBid(), t.getBname(), t.getCity(), t.getIfsc())));
		System.out.println();
	}
	
	public void getAllUser() {
		String sql="select * from bank_user";
		List<User> list=template.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u=new User();
				u.setAccno(rs.getInt(1));
				u.setUname(rs.getString(2));
				u.setLocation(rs.getString(3));
				u.setBalance(rs.getDouble(4));
				u.setMobile(rs.getString(5));
				u.setBankid(rs.getInt(6));
				//System.out.println(u);
				return u;
			}
		});
		System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s %-15s", 
				"AccountNo.","UserName","Location","Balance","Mobile No","Bank-Id"));
		list.forEach(thi->System.out.println(String.format("%-5s %-15s %-15s %-15s %-15s %-15s", thi.getAccno(),
				thi.getUname(),thi.getLocation(),thi.getBalance(),thi.getMobile(),thi.getBankid())));
		System.out.println();
	}
	public void getBankByID(int id) {
		String sql="select * from banks where bid=:id";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		List<Bank> list = template.query(sql,map, new RowMapper<Bank>() {

			public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
				Bank bank = new Bank();

				bank.setBid(rs.getInt(1));
				bank.setBname(rs.getString(2));
				bank.setCity(rs.getString(3));
				bank.setIfsc(rs.getString(4));
				return bank;
			}
		});
		System.out.println(String.format("%-5s %-15s %-15s %-15s","B-ID", "NAME", "CITY","IFSC-CODE"));
		list.forEach(t -> System.out
				.println(String.format("%-5s %-15s %-15s %-15s", t.getBid(), t.getBname(), t.getCity(), t.getIfsc())));
		System.out.println();
	}
	public User getUserByAcc(int accno) {
		String sql="select * from bank_user where accno=:accno";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("accno", accno);
		List<User> list=template.query(sql,map, new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u=new User();
				u.setAccno(rs.getInt(1));
				u.setUname(rs.getString(2));
				u.setLocation(rs.getString(3));
				u.setBalance(rs.getDouble(4));
				u.setMobile(rs.getString(5));
				u.setBankid(rs.getInt(6));
				return u;
			}
		});
		
		return list.get(0);
	}
	public void insertBank(Bank bank) {
		String sql="insert into banks values(:id,:name,:city,:ifsc)";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", bank.getBid());
		map.put("name", bank.getBname());
		map.put("city", bank.getCity());
		map.put("ifsc", bank.getIfsc());
		int i=template.update(sql, map);
		if(i==1)
			System.out.println("Bank Inserted successfully........");
		else
			System.out.println("Unable to insert!!!!!!!!!!!");
	}
	
	public void insertUser(User u) {
		String sql="insert into bank_user values(:accno,:name,:location,:balance,:mobile,:bankid)";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("accno", u.getAccno());
		map.put("name", u.getUname());
		map.put("location", u.getLocation());
		map.put("balance", u.getBalance());
		map.put("mobile", u.getMobile());
		map.put("bankid", u.getBankid());
		int i=template.update(sql, map);
		if(i==1)
			System.out.println("User Inserted successfully........");
		else
			System.out.println("Unable to insert!!!!!!!!!!!");
	}
	public void updateBankCity(int id,String city)
	{
		String sql="update banks set city=:city where bid=:id";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",id);
		map.put("city", city);
		int i=template.update(sql, map);
		if(i==1)
			System.out.println("Bank Details Updated  successfully........");
		else
			System.out.println("Unable to update!!!!!!!!!!!");
	}
	
	public void updateUserBalance(int accno,double balance)
	{
		String sql="update bank_user set balance=:balance where accno=:accno";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("balance",balance);
		map.put("accno", accno);
		int i=template.update(sql, map);
		if(i==1)
			System.out.println("User details Updated  successfully........");
		else
			System.out.println("Unable to update!!!!!!!!!!!");
	}
	public void updateUserLocation(int accno,String location)
	{
		String sql="update bank_user set location=:location where accno=:accno";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("location",location);
		map.put("accno", accno);
		int i=template.update(sql, map);
		if(i==1)
			System.out.println("Student details Updated  successfully........");
		else
			System.out.println("Unable to update!!!!!!!!!!!");
	}
	public void deleteUser(int accno)
	{
		String sql="delete from bank_user where accno="+accno+"";
		Map<String,Object> map=new HashMap<String, Object>();
		int i=template.update(sql, map);
		if(i==1)
			System.out.println("Student details Updated  successfully........");
		else
			System.out.println("Unable to update!!!!!!!!!!!");
	}
	public Bank getfullBank(int id)
	{
		String sql="select * from banks where bid=:id";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		
		List<Bank> bank = template.query(sql,map, new RowMapper<Bank>() {

			public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
				Bank bank = new Bank();

				bank.setBid(rs.getInt(1));
				bank.setBname(rs.getString(2));
				bank.setCity(rs.getString(3));
				bank.setIfsc(rs.getString(4));
				return bank;
			}
		});
		String sql1="select * from bank_user where bankid=:id";
		Map<String,Object> map1=new HashMap<String, Object>();
		map1.put("id", id);
		List<User> list=template.query(sql1,map1, new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u=new User();
				u.setAccno(rs.getInt(1));
				u.setUname(rs.getString(2));
				u.setLocation(rs.getString(3));
				u.setBalance(rs.getDouble(4));
				u.setMobile(rs.getString(5));
				u.setBankid(rs.getInt(6));
				return u;
			}
		});
		bank.get(0).setUser(list);
		
		return bank.get(0);
	}
}
