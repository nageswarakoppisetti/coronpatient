package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;

import com.nt.controller.MainController;
import com.nt.vo.PatientVO;

import java.util.Arrays;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootProj04RealtimeDiAutoConfigurationCoronaAppApplication {

	public static void main(String[] args) {
		
		//read inputs from enduser
				Scanner sc=new Scanner(System.in);
				System.out.println("Cites count::");
				int count=sc.nextInt();
				String cites[]=null;
				if(count>=1)
					cites=new String[count];
				else {
					System.out.println("invalid cities count ");
					return;
				}
				for(int i=0;i<count;++i) {
					System.out.println("enter cites"+(i+1));
					String desg=sc.next();
					cites[i]=desg;
				}
			     		
				//get IOC container
		
				ApplicationContext ctx=	SpringApplication.run(BootProj04RealtimeDiAutoConfigurationCoronaAppApplication.class, args);
	
				//get Controller class obj
				MainController controller=ctx.getBean("controller",MainController.class);
				//invoke b.method
				try {
					List<PatientVO> listVO=controller.showPatiensByCities(cites);
					System.out.println("IS list is empty"+listVO.isEmpty());
					System.out.println("patient details having cities "+Arrays.toString(cites));
					listVO.forEach(vo->{
						System.out.println(vo);
					});
				}//try
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Some Internal problem::"+e.getMessage());
				}
				//close IOC container
				((ConfigurableApplicationContext) ctx).close();
	
	}

}
