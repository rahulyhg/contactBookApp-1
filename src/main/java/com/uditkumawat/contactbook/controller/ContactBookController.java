package com.uditkumawat.contactbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uditkumawat.beans.Contact;
import com.uditkumawat.contactJdbcRepository.ContactJdbcRepository;
import com.uditkumawat.contactbook.exceptionHandler.ContactBookException;
import com.uditkumawat.contactbook.util.RequestValidations;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class ContactBookController {

	private Logger logger = LoggerFactory.getLogger(ContactBookController.class);

	@Autowired
	ContactJdbcRepository repository;

	/**
	 * API - Get all contacts
	 * 
	 * @return List<Contact>
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public List<Contact> getAllContacts() {

		logger.info("getAllContact() called");

		return repository.findAll();
	}

	/**
	 * Add contact to existing contacts
	 * 
	 * @param contact
	 * @return
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public List<Contact> addContact(@RequestBody Contact contact) {

		logger.info("addContact() method called");
		logger.debug("addContact() , Contact {}", contact);

		RequestValidations.checkRequestBody(contact);

		if (repository.addContact(contact) == 0) {
			throw new ContactBookException("Contact not added", HttpStatus.INTERNAL_SERVER_ERROR.value());
		} else {
			return repository.findAll();
		}
	}

	/**
	 * Update existing contact
	 * 
	 * @param id
	 * @param contact
	 * @return
	 */
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public List<Contact> updateContact(@PathVariable String id, @RequestBody Contact contact) {
		logger.info("updateContact() method called");
		logger.debug("updateContact() , id -> {} , Contact ->{}", id, contact);

		RequestValidations.checkId(id);

		if (repository.updateContact(Long.parseLong(id), contact) == 0) {
			throw new ContactBookException("Contact not found", HttpStatus.BAD_REQUEST.value());
		} else {
			return repository.findAll();
		}
	}

	/**
	 * Deletes existing contact
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public List<Contact> deleteContact(@PathVariable String id) throws Exception {

		logger.info("deleteContact() method called");
		logger.debug("deleteContact() , id {}", id);

		RequestValidations.checkId(id);

		if (repository.deleteContact(Long.parseLong(id)) == 0) {
			throw new ContactBookException("Contact not found", HttpStatus.BAD_REQUEST.value());
		} else {
			return repository.findAll();
		}
	}

	/**
	 * Search contact based on searchValue i.e. name or email
	 * 
	 * @param searchValue
	 * @param offset
	 * @param limit
	 * @return List<Contact>
	 */
	@RequestMapping(value = "/contact/{searchValue}", method = RequestMethod.GET)
	public List<Contact> getContactsByNameOrEmail(@PathVariable("searchValue") String searchValue,
			@RequestParam(value = "offset", defaultValue = "0", required = false) String offset,
			@RequestParam(value = "limit", defaultValue = "10", required = false) String limit) {

		logger.info("getContactsByNameOrEmail() method called");
		logger.info("getContactsByNameOrEmail() , searchValue->{},limit->{},offset->{}", searchValue, limit, offset);

		if (limit == null) {
			limit = "10";
		}

		if (offset == null) {
			offset = "0";
		}

		return repository.getContactsByNameOrEmail(searchValue, Integer.parseInt(limit), Integer.parseInt(offset));
	}

}
