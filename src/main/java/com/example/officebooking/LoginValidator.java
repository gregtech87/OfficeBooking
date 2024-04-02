package com.example.officebooking;

import com.example.officebooking.jsonStuff.JsonToClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class LoginValidator {
    public static String loginErrorMessage = "\"ok\"";

    private JsonToClient jsonToClient = new JsonToClient();
    private Admin admin = new Admin();
    private ObjectMapper objectMapper = new ObjectMapper();
//    private String username = "admin";
//    private String password = "password";
    private boolean approved = false;


    public LoginValidator() {
    }

    public boolean checkCredentials(String user, String password) throws IOException {

        String adminString = jsonToClient.getAdminData();
        admin = objectMapper.readValue(adminString, Admin.class);

        if(admin.getUsername().equals(user) && admin.getPassword().equals(password)){

//        if (this.username.equals(user) && this.password.equals(password)) {
            approved = true;
        }
        System.out.println(approved);
        return approved;
    }

    public static String getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public static void setLoginErrorMessage(String loginErrorMessage) {
        LoginValidator.loginErrorMessage = loginErrorMessage;
    }
}