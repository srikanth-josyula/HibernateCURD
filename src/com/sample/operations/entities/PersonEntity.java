package com.sample.operations.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.sample.operations.dto.Address;

@Entity
@Table(name = "PERSONENTITY")
@GenericGenerator(name = "generatorName", strategy = "sequence")
@NamedNativeQuery(name = "PersonDetails.byID", query = "select * from PersonEntity p where p.PersonId = ?", resultClass = PersonEntity.class)

public class PersonEntity {

	@Id
	@GeneratedValue(generator = "generatorName")
	@Column(name = "PERSONID", updatable = false, nullable = false)
	private Integer PersonId;

	@Column(name = "PERSONNAME")
	private String PersonName;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATEOFBIRTH")
	private Date dateOfBirth;

	@Column(name = "EMAILADDRESS")
	private String emailAddress;

	@Column(name = "CONTACTNUMBERS")
	@ElementCollection
	@JoinTable(name = "PERSON_CONTACTINFO", joinColumns = @JoinColumn(name = "PERSONID"))
	private Set<Integer> contactNumbers = new HashSet<Integer>();

	// @Formula(value = "date_part('year', AGE(DATEOFBIRTH) as AGE")
	@Column(name = "AGE")
	private int age;

	@Embedded
	@Column(name = "ADDRESS")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMPANY_NAME")
	private CompanyEntity company;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "REPORTEEENTITY")
	private List<ReporteeEntity> reporteeList = new ArrayList<ReporteeEntity>();

	@Transient
	private boolean isValid;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getPersonId() {
		return PersonId;
	}

	public void setPersonId(Integer personId) {
		PersonId = personId;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName) {
		PersonName = personName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public Set<Integer> getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(Set<Integer> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	public List<ReporteeEntity> getReporteeList() {
		return reporteeList;
	}

	public void setReporteeList(List<ReporteeEntity> reporteeList) {
		this.reporteeList = reporteeList;
	}

}
