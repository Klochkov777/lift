package pers.klochkov.lift.reader;

public class PropertiesFloorReader extends PropertiesReader{

    private int minPeople;
    private int maxPeople;
    public PropertiesFloorReader(){
        minPeople = Integer.parseInt(properties.getProperty("floor.min_people"));
        maxPeople = Integer.parseInt(properties.getProperty("floor.max_people"));
    }

    public int getMinPeople() {
        return minPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }
}
