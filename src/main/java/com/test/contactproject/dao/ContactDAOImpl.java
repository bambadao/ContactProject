package com.test.contactproject.dao;

import com.test.contactproject.connection.ContactConnection;
import com.test.contactproject.model.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactDAOImpl implements ContactDAO {
    
    private Connection connection;

    @Override
    public Boolean addContact(Contact contact) {
        if (contact == null)
            return false;
        
        try {
            connection = ContactConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Contact () VALUES (default,?,?,?)");
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setString(3, contact.getAddress());
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ContactConnection.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

    @Override
    public Boolean updateContact(Contact contact) {
        if (contact == null)
            return false;
        
        try {
            connection = ContactConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Contact SET name=?, phone=?, address=? WHERE id=?");
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setString(3, contact.getAddress());
            preparedStatement.setInt(4, contact.getId());
            
            return preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean deleteContact(Integer id) {
        if (id == null)
            return false;
        
        try {
            connection = ContactConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Contact WHERE id = ?");
            preparedStatement.setInt(1, id);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ContactConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Contact getContactById(Integer id) {
        if (id == null)
            return null;
        
        try {
            connection = ContactConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Contact WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Contact contact = new Contact();
            if (rs.next()) {
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setPhone(rs.getString("phone"));
                contact.setAddress(rs.getString("address"));
            }
            return contact;
        } catch (SQLException ex) {
            Logger.getLogger(ContactConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            connection = ContactConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Contact");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setPhone(rs.getString("phone"));
                contact.setAddress(rs.getString("address"));
                contacts.add(contact);
            }
            return contacts;
        } catch (SQLException ex) {
            Logger.getLogger(ContactConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
