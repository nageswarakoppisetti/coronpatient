package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bo.PatientBO;
import com.nt.dao.IPatientDAO;
import com.nt.dto.PatientDTO;


@Service("patientService")
public class PatientMgmtServiceImpl implements IPatientMgmtService {

	@Autowired
	private IPatientDAO  dao;
	
	@Override
	public List<PatientDTO> fetchPatientsByCities(String[] desgs) throws Exception {
		 //convert desgs[] into  SQL  IN caluse String condition  ('CLERK','MANAGER','SALESMAN')
		 //StringBuffer buffer=new StringBuffer("(");
		  StringBuilder  buffer=new StringBuilder("(");
		 for(int i=0;i<desgs.length;++i) {
			 if(i==desgs.length-1)  //for the last element of the arary
				 buffer.append("'"+desgs[i]+"')");
			 else
				 buffer.append("'"+desgs[i]+"',");  //for other than last element
		 }//for
		 //convert StringBuffer/StringBuidler object into String
		 String cond=buffer.toString();
		  //use DAO
		   List<PatientBO> listBO=dao.getPatientByCities(cond);
		   //convert ListBO to ListDTO
		   List<PatientDTO> listDTO=new ArrayList();
		   listBO.forEach(bo->{
			   PatientDTO dto=new PatientDTO();
			   //copy  each BO class obj to each DTO class obj 
			   BeanUtils.copyProperties(bo,dto); // property names and type must match in both java beans
			   dto.setSrNo(listDTO.size()+1);
			   //add each obj of DTO class to ListDTO
			   listDTO.add(dto);
		   });

		return listDTO;
	}//for

}
