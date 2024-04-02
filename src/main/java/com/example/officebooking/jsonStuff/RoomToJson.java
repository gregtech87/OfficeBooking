package com.example.officebooking.jsonStuff;

import com.example.officebooking.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RoomToJson {

    public RoomToJson() {
    }

    public void saveRoom(Room room) throws IOException {
        JsonToClient jsonToClient = new JsonToClient();

        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(jsonToClient.getResourceFile("rooms.json").getPath());
        final String currentJsonArrayAsString = Files.readString(path);
        try (FileWriter fileWriter = new FileWriter(path.toFile(), false)) {

//            ----------------
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(room);
            System.out.println("Json sträng för rum som ska sparas: " + jsonInString);
//            ----------------

            JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(room));
            JSONArray jsonArray = new JSONArray(currentJsonArrayAsString);
            jsonArray.put(jsonObject);
            fileWriter.write(jsonArray.toString());
        }
    }
}