package com.example.seatingchartbooking;

import com.example.seatingchartbooking.jsonStuff.CorruptItemToJson;
import com.example.seatingchartbooking.jsonStuff.RoomToJson;
import com.example.seatingchartbooking.jsonStuff.TableToJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    TableToJson tableToJson = new TableToJson();
    RoomToJson roomToJson = new RoomToJson();

    List<Boolean> tableList = new ArrayList<>();
    List<Boolean> roomList = new ArrayList<>();

    public Validator() {
    }

    public void validateItem(Table table, Room room) throws IOException {
        // Inspect all values before storing.
        if (table.getName() != null) {
            tableList.add(tester(table.getName()));
            tableList.add(tester(String.valueOf(table.getTableId())));
            tableList.add(tester(table.getDateOfBooking()));
            tableList.add(tester(table.getBeforeLunch()));
            tableList.add(tester(table.getAfterLunch()));
            tableList.add(tester(table.getFullDay()));
            tableList.add(tester(table.getPermanentPlace()));
        }
        if (room.getName() != null) {
            roomList.add(tester(room.getName()));
            roomList.add(tester(room.getRoomId()));
            roomList.add(tester(room.getDateOfBooking()));
            roomList.add(tester(room.getStartTime()));
            roomList.add(tester(room.getEndTime()));
            roomList.add(tester(room.getFullDay()));
        }
        checkIfApproved(table, room);
    }

    private boolean tester(String item) {
        // Generate list of safe characters.
        List<Character> approvedCharacterList = new ArrayList<>();
        // Numbers 0-9.
        for (char c = 48; c <= 57; c++) {
            approvedCharacterList.add(c);
        }
        // Capital letters A-Z
        for (char c = 65; c <= 90; c++) {
            approvedCharacterList.add(c);
        }
        // Small letters a-z
        for (char c = 97; c <= 122; c++) {
            approvedCharacterList.add(c);
        }
        // Special characters.
        String goodSymbols = "ÀàÁáÂâÃãÄäÅåÆæÇçÈèÉéÊêËëÌìÍíÎîÏïÐðÑñÒòÓóÔôÕõÖöØøÙùšÚúÛûœÜüÝýžŸßÿ :-";
        for (int i = 0; i < goodSymbols.length(); i++) {
            approvedCharacterList.add(goodSymbols.charAt(i));
        }

        // inspect given string.
        if (item != null) {
            for (int i = 0; i < item.length(); i++) {
                char ch = item.charAt(i);
                boolean check = false;
                for (char c : approvedCharacterList) {
                    if (c == ch) {
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkIfApproved(Table table, Room room) throws IOException {
//        Goes through boolean list to see if all values is approved.
        boolean saveableTable = true;
        boolean saveableRoom = true;
        System.out.println("Bord: " + tableList);
        System.out.println("Rum: " + roomList);

        for (boolean b : tableList) {
            if (!b) {
                System.out.println("Korrupt bord");
                new CorruptItemToJson().saveCourruptTable(table);
                saveableTable = false;
            }
        }
        for (boolean b : roomList) {
            if (!b) {
                System.out.println("korrupt rum");
                new CorruptItemToJson().saveCorruptRoom(room);
                saveableRoom = false;
            }
        }
        if (saveableTable && table.getName() != null) {
            tableToJson.saveTable(table);
        }
        if (saveableRoom && room.getName() != null) {
            roomToJson.saveRoom(room);
        }
    }
}