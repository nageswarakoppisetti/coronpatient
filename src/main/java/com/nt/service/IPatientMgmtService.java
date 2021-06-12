package com.nt.service;

import java.util.List;

import com.nt.dto.PatientDTO;

public interface IPatientMgmtService {

	public List<PatientDTO>  fetchPatientsByCities(String desgs[])throws Exception;
}
