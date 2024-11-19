package com.dme.DormitoryProject.statusCode;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class JsonFileReader {
    private static Map<String, Map<String, String>> statusCodes;

    static {
        try {
            // Load status codes from the JSON file
            ClassLoader classLoader = JsonFileReader.class.getClassLoader();
            URL resource = classLoader.getResource("statusCode.json");

            if (resource == null) {
                throw new IllegalArgumentException("JSON file not found!");
            }

            File jsonFile = new File(resource.getFile());
            ObjectMapper objectMapper = new ObjectMapper();
            statusCodes = objectMapper.readValue(jsonFile, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String statusCode, String lang) {
        // Get the message based on status code and language
        if (statusCodes.containsKey(statusCode) && statusCodes.get(statusCode).containsKey(lang)) {
            return statusCodes.get(statusCode).get(lang);
        } else {
            return "Message not found";  // Default message in case of error
        }
    }
}
