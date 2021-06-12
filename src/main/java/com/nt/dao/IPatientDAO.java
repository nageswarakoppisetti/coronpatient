package com.nt.dao;

import java.util.List;

import com.nt.bo.PatientBO;

public interface IPatientDAO {
    public  List<PatientBO> getPatientByCities(String cond)throws Exception;
}




