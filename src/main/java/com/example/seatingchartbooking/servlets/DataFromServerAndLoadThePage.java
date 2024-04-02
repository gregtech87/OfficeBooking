package com.example.seatingchartbooking.servlets;

import java.io.*;
import com.example.seatingchartbooking.DataCleaner;
import com.example.seatingchartbooking.LoginValidator;
import com.example.seatingchartbooking.jsonStuff.AdminToJson;
import com.example.seatingchartbooking.jsonStuff.CorruptItemToJson;
import com.example.seatingchartbooking.jsonStuff.DeleteOldEntries;
import com.example.seatingchartbooking.jsonStuff.JsonToClient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "dataFromServerAndLoadThePage", value = "/index.jsp")
public class DataFromServerAndLoadThePage extends HttpServlet {


    public DataFromServerAndLoadThePage() {
    }

    public void init() {
//        Cleaning process for JSONs when date expired.
        Thread t = new Thread(new DataCleaner());
        t.start();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        JSON data to console.
        JsonToClient jsonToClient = new JsonToClient();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
        request.setAttribute("tableListFromServer", jsonToClient.getTableData());
        request.setAttribute("roomListFromServer", jsonToClient.getRoomData());
        request.setAttribute("permanentSeatListFromServer", jsonToClient.getpermanentSeatData());
        request.setAttribute("errorBooking", CorruptItemToJson.getErrorMessage());
        request.setAttribute("errorLogin", LoginValidator.getLoginErrorMessage());
//        ----------------------------------
//       Load webpage after updating data from server.
        requestDispatcher.forward(request, response);
//        Resets error message after use.
        CorruptItemToJson.setErrorMessage("\"ok\"");
        LoginValidator.setLoginErrorMessage("\"ok\"");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    public void destroy() {
    }
}