package com.test.contactproject.servlet;

import com.test.contactproject.dao.ContactDAO;
import com.test.contactproject.dao.ContactDAOImpl;
import com.test.contactproject.model.Contact;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt((String) request.getParameter("id"));
        String name = (String) request.getParameter("name");
        String phone = (String) request.getParameter("phone");
        String address = (String) request.getParameter("address");
        
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setPhone(phone);
        contact.setAddress(address);
        ContactDAO contactDAO = new ContactDAOImpl();
        if (contactDAO.updateContact(contact)) {
            response.getWriter().write("update success");
        } else {
            response.getWriter().write("update failed");
        }
    }
}
