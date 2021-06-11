package kr.ac.ajou.task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface IContactManager {
    boolean addContact(Contact contact);
    boolean deleteContact(Contact contact);
    boolean editContact(Contact contact);
    List<Contact> getContacts();
    Contact getContactById(UUID uuid);

}
