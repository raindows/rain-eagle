package cn.dalabs.r;

import java.io.Serializable;

public class ParamInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String PatientName;
	private String PatientTel;
	private String TestCode;
	private String StartDate;
	private String EndDate;

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	public String getPatientTel() {
		return PatientTel;
	}

	public void setPatientTel(String patientTel) {
		PatientTel = patientTel;
	}

	public String getTestCode() {
		return TestCode;
	}

	public void setTestCode(String testCode) {
		TestCode = testCode;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

}
