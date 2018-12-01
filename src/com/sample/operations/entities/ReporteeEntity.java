package com.sample.operations.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "REPORTEEENTITY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	name = "REPORTEE_ENTITY_TYPE",
	discriminatorType = DiscriminatorType.STRING
	)
public class ReporteeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reporteeId;
	private String repoteeName;

	public int getReporteeId() {
		return reporteeId;
	}

	public void setReporteeId(int reporteeId) {
		this.reporteeId = reporteeId;
	}

	public String getRepoteeName() {
		return repoteeName;
	}

	public void setRepoteeName(String repoteeName) {
		this.repoteeName = repoteeName;
	}
}
