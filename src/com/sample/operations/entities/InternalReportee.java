package com.sample.operations.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;

@Entity
@DiscriminatorValue("Internal_Reportee")
@NamedNativeQuery(name = "Reportee.byID", query = "select * from InternalReportee p where p.reporteeentity = ?", resultClass = InternalReportee.class)
public class InternalReportee extends ReporteeEntity {

	private String vendorType;
	private int vendorLevel;

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public int getVendorLevel() {
		return vendorLevel;
	}

	public void setVendorLevel(int vendorLevel) {
		this.vendorLevel = vendorLevel;
	}

}
