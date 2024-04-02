package com.example.seatingchartbooking.servlets;

import java.io.*;

import com.example.seatingchartbooking.jsonStuff.AdminToJson;
import com.example.seatingchartbooking.jsonStuff.JsonToClient;
import com.example.seatingchartbooking.jsonStuff.PermanentSeatsToJson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "eZ$!sypN2qAYZCyFyJkFybh_K$Vnf-prk)v)X9Ay", value = "/eZ$!sypN2qAYZCyFyJkFybh_K$Vnf-prk)v)X9Ay")
public class AdminPage extends HttpServlet {
    private final PermanentSeatsToJson seatsToJson = new PermanentSeatsToJson();
    private AdminToJson adminToJson = new AdminToJson();
    private JsonToClient jsonToClient = new JsonToClient();
    public AdminPage() {
    }

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //        JSON data to console.

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin.jsp");
        request.setAttribute("permanentSeatListFromServer", jsonToClient.getpermanentSeatData());
        request.setAttribute("credentialsErrorMessage", AdminToJson.getCredentialsErrorMessage());
//        ----------------------------------
//       Load webpage after updating data from server.
        requestDispatcher.forward(request, response);
//        Resets error message after use.
        AdminToJson.setCredentialsErrorMessage("\"ok\"");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonStringFromHtml = request.getParameter("permTableToJson");
        System.out.println(jsonStringFromHtml);
        seatsToJson.saveToJsonFile(jsonStringFromHtml);
        response.sendRedirect(request.getContextPath() + "/eZ$!sypN2qAYZCyFyJkFybh_K$Vnf-prk)v)X9Ay");
    }

    public void destroy() {
    }
}