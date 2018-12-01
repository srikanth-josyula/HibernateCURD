package com.sample.operations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sample.operations.dao.impl.PersonServiceImpl;
import com.sample.operations.dto.Address;
import com.sample.operations.entities.CompanyEntity;
import com.sample.operations.entities.ExternalReportee;
import com.sample.operations.entities.InternalReportee;
import com.sample.operations.entities.PersonEntity;
import com.sample.operations.entities.ReporteeEntity;
import com.sample.operations.exceptions.CustomException;

public class Client {

	static PersonServiceImpl prsService = new PersonServiceImpl();

	public static void main(String[] args) {

		createPersonTest();
		// fetchPersonTest(1);
		// updatePersonTest(3);
		// deletePersonTest(5);
		// createhqlQuery();
	}

	@SuppressWarnings("unused")
	private static void createhqlQuery() {

		try {
			prsService.customQuery();
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	public static void createPersonTest() {

		Address adrs = new Address();
		adrs.setCity("Hyderabad");
		adrs.setCountry("India");
		adrs.setState("Andhra Pradesh");
		adrs.setStreet("Hitech City");

		Set<Integer> contactNumbers = new HashSet<Integer>();
		contactNumbers.add(123654789);
		contactNumbers.add(981254789);

		CompanyEntity cmp = new CompanyEntity();
		cmp.setCompanyName("Apple.Inc");
		cmp.setLocation("Hyderabad");
		cmp.setJobTitle("Sr.Java Developer");

		PersonEntity prs = new PersonEntity();
		prs.setPersonName("Srikanth Josyula");
		prs.setEmailAddress("srikanth.josyula@xyz.com");
		prs.setDateOfBirth(getMyBirthday());
		prs.setContactNumbers(contactNumbers);
		prs.setAddress(adrs);
		prs.setAge(23);
		prs.setCompany(cmp);

		try {
			prsService.createPerson(prs);
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	private static Date getMyBirthday() {

		Calendar c = Calendar.getInstance();
		c.set(c.YEAR, 1984);
		c.set(c.MONTH, 6);
		c.set(c.DATE, 23);

		return c.getTime();
	}

	public static void fetchPersonTest(int prsId) {

		try {
			PersonEntity person = prsService.fetchPerson(prsId);
			System.out.println("Person Name for the " + prsId + " is :: " + person.getPersonName());
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	public static void updatePersonTest(int prsId) {

		try {
			PersonEntity person = prsService.updatePerson(prsId);
			System.out.println("Updated Succesfully the personId is :: " + person.getPersonId());
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	public static void deletePersonTest(int prsId) {

		try {
			prsService.deletePerson(prsId);
			System.out.println("Person Name for the " + prsId + " is deleted succesfully");
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	public static List<ReporteeEntity> addReportees() {

		List<ReporteeEntity> list = new ArrayList<ReporteeEntity>();

		ExternalReportee exreportee = new ExternalReportee();
		exreportee.setRepoteeName("Kanth");
		exreportee.setContractorType("Onsite-Contractor");
		exreportee.setContractorLevel(6);

		InternalReportee intreportee = new InternalReportee();
		intreportee.setRepoteeName("Joey");
		intreportee.setVendorType("Onsite-Vendors");
		intreportee.setVendorLevel(3);

		list.add(exreportee);
		list.add(intreportee);

		return list;

	}
}
