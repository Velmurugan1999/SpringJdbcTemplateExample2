package com.jdbc.access;

import java.util.Scanner;

import com.jdbc.bank.User;
import com.jdbc.dao.BankDao;

public class UserAccess {
	public void userAccess(BankDao dao)
	{
		System.out.println("Entered into User access>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Scanner sc=new Scanner(System.in);
		boolean b=true;
		System.out.println("Enter your Accno and mobileno to access");
		int acc1=Integer.parseInt(sc.nextLine());
		String mobileno1=sc.nextLine();
		User u=dao.getUserByAcc(acc1);
		if(u!=null && u.getMobile().equals(mobileno1))
		{
			b=true;
		}
		else {
			System.out.println("Access denied!!!!!!!!!!\nExit...........");
			b=false;
		}
		while(b)
		{
			
			System.out.println("Enter:\n(1)->To view your Account details        (2)->Update your location "
					+ 				 "\n(3)->Update your balance       		     (4)->Delete your Account ");
			int i=Integer.parseInt(sc.nextLine());
			if(i==1)
			{
				
				User us=dao.getUserByAcc(acc1);
				
				System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s %-15s", 
						"AccountNo.","UserName","Location","Balance","Mobile No","Bank-Id"));
				System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s %-15s\n", us.getAccno(),
						us.getUname(),us.getLocation(),us.getBalance(),us.getMobile(),us.getBankid()));
			}
			else if(i==2)
			{
				System.out.println("Enter location to update");
				
				String location=sc.nextLine();
				dao.updateUserLocation(acc1, location);
			}
			else if(i==3) {
				System.out.println("Enter balance to update");
				double balance=Double.parseDouble(sc.nextLine());
				dao.updateUserBalance(acc1, balance);
			}
			else if(i==4)
			{
				System.out.println("Enter yes/no to delete");
				String y=sc.nextLine();
				if(y.equalsIgnoreCase("yes"))
				{
					dao.deleteUser(acc1);
					System.out.println("Your Account is deleted$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					break;
				}
				
			}
			else
			{
				System.out.println("Exting User Interface--------------------------->");
				break;
			}
		}
	}
}
