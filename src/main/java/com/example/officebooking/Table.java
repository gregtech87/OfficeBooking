package com.example.officebooking;

public class Table {
    private int tableId;
    private String name;
    private String dateOfBooking;
    private String beforeLunch;
    private String afterLunch;
    private String fullDay;
    private String permanentPlace;
    private String latestEdit;

    public Table() {
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
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

    public String getBeforeLunch() {
        return beforeLunch;
    }

    public void setBeforeLunch(String beforeLunch) {
        this.beforeLunch = beforeLunch;
    }

    public String getAfterLunch() {
        return afterLunch;
    }

    public void setAfterLunch(String afterLunch) {
        this.afterLunch = afterLunch;
    }

    public String getFullDay() {
        return fullDay;
    }

    public void setFullDay(String fullDay) {
        this.fullDay = fullDay;
    }

    public String getPermanentPlace() {
        return permanentPlace;
    }

    public void setPermanentPlace(String permanentPlace) {
        this.permanentPlace = permanentPlace;
    }

    public String getLatestEdit() {
        return latestEdit;
    }

    public void setLatestEdit(String latestEdit) {
        this.latestEdit = latestEdit;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableId=" + tableId +
                ", name='" + name + '\'' +
                ", dateOfBooking='" + dateOfBooking + '\'' +
                ", beforeLunch='" + beforeLunch + '\'' +
                ", afterLunch='" + afterLunch + '\'' +
                ", fullDay='" + fullDay + '\'' +
                ", permanentPlace='" + permanentPlace + '\'' +
                ", latestEdit='" + latestEdit + '\'' +
                '}';
    }
}