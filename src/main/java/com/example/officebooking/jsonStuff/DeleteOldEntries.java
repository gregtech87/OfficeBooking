package com.example.officebooking.jsonStuff;


import com.example.officebooking.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class DeleteOldEntries extends TimerTask {
    private final TableToJson tableToJson = new TableToJson();
    private final RoomToJson roomToJson = new RoomToJson();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonToClient jsonToClient = new JsonToClient();

    public void run() {
        try {
            searchTableData();
            searchRoomData();
            searchCorruptData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchTableData() throws IOException {

//        create dummydata to be stored last in tables.json.
        Table dummyTable = new Table();
        dummyTable.setName("Dummy McDummyFace");
        dummyTable.setDateOfBooking("2000-01-01");
        TableToJson dummySave = new TableToJson();
        dummySave.saveTable(dummyTable);

        List<Integer> oldTableEntryIndex = new ArrayList<>();
        int tableIndex = 0;

//        Get current date to variable.
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

//        tables.json to list of table objects.
        File tablesJson = new File(jsonToClient.getResourceFile("tables.json").toURI());
        List<Table> tableList = objectMapper.readValue(tablesJson, new TypeReference<>() {
        });

        System.out.println("bord antal: "+ tableList.size());
//        Identifying old entries in list of tables and saving index of those entries in "oldTableEntryIndex".
        for (Table t : tableList) {
            String entryDate = t.getDateOfBooking();
            String yearString = entryDate.substring(0, 4);
            String monthString = entryDate.substring(5, 7);
            String dayString = entryDate.substring(8);
            int year = Integer.parseInt(yearString);
            int month = Integer.parseInt(monthString);
            int day = Integer.parseInt(dayString);

            if (year < currentYear) {
                oldTableEntryIndex.add(tableIndex);
            } else if (month < currentMonth) {
                oldTableEntryIndex.add(tableIndex);
            } else if (day < currentDay) {
                oldTableEntryIndex.add(tableIndex);
            }
            tableIndex++;
        }

//        Looping "tableList" backwards and deleting old ones.
        for (int i = tableList.size() - 1; i >= 0; i--) {
            for (Integer integer : oldTableEntryIndex) {
                if (integer == i) {
                    if (i < tableList.size() - 1) {
                        tableList.remove(i);
                    }
                }
            }
        }
        System.out.println("bord antal: "+ tableList.size());
//        Clear old table JSON file from data.
        clearJsonFromData(tablesJson);

//        Creating new updated JSON.
        for (Table t : tableList) {
            tableToJson.saveTable(t);
        }
    }

    private void searchRoomData() throws IOException {

//        create dummydata to be stored last in rooms.json.
        Room dummyRoom = new Room();
        dummyRoom.setName("Dummy McDummyFace");
        dummyRoom.setDateOfBooking("2000-01-01");
        RoomToJson dummySave = new RoomToJson();
        dummySave.saveRoom(dummyRoom);

        List<Integer> oldRoomEntryIndex = new ArrayList<>();
        int roomIndex = 0;

//        Get current date to variable.
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);


//        rooms.json to list of room objects.
        File roomJson = new File(jsonToClient.getResourceFile("rooms.json").toURI());
        List<Room> roomList = objectMapper.readValue(roomJson, new TypeReference<>() {
        });
        System.out.println("rum antal: "+roomList.size());
//        Identifying old entries in list of rooms and saving index of those entries in "oldRoomEntryIndex".
        for (Room t : roomList) {
            String entryDate = t.getDateOfBooking();
            String yearString = entryDate.substring(0, 4);
            String monthString = entryDate.substring(5, 7);
            String dayString = entryDate.substring(8);
            int year = Integer.parseInt(yearString);
            int month = Integer.parseInt(monthString);
            int day = Integer.parseInt(dayString);

            if (year < currentYear) {
                oldRoomEntryIndex.add(roomIndex);
            } else if (month < currentMonth) {
                oldRoomEntryIndex.add(roomIndex);
            } else if (day < currentDay) {
                oldRoomEntryIndex.add(roomIndex);
            }
            roomIndex++;
        }

//        Looping "roomList" backwards and deleting old ones.
        for (int i = roomList.size() - 1; i >= 0; i--) {
            for (Integer integer : oldRoomEntryIndex) {
                if (integer == i) {
                    if (i < roomList.size() - 1) {
                        roomList.remove(i);
                    }
                }
            }
        }

        System.out.println("rum antal: "+roomList.size());
//        Clear old table JSON file from data.
        clearJsonFromData(roomJson);

//        Creating new updated JSON.
        for (Room r : roomList) {
            roomToJson.saveRoom(r);
        }
    }

    private void searchCorruptData() throws IOException {
        CorruptItemToJson corruptItemToJson = new CorruptItemToJson();
        boolean tableLoop = true;
        boolean roomLoop = true;
        File corruptTableJson = new File(jsonToClient.getResourceFile("corruptTables.json").toURI());
        File corruptRoomJson = new File(jsonToClient.getResourceFile("corruptRooms.json").toURI());
        List<Table> tableList = objectMapper.readValue(corruptTableJson, new TypeReference<>() {
        });
        List<Room> roomList = objectMapper.readValue(corruptRoomJson, new TypeReference<>() {
        });

        while (tableLoop) {
            if (tableList.size() > 5) {
                tableList.remove(0);
            } else {
                tableLoop = false;
            }
        }
        while (roomLoop) {
            if (roomList.size() > 5) {
                roomList.remove(0);
            } else {
                roomLoop = false;
            }
        }
        clearJsonFromData(corruptTableJson);
        clearJsonFromData(corruptRoomJson);

        for (Table t : tableList) {
            corruptItemToJson.saveCourruptTable(t);
        }
        for (Room r : roomList) {
            corruptItemToJson.saveCorruptRoom(r);
        }
        CorruptItemToJson.setErrorMessage("\"ok\"");
    }

    public void clearJsonFromData(File Json) {
        try (PrintWriter pw = new PrintWriter(Json)) {
            pw.print("[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}