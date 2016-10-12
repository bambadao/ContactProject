package com.test.contactproject.servlet;

import com.test.contactproject.dao.ContactDAO;
import com.test.contactproject.dao.ContactDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt((String) request.getParameter("id"));
        
        ContactDAO contactDAO = new ContactDAOImpl();
        if (contactDAO.deleteContact(id)) {
            response.getWriter().write("delete success");
        } else {
            response.getWriter().write("delete failed");
        }
    }

}
