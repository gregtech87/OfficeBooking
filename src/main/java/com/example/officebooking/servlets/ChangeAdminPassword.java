package com.example.officebooking.servlets;

import com.example.officebooking.jsonStuff.AdminToJson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "changeAdminPassword", value = "/changeAdminPassword")
public class ChangeAdminPassword extends HttpServlet {

    private String oldUsername;
    private String newUsername;
    private String oldPassword;
    private String newPassword;
    private String newPassword2;
    private AdminToJson adminToJson = new AdminToJson();
    public ChangeAdminPassword() {
    }

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        oldUsername = request.getParameter("oldUsername");
        newUsername = request.getParameter("newUsername");
        oldPassword = request.getParameter("oldPassword");
        newPassword = request.getParameter("newPassword");
        newPassword2 = request.getParameter("newPassword2");

        if(adminToJson.checkAdmin(oldUsername, newUsername, oldPassword, newPassword, newPassword2)){
            response.sendRedirect(request.getContextPath() + "/eZ$!sypN2qAYZCyFyJkFybh_K$Vnf-prk)v)X9Ay");
        } else {
            response.sendRedirect(request.getContextPath() + "/eZ$!sypN2qAYZCyFyJkFybh_K$Vnf-prk)v)X9Ay");
            adminToJson.setCredentialsErrorMessage("\"Not ok\"");
        }
    }

    public void destroy() {
    }
}