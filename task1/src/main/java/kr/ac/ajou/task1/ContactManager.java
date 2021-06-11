package kr.ac.ajou.task1;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContactManager implements IContactManager{


    public ContactManager() {

    }
    @PostConstruct
    private void init()
    {
        List<Contact> contacts = new ArrayList<>();
    }

    @Override
    public boolean addContact(Contact contact)
    {
        return cc.add(contact);
    }

    public boolean deleteContact(Contact contact)
    {
        return cc.delete(contact);
    }

    public List<Contact> getContacts()
    {
        return cc.all();
    }


    @Override
    public Contact getContactById(UUID uuid)
    {
        return cc.getById(uuid).get();
    }

    public boolean editContact(Contact contact)
    {
        return cc.edit(contact);
    }
}
