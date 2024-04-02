package com.example.seatingchartbooking.jsonStuff;

import com.example.seatingchartbooking.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TableToJson {

    public TableToJson() {
    }

    public void saveTable(Table table) throws IOException {
        JsonToClient jsonToClient = new JsonToClient();

        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(jsonToClient.getResourceFile("tables.json").getPath());
        final String currentJsonArrayAsString = Files.readString(path);
        try (FileWriter fileWriter = new FileWriter(path.toFile(), false)) {

//            ----------------
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(table);
            System.out.println("Json sträng för bord som ska sparas: " + jsonInString);
//            ------------------

            JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(table));
            JSONArray jsonArray = new JSONArray(currentJsonArrayAsString);
            jsonArray.put(jsonObject);
            fileWriter.write(jsonArray.toString());
        }
    }
}