package com.company.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fip = new FileInputStream("src/test/resources/config/config.properties");
            properties.load(fip);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load configuration file ");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
