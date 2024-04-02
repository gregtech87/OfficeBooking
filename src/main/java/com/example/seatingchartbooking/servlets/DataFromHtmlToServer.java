package com.example.seatingchartbooking.servlets;

import java.io.*;
import java.text.ParseException;

import com.example.seatingchartbooking.*;
import com.example.seatingchartbooking.jsonStuff.CorruptItemToJson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DataFromHtml-ToServer", value = "/DataFromHtmlToServer")
public class DataFromHtmlToServer extends HttpServlet {

    public String tableID;
    public String roomID;
    public String nameInputTable;
    public String dateInputTable;
    public String startTimeInputTable;
    public String endTimeInputTable;
    public String nameInputRoom;
    public String dateInputRoom;
    public String startTimeInputRoom;
    public String endTimeInputRoom;
    public String wholeDayTable;
    public String wholeDayRoom;
    public String beforeLunchTable;
    public String afterLunchTable;

    public DataFromHtmlToServer() {
    }

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//      Data in from HTML form.
        tableID = request.getParameter("bookedTable");
        nameInputTable = request.getParameter("nameTable");
        dateInputTable = request.getParameter("dateInputTable");
        startTimeInputTable = request.getParameter("startTimeInputTable");
        endTimeInputTable = request.getParameter("endTimeInputTable");
        wholeDayTable = request.getParameter("wholeDayInputTable");
        beforeLunchTable = request.getParameter("beforeLunchInputTable");
        afterLunchTable = request.getParameter("afterLunchInputTable");

        roomID = request.getParameter("bookedRoom");
        nameInputRoom = request.getParameter("nameRoom");
        dateInputRoom = request.getParameter("dateInputRoom");
        startTimeInputRoom = request.getParameter("startTimeInputRoom");
        endTimeInputRoom = request.getParameter("endTimeInputRoom");
        wholeDayRoom = request.getParameter("wholeDayInputRoom");
//      Convert data from string to java object.
        try {
            convert();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //reload webpage after submitted booking.
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    public void destroy() {
    }

    private void convert() throws ParseException, IOException {
//      Creating java object from client formdata.
        Table table = new Table();
        Room room = new Room();
        Validator validator = new Validator();

        if (tableID != null && table.getPermanentPlace() == null) {
            table.setName(nameInputTable);
            table.setTableId(Integer.parseInt(tableID));
            table.setDateOfBooking(dateInputTable);
            table.setFullDay(wholeDayTable);
            table.setBeforeLunch(beforeLunchTable);
            table.setAfterLunch(afterLunchTable);
        }else{
            System.out.println("Bordet finns inte");
        }

        if (roomID != null && room.getPermanentBooked() == null) {
            room.setName(nameInputRoom);
            room.setRoomId(roomID);
            room.setDateOfBooking(dateInputRoom);
            room.setStartTime(startTimeInputRoom);
            room.setEndTime(endTimeInputRoom);
            room.setFullDay(wholeDayRoom);
        }else{
            System.out.println("Rummet finns inte");
        }

        validator.validateItem(table, room);
    }
}