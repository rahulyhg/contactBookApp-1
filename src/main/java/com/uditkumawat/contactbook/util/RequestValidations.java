package com.uditkumawat.contactbook.util;

import org.springframework.http.HttpStatus;

import com.uditkumawat.beans.Contact;
import com.uditkumawat.contactbook.exceptionHandler.ContactBookException;

/**
 * This Util class basically checks all validations
 * 
 * @author ukumawat
 *
 */
public class RequestValidations {

	public static boolean checkRequestBody(Contact contact) {

		if (contact.getName() == null) {
			throw new ContactBookException("Name should not be null", HttpStatus.BAD_REQUEST.value());
		}

		if (contact.getPhone() == null) {
			throw new ContactBookException("Phone Number should not be null", HttpStatus.BAD_REQUEST.value());
		}

		return true;
	}

	public static boolean checkId(String id) {

		try {
			
			int number = Integer.parseInt(id);
			return true;
		}
		catch(Exception e) {
			throw new ContactBookException("Id is invalid.It should be numeric", HttpStatus.BAD_REQUEST.value());
		}
	}
}
