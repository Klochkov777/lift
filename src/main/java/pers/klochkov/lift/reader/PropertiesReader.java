package pers.klochkov.lift.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertiesReader {
    public static final String CONFIG_NAME = "config.properties";
    public final Properties properties;

    protected PropertiesReader() {
        properties = new Properties();
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_NAME);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
