package com.nt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.bo.PatientBO;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import javax.sql.DataSource;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

@Repository("patientDAO")
public class PatientDAOImpl implements IPatientDAO {
	
	private static final String GET_PATIENTS_BY_CITIES="SELECT PATIENT_ID,FIRST_NAME,LAST_NAME,ADHAAR_NUMBER FROM PATIENTS WHERE CITY IN";
	
	@Autowired
	private  DataSource  ds;

	@Override
	public List<PatientBO> getPatientByCities(String cond) throws Exception {
		 List<PatientBO> listBO=null;
		try(  //get pooled jdbc con   
				Connection con=ds.getConnection();
				  // create STatement object
				Statement st=con.createStatement(); // preparedSTatement object can not used here becoz no.of desgs is dynamic value
			   //send and execute SQL query in Db s/w
				// select empno,ename,job,sal,deptno,mgr  from emp where job in('CLERK','MANAGER') order by job
				 ResultSet rs=st.executeQuery(GET_PATIENTS_BY_CITIES+cond+" ORDER BY CITY");
				){
			 //convert RS object records to List of BO class objs
			 listBO=new ArrayList();
			 PatientBO bo=null;
			 while(rs.next()) {
				 //copy each record of RS to a object of EmployeeBO class
				  bo=new  PatientBO();
				 bo.setPatient_id(rs.getInt(1));
				 bo.setFirstName(rs.getString(2));
				 bo.setLastName(rs.getString(3));
				 bo.setAdhaarNumber(rs.getInt(4));
				 
				 //add each object of EmpoyeeBO to  List Collection
				 listBO.add(bo);
			 }//while
		}//try   // RS, ST, Con object will be closed here automaticlly..
		catch(SQLException se) {
			se.printStackTrace();
			throw se;  //for Exception  propagation
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listBO;
	}//method


}
