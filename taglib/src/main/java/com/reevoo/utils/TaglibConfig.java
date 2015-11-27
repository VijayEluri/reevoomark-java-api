package com.reevoo.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class TaglibConfig {

    private static Properties properties = loadProperties();

    private static String multitrkrefMarkloaderScript = loadResourceFile("multitrkrefMarkloader.script");

    private static String singletrkrefMarkloaderScript = loadResourceFile("singletrkrefMarkloader.script");

    private static String propensityToBuyScript = loadResourceFile("propensityToBuyTracking.script");

    private static String purchaseTrackingScript = loadResourceFile("purchaseTracking.script");

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

    private static String loadResourceFile(String filename) {
        BufferedReader reader = null;
        StringBuilder contentBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(TaglibConfig.class.getClassLoader().getResourceAsStream(filename)));
            String line = reader.readLine();
            while (line != null) {
                contentBuilder.append(line).append("\n");
                line = reader.readLine();
            }

        } catch (Exception e) {
            throw new RuntimeException(String.format("Unable to load resource file [%s]", filename), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return contentBuilder.toString();
    }

    public static String getProperty(String name) {

        String value = properties.getProperty(name);
        if (value == null) {
            value = "";
        }
        return value;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperty(String name, String value) {
        properties.setProperty(name, value);
    }

    public static String getMultitrkrefMarkloaderScript() {
        return multitrkrefMarkloaderScript;
    }

    public static String getSingletrkrefMarkloaderScript() {
        return singletrkrefMarkloaderScript;
    }

    public static String getPropensityToBuyScript() {
        return propensityToBuyScript;
    }

    public static String getPurchaseTrackingScript() {
        return purchaseTrackingScript;
    }

}
