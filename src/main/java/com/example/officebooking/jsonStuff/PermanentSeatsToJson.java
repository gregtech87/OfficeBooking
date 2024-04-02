package com.example.officebooking.jsonStuff;


import com.example.officebooking.Table;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PermanentSeatsToJson {

    public PermanentSeatsToJson() {
    }

    public void saveToJsonFile(String seats) throws IOException {
//        Writes received json string into file.
        System.out.println(seats);
        JsonToClient jsonToClient = new JsonToClient();
        Table table = new Table();
        Path path = Paths.get(jsonToClient.getResourceFile("permanentSeats.json").toURI());
        ObjectMapper objectMapper = new ObjectMapper();

        try (FileWriter fileWriter = new FileWriter(path.toFile(), false)) {
//            ----------------
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(seats);
            System.out.println("Json sträng för bord som ska sparas: " + jsonInString);
//            ------------------

            fileWriter.write(seats);
//
//            JSONArray jsonArray = new JSONArray(seats);
//            System.out.println(jsonArray);
//            jsonArray.clear();
//            System.out.println(jsonArray);
//            jsonArray.put(seats);
//            System.out.println(jsonArray);
//            fileWriter.write(jsonArray.toString());
        }
    }
}