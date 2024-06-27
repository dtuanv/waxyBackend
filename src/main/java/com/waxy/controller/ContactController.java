package com.waxy.controller;

import com.waxy.database.entity.Contact;
import com.waxy.database.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepository;

    @PostMapping("/saveContact")
    private void saveContact(@RequestBody Contact contact){

        contactRepository.save(contact);
    }

    @GetMapping("/allContact/{userRole}")
    private Set<Contact>  getAllContacts(@PathVariable String userRole){

        Set<Contact> contacts;
                if(userRole.equals("admin") ){
                    contacts =      contactRepository.findAll().stream().collect(Collectors.toSet());
                } else {
                    contacts = new HashSet<>();
                }

        return contacts;
    }
    @PostMapping ("/updateReadContact")
    private Contact updateReadContact(@RequestBody Contact contact){
      return   contactRepository.updateReadContactQuery(!contact.isRead(), contact.getId() );
    }

}
