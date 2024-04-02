package com.example.officebooking.jsonStuff;

import com.example.officebooking.Admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AdminToJson {
    public static String credentialsErrorMessage = "\"ok\"";
    private boolean oldLoginInfo = false;
    private boolean newPasswordInfo = false;
    private boolean newLoginInfo = false;
    private ObjectMapper objectMapper = new ObjectMapper();
    private JsonToClient jsonToClient = new JsonToClient();
    private Admin admin = new Admin();

    public boolean checkAdmin(String oldUsername, String newUsername, String oldPassword, String newPassword, String newPassword2) throws IOException {

        String adminString = jsonToClient.getAdminData();
        admin = objectMapper.readValue(adminString, Admin.class);

        if (admin.getUsername().equals(oldUsername) && admin.getPassword().equals(oldPassword)) {
            oldLoginInfo = true;
        }
        if (oldLoginInfo) {
            if (newPassword.equals(newPassword2)) {
                newPasswordInfo = true;
            }
        }
        if (oldLoginInfo && newPasswordInfo) {
            admin.setUsername(newUsername);
            admin.setPassword(newPassword);
            System.out.println("Nya: "+admin);
            saveAdmin(admin);
            newLoginInfo = true;
        }
        return newLoginInfo;
    }

    private void saveAdmin(Admin admin) throws IOException {
//        DeleteOldEntries oldEntries = new DeleteOldEntries();
//        oldEntries.clearJsonFromData(new File(FileFinder.getPathToAdminJson()));
        JsonToClient jsonToClient = new JsonToClient();

        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(jsonToClient.getResourceFile("admin.json").getPath());
        final String currentJsonArrayAsString = Files.readString(path);
        try (FileWriter fileWriter = new FileWriter(path.toFile(), false)) {

//            ----------------
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(admin);
            System.out.println("Json sträng för admin som ska sparas: " + jsonInString);
//            ------------------

            JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(admin));
            JSONArray jsonArray = new JSONArray(currentJsonArrayAsString);
            jsonArray.clear();
            jsonArray.put(jsonObject);
            fileWriter.write(jsonArray.toString());
        }
    }

    public static String getCredentialsErrorMessage() {
        return credentialsErrorMessage;
    }

    public static void setCredentialsErrorMessage(String credentialsErrorMessage) {
        AdminToJson.credentialsErrorMessage = credentialsErrorMessage;
    }

    public boolean isNewLoginInfo() {
        return newLoginInfo;
    }

    public void setNewLoginInfo(boolean newLoginInfo) {
        this.newLoginInfo = newLoginInfo;
    }
}
