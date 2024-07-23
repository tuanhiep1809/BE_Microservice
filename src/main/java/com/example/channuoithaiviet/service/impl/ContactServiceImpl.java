package com.example.channuoithaiviet.service.impl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Category;
import com.example.channuoithaiviet.entity.Contact;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateContactRequest;
import com.example.channuoithaiviet.repository.ContactRepository;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Override  // lấy all
    public List<Contact> getList() {
        // TODO Auto-generated method stub
        return contactRepository.findAll(Sort.by("id").descending());
    }
    @Override  // lấy theo id
    public Contact getContact(long id){
        Contact blog = contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Contact"));
        return blog;
    }
    @Override // tạo mới
    public Contact createContact(CreateContactRequest request) {
        // TODO Auto-generated method stub
        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setGmail(request.getGmail());
        contact.setContent(request.getContent());
        contact.setStatus(false);        
        contactRepository.save(contact);
        return contact;
    }
    @Override
    public Contact updateContact(long id, CreateContactRequest request) {
        // TODO Auto-generated method stub
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Contact"));
        contact.setName(request.getName());
        contact.setGmail(request.getGmail());
        contact.setContent(request.getContent());      
        contactRepository.save(contact);
        return contact;
    }
    
    @Override
    public void deleteContact(long id) {
        // TODO Auto-generated method stub
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Contact"));
        contactRepository.delete(contact);
    }
    
    @Override
    public void enableContact(long id) {
        // TODO Auto-generated method stub
    	Contact contact = contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Contact With Id: " + id));
        if(contact.isStatus()){
        	contact.setStatus(false);
        } else{
        	contact.setStatus(true);
        }
        contactRepository.save(contact);
    }

}
