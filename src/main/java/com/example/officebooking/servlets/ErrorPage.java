package com.example.officebooking.servlets;

import java.io.*;

import com.example.officebooking.jsonStuff.JsonToClient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "5w5iUnWfFs$*yTQ'44n_vn+P7')'+C*Tquo_._X9", value = "/5w5iUnWfFs$*yTQ'44n_vn+P7')'+C*Tquo_._X9")
public class ErrorPage extends HttpServlet {

    public ErrorPage() {
    }

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //        JSON data to console.
        JsonToClient jsonToClient = new JsonToClient();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
        request.setAttribute("errorTableListFromServer", jsonToClient.getCorruptTableData());
        request.setAttribute("errorRoomListFromServer", jsonToClient.getCorruptRoomData());
//        ----------------------------------
//       Load webpage after updating data from server.
        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    public void destroy() {
    }
}