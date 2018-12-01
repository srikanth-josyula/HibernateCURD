package com.sample.operations.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;

@Entity
@DiscriminatorValue("External_Reportee")
@NamedNativeQuery(name = "ExReportee.byID", query = "select * from ExternalReportee p where p.reporteeentity = ?", resultClass = ExternalReportee.class)
public class ExternalReportee extends ReporteeEntity {

	private String contractorType;
	private int contractorLevel;

	public String getContractorType() {
		return contractorType;
	}

	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}

	public int getContractorLevel() {
		return contractorLevel;
	}

	public void setContractorLevel(int contractorLevel) {
		this.contractorLevel = contractorLevel;
	}

}
