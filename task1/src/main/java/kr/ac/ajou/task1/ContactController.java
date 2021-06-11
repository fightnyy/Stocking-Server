package kr.ac.ajou.task1;

import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
public class ContactController {
    List<Contact> contacts = new ArrayList<>();
    ContactController()
    {
    }

    public boolean add(Contact contact)
    {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean delete(Contact contact)
    {
        if (!contacts.contains(contact))
        {
            return false;
        }
        else{
            contacts.remove(contact);
            return true;
        }
    }

    public List<Contact> all()
    {
        return contacts;
    }

    public Optional<Contact> getById(UUID uuid)
    {
        for (Contact contact : contacts) {
            if (contact.getId().equals(uuid))
            {
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }

    public boolean edit(Contact contact)
    {
        for (Contact contact1 : contacts) {
            if (contact1.getId().equals(contact.getId()))
            {
                contact1 = contact;
                return true;
            }
        }
        return false;
    }

}
