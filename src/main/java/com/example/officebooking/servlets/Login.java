package com.example.officebooking.servlets;

import com.example.officebooking.LoginValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    private String username;
    private String password;

private LoginValidator loginValidator = new LoginValidator();
    public Login() {
    }

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        if(loginValidator.checkCredentials(username, password)){
            response.sendRedirect(request.getContextPath() + "/eZ$!sypN2qAYZCyFyJkFybh_K$Vnf-prk)v)X9Ay");
        } else {
            LoginValidator.setLoginErrorMessage("\"Not ok\"");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    public void destroy() {
    }

}