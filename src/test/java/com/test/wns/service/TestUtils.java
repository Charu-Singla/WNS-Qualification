package com.test.wns.service;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Provides utility methods for testing.
 *
 * @author charusingla
 */

public class TestUtils {

    /**
     * Gets the json string from file.
     *
     * @param testInput the test input
     * @return the json string from file
     */
    public static String getStringFromFile(String testInput) {

        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(testInput).toURI())));
        } catch (IOException | URISyntaxException e) {
        }
        return content;
    }
    
 
}
