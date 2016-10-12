
package com.test.contactproject.servlet;

import com.google.gson.Gson;
import com.test.contactproject.dao.ContactDAO;
import com.test.contactproject.dao.ContactDAOImpl;
import com.test.contactproject.model.Contact;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ContactDAO contactDAO = new ContactDAOImpl();
        List<Contact> contacts = contactDAO.getAllContacts();
        
        String jsonContacts = new Gson().toJson(contacts);
        response.setContentType("application/json");
        response.getWriter().write(jsonContacts);
        
    }

}
