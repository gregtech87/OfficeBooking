package com.example.seatingchartbooking.jsonStuff;

import com.example.seatingchartbooking.Admin;
import com.example.seatingchartbooking.Room;
import com.example.seatingchartbooking.Table;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonToClient {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonToClient() {
    }

    public String getAdminData() throws IOException {
//        Admin to console
        File adminJson = new File(getResourceFile("admin.json").toURI());
        Admin[] admin = objectMapper.readValue(adminJson, Admin[].class);
        String adminString =  objectMapper.writeValueAsString(admin);
        String adminString2 = adminString.substring(1,adminString.length()-1);
        return adminString2;
    }
    public String getCorruptRoomData() throws IOException {
//        CorruptRoomData to console
        File corruptRoomJson = new File(getResourceFile("corruptRooms.json").toURI());
        Room[] rooms = objectMapper.readValue(corruptRoomJson, Room[].class);
        return objectMapper.writeValueAsString(rooms);
    }
    public String getCorruptTableData() throws IOException {
//        CorruptTableData to console
        File CorruptTableJson = new File(getResourceFile("corruptTables.json").toURI());
        Table[] tables = objectMapper.readValue(CorruptTableJson, Table[].class);
        return objectMapper.writeValueAsString(tables);
    }
    public String getpermanentSeatData() throws IOException {
//        permanentSeatJSON to console
        File permanentSeatJson = new File(getResourceFile("permanentSeats.json").toURI());
        Table[] tables = objectMapper.readValue(permanentSeatJson, Table[].class);
        return objectMapper.writeValueAsString(tables);
    }

    public String getTableData() throws IOException {
//        tableJSON to console
        File tablesJson = new File(getResourceFile("tables.json").toURI());
        Table[] tables = objectMapper.readValue(tablesJson, Table[].class);
        return objectMapper.writeValueAsString(tables);
    }

    public String getRoomData() throws IOException {
//        roomJSON to console
        File roomJson = new File(getResourceFile("rooms.json").toURI());
        Room[] rooms = objectMapper.readValue(roomJson, Room[].class);
        return objectMapper.writeValueAsString(rooms);
    }

    public File getResourceFile(String fileName)
    {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(fileName);

        if(url == null) {
            throw new IllegalArgumentException(fileName + " is not found 1");
        }

        File file = new File(url.getFile());

        return file;
    }
}