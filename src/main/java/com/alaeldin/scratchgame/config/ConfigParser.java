/**
 * Develop By Alaeldin Musa
 * Date 10/2/2024
 */

package com.alaeldin.scratchgame.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class ConfigParser {

    /**
     * Function Read File Json
     * @param pathFile String
     * @return Config
     */
    public static Config parseConfig(String pathFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(new File(pathFile), Config.class);
    }

}