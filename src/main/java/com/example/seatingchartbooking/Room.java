package com.example.seatingchartbooking;

public class Room {
    private String roomId;
    private String name;
    private String dateOfBooking;
    private String startTime;
    private String endTime;
    private String fullDay;
    private String permanentBooked;
    private String latestEdit;

    public Room() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFullDay() {
        return fullDay;
    }

    public void setFullDay(String fullDay) {
        this.fullDay = fullDay;
    }

    public String getPermanentBooked() {
        return permanentBooked;
    }

    public void setPermanentBooked(String permanentBooked) {
        this.permanentBooked = permanentBooked;
    }

    public String getLatestEdit() {
        return latestEdit;
    }

    public void setLatestEdit(String latestEdit) {
        this.latestEdit = latestEdit;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBooking='" + dateOfBooking + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", fullDay='" + fullDay + '\'' +
                ", permanentBooked='" + permanentBooked + '\'' +
                ", lastEdit='" + latestEdit + '\'' +
                '}';
    }
}