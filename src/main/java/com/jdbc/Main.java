package com.jdbc;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdbc.access.AdminAccess;
import com.jdbc.access.UserAccess;
import com.jdbc.bank.User;
import com.jdbc.dao.BankDao;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(JdbcConfig.class);
		BankDao dao=(BankDao) context.getBean(BankDao.class);
		Scanner sc =new Scanner(System.in);
		while(true)
		{
			System.out.println("Enter:\n(1)->User Acces       (2)->Admin Access");
			int i=Integer.parseInt(sc.nextLine());
			if(i==1)
			{
				UserAccess access=new UserAccess();
				access.userAccess(dao);
			}
			else if(i==2)
			{
				AdminAccess access=new AdminAccess();
				access.adminAccess(dao);
			}
			else
				break;
		}
	}

}