package pers.klochkov.lift.reader;

import java.util.Properties;

public class PropertiesLiftReader extends PropertiesReader{

    private int startFloor;
    private int maxPerson;

    public PropertiesLiftReader() {
        startFloor = Integer.parseInt(properties.getProperty("lift.start_floor"));
        maxPerson = Integer.parseInt(properties.getProperty("lift.max_person"));
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public int getStartFloor() {
        return startFloor;
    }
}
