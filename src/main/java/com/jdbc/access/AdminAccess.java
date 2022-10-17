package com.jdbc.access;

import java.util.Scanner;

import com.jdbc.bank.Bank;
import com.jdbc.bank.User;
import com.jdbc.dao.BankDao;

public class AdminAccess {
		public void adminAccess(BankDao dao)
		{
			Scanner sc=new Scanner(System.in);
			
			System.out.println("Entered into Bank Access...........................");
			while(true)
			{
				System.out.println(String.format("%-30s %-30s \n%-30s %-30s \n%-30s %-30s\n%-30s %-30s\n%-30s", 
						"(1)->View all Bank","(2)->View all User","(3)->Add new bank","(4)->Update bank location",
						"(5)->Update User Location","(6)->Add new User","(7)->Get user details by Id","(8)->Get bank details by Id",
						"(9)->Get users by bank id"));
				int i=Integer.parseInt(sc.nextLine());
				if(i==1)
				{
					dao.getAllBank();
				}
				else if(i==2)
				{
					dao.getAllUser();
				}
				else if(i==3)
				{
					System.out.println("Enter Bid, Name, City, Ifsc-Code");
					int bid=Integer.parseInt(sc.nextLine());
					String name=sc.nextLine();
					String city=sc.nextLine();
					String ifsc=sc.nextLine();
					Bank bank=new Bank(bid,name,city,ifsc);
					dao.insertBank(bank);
				}
				else if(i==4)
				{
					System.out.println("Enter the bank Id and city to update");
					int bid=Integer.parseInt(sc.nextLine());
					String city=sc.nextLine();
					dao.updateBankCity(bid, city);
				}
				else if(i==5)
				{
					System.out.println("Enter the Accno and city to update");
					int accno=Integer.parseInt(sc.nextLine());
					String city=sc.nextLine();
					dao.updateUserLocation(accno, city);
				}
				else if(i==6)
				{
					System.out.println("Enter Accno,Name,Location,Balance,Mobile,Bankid");
					int accno=Integer.parseInt(sc.nextLine());
					String name=sc.nextLine();
					String location=sc.nextLine();
					double balance=Double.parseDouble(sc.nextLine());
					String mobile=sc.nextLine();
					int bankid=Integer.parseInt(sc.nextLine());
					User user=new User(accno,name,location,balance,mobile,bankid);
					dao.insertUser(user);
				}
				else if(i==7)
				{
					System.out.println("Enter the Accno to get");
					int accno=Integer.parseInt(sc.nextLine());
					User us=dao.getUserByAcc(accno);
					
					System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s %-15s", 
							"AccountNo.","UserName","Location","Balance","Mobile No","Bank-Id"));
					System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s %-15s\n", us.getAccno(),
							us.getUname(),us.getLocation(),us.getBalance(),us.getMobile(),us.getBankid()));
					
				}
				else if(i==8)
				{
					System.out.println("Enter the bank Id to view ");
					int bid=Integer.parseInt(sc.nextLine());
					dao.getBankByID(bid);
				}
				else if(i==9)
				{
					System.out.println("Enter the bank Id to view account holders");
					int bid=Integer.parseInt(sc.nextLine());
					Bank bank=dao.getfullBank(bid);
					System.out.println("Bank details=>");
					System.out.println(bank.toString());
					System.out.println("\nAccount Holders ==============>");
					System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s %-15s", 
							"AccountNo.","UserName","Location","Balance","Mobile No","Bank-Id"));
					bank.getUser().forEach(u->System.out.println(u.toString()));
					System.out.println();
				}
				else
				{
					System.out.println("Exting Admin .........................");
					break;
				}
			}
		}
}
