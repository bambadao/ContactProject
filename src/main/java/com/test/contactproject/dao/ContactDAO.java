package com.test.contactproject.dao;

import com.test.contactproject.model.Contact;
import java.util.List;

public interface ContactDAO {
    public Boolean addContact(Contact contact);
    public Boolean updateContact(Contact id);
    public Boolean deleteContact(Integer id);
    public Contact getContactById(Integer id);
    public List<Contact> getAllContacts();
}
