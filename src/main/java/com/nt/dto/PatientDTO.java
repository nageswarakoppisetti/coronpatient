package com.nt.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PatientDTO implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6932141761921718894L;
	private Integer srNo;
	private Integer patient_id;
	private String  firstName;
	private String  lastName;
	private Double  city;
	private Integer adhaarNumber;

}
