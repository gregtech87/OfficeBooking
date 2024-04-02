package com.example.officebooking.jsonStuff;

import com.example.officebooking.Room;
import com.example.officebooking.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class CorruptItemToJson {

    LocalDateTime localDateTime = LocalDateTime.now();
    public static String errorMessage = "\"ok\"";

    public void saveCourruptTable(Table table) throws IOException {
        table.setLatestEdit(String.valueOf(localDateTime));

        JsonToClient jsonToClient = new JsonToClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(jsonToClient.getResourceFile("corruptTables.json").getPath());
        final String currentJsonArrayAsString = Files.readString(path);
        try (FileWriter fileWriter = new FileWriter(path.toFile(), false)) {

//            ----------------
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(table);
            System.out.println("Korrupt bord som ska sparas: " + jsonInString);
//            ------------------

            JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(table));
            JSONArray jsonArray = new JSONArray(currentJsonArrayAsString);
            jsonArray.put(jsonObject);
            fileWriter.write(jsonArray.toString());
            errorMessage = "\"Not ok\"";
        }
    }

    public void saveCorruptRoom(Room room) throws IOException {
        room.setLatestEdit(String.valueOf(localDateTime));

        JsonToClient jsonToClient = new JsonToClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(jsonToClient.getResourceFile("corruptRooms.json").getPath());
        final String currentJsonArrayAsString = Files.readString(path);
        try (FileWriter fileWriter = new FileWriter(path.toFile(), false)) {

//            ----------------
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(room);
            System.out.println("Korrupt rum som ska sparas: " + jsonInString);
//            ----------------

            JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(room));
            JSONArray jsonArray = new JSONArray(currentJsonArrayAsString);
            jsonArray.put(jsonObject);
            fileWriter.write(jsonArray.toString());
            errorMessage = "'Not ok'";
        }
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        CorruptItemToJson.errorMessage = errorMessage;
    }
}