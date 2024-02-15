package com.alaeldin.scratchgame.config;

import com.alaeldin.Main;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.stream.Collectors;

public class ConfigParser {

    public static Config parseConfig(String pathFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(pathFile), Config.class);
    }

}
