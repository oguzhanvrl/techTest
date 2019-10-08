package com.trendyol.gui.test.base.properties;

import java.io.*;
import java.util.*;

public class PropertiesFile {

    private static PropertiesFile instance = null;

    private final String PROP_FILE_NAME  = "config.properties";
    private Properties properties = new Properties(){
        /* properties file does not save PropertiesData sequentially
            so added synch method to store in prop file
        */
        @Override
        public synchronized Enumeration<Object> keys() {
            return Collections.enumeration(new TreeSet<>(super.keySet()));
        }
    };
    private InputStream inputStream;
    private OutputStream outputStream;

    private PropertiesFile(){
        try {
            inputStream = PropertiesFile.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);
            if(inputStream != null){
                properties.load(inputStream);
            }else{
                createPropertiesFile();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static PropertiesFile getInstance(){
        if(instance == null){
            return instance = new PropertiesFile();
        }else return instance;
    }

    public String getProperty(PropertiesData data){
        return properties.getProperty(String.valueOf(data));
    }

    private void createPropertiesFile(){
        try {
            outputStream = new FileOutputStream("./target/config/" + PROP_FILE_NAME);

            // writes all enum data from PropertiesData to config.properties
            for(PropertiesData data : PropertiesData.values()){
                properties.setProperty(String.valueOf(data), "");
            }

            properties.store(outputStream, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
