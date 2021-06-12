package com.nt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nt.dto.PatientDTO;
import com.nt.service.IPatientMgmtService;
import com.nt.vo.PatientVO;
import org.springframework.beans.BeanUtils;

@Controller("controller")
public class MainController {
	
	@Autowired
	private IPatientMgmtService service;
	
	public List<PatientVO> showPatiensByCities(String cities[])throws Exception{
		//use service
		List<PatientDTO> listDTO=service.fetchPatientsByCities(cities);
		//convert listDTO to listVO
		List<PatientVO> listVO=new ArrayList();
		listDTO.forEach(dto->{
			PatientVO vo=new PatientVO();
			BeanUtils.copyProperties(dto, vo);
			vo.setSrNo(String.valueOf(dto.getSrNo()));
			vo.setPatient_id(String.valueOf(dto.getPatient_id()));
			vo.setFirstName(String.valueOf(dto.getFirstName()));
			vo.setLastName(String.valueOf(dto.getLastName()));
			vo.setAdhaarNumber(String.valueOf(dto.getAdhaarNumber()));
			listVO.add(vo);
		});
		return listVO;
	}//method


}
