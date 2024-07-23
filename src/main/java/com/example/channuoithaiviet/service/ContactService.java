package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.Contact;
import com.example.channuoithaiviet.model.request.CreateContactRequest;

public interface ContactService {
	 	List<Contact> getList();
	    Contact getContact(long id);
	    Contact createContact(CreateContactRequest request);
	    Contact updateContact(long id,CreateContactRequest request);
	    void deleteContact(long id);
	    void enableContact(long id);
}
