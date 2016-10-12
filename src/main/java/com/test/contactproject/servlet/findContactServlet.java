package com.test.contactproject.servlet;

import com.google.gson.Gson;
import com.test.contactproject.dao.ContactDAO;
import com.test.contactproject.dao.ContactDAOImpl;
import com.test.contactproject.model.Contact;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class findContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt((String) request.getParameter("id"));
        
        ContactDAO contactDAO = new ContactDAOImpl();
        Contact contact = contactDAO.getContactById(id);
        
        String jsonContact = new Gson().toJson(contact);
        response.setContentType("application/json");
        response.getWriter().write(jsonContact);
    }

}
