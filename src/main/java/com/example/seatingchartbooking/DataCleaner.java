package com.example.seatingchartbooking;

import com.example.seatingchartbooking.jsonStuff.DeleteOldEntries;

import java.util.Calendar;
import java.util.Timer;

public class DataCleaner implements Runnable {

    public DataCleaner() {
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();

        //Selecting tomorrow's date.
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1;

        //Select time for code to run.
        int hour = 0;
        int minute = 30;

        //Setting the date from when you want to activate scheduling(tomorrow).
        date.set(year, month, day, hour, minute);
        System.out.println("SET time: " + date.getTime());

        //Execute "DeleteOldEntries" every 24 hours.
        timer.schedule(new DeleteOldEntries(), date.getTime(), 86400000);
    }
}