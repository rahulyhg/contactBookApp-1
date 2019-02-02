package com.uditkumawat.contactbook.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.uditkumawat.beans.Contact;
import com.uditkumawat.contactJdbcRepository.ContactJdbcRepository;
import com.uditkumawat.contactbook.exceptionHandler.ContactBookException;

@RunWith(MockitoJUnitRunner.class)
public class ControllerBookControllerTest {

	@Mock
	ContactJdbcRepository repository;

	@InjectMocks
	ContactBookController contactBookController;

	@Test
	public void getAllContactsTest() {

		List<Contact> contacts = new ArrayList<>();
		contacts.add(new Contact("udit", "udit@gmail.com", "9877242412"));
		when(repository.findAll()).thenReturn(contacts);

		assertEquals(1, contactBookController.getAllContacts().size());

	}

	@Test
	public void getAllContactsTest_withLongList() {

		List<Contact> contacts = new ArrayList<>();
		contacts.add(new Contact("udit", "udit@gmail.com", "9877242412"));
		contacts.add(new Contact("udit", "udi1t@gmail.com", "9877242412"));
		contacts.add(new Contact("udit", "udit2@gmail.com", "9877242412"));
		when(repository.findAll()).thenReturn(contacts);

		assertEquals(3, contactBookController.getAllContacts().size());

	}

	@Test
	public void addContactTest() {

		List<Contact> contacts = new ArrayList<>();

		Contact ct = new Contact("udit", "udit@gmail.com", "9877242412");

		when(repository.addContact(ct)).thenReturn(1);

		contacts.add(ct);

		assertEquals(0, contactBookController.addContact(ct).size());

	}
	
	@Test(expected=ContactBookException.class)
	public void addContactTest_withException() {

		List<Contact> contacts = new ArrayList<>();

		Contact ct = new Contact("udit", "udit@gmail.com", "9877242412");

		when(repository.addContact(ct)).thenReturn(0);

		contactBookController.addContact(ct);
	}
}
