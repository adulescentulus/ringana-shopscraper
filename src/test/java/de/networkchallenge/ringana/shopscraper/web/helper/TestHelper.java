package de.networkchallenge.ringana.shopscraper.web.helper;

import java.util.Scanner;

public class TestHelper {
    public static String readResource(String path) {
        try (Scanner scanner = new Scanner(TestHelper.class.getResourceAsStream(path), "UTF-8")) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
