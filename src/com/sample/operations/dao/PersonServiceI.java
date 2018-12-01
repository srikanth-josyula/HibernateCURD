package com.sample.operations.dao;

import com.sample.operations.entities.PersonEntity;
import com.sample.operations.exceptions.CustomException;

public interface PersonServiceI {

	public void createPerson(PersonEntity person) throws CustomException;
	public PersonEntity updatePerson(int personId) throws CustomException;
	public PersonEntity fetchPerson(int personId) throws CustomException;
	public void deletePerson(int personId) throws CustomException;

}
