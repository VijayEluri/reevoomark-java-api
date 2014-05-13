package com.reevoo.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TaglibConfig {

    private static Properties properties = loadProperties();


    private static Properties loadProperties() {

        Properties properties = new Properties();

        // load the internal default properties.
        try {
            InputStream configValues = TaglibConfig.class.getClassLoader().getResourceAsStream("taglibConfig.properties");
            if (configValues != null) {
                properties.load(configValues);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to load configuration properties for file [%s]", "tablibConfig.properties"), e);
        }

        // look if the client has defined their own properties and if so override the default internal values with any values provided by the customer.
        try {
            InputStream configValues = TaglibConfig.class.getClassLoader().getResourceAsStream("reevooTaglibConfig.properties");
            if (configValues != null) {
                properties.load(configValues);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to load configuration properties for file [%s]", "reevooTablibConfig.properties"), e);
        }
        return properties;
    }


    public static String getProperty(String name) {
        String value = properties.getProperty(name);
        if (value == null) {
            value = "";
        }
        return value;
    }


}
