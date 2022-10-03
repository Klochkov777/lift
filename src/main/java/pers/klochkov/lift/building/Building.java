package pers.klochkov.lift.building;

import pers.klochkov.lift.reader.PropertiesBuildingReader;


import java.util.ArrayList;
import java.util.List;

public final class Building {
    private final int amountFloors;
    private List<Floor> floors;

    public Building() {
        PropertiesBuildingReader propertiesBuildingReader = new PropertiesBuildingReader();/////7&&&&&&&&&&&&
        int maxFloors = propertiesBuildingReader.getMaxFloor();
        int minFloors = propertiesBuildingReader.getMinFloor();
        amountFloors = randomNumber(minFloors, maxFloors);
        floors = generateFloors();
        setPeopleToFloors();
    }


    private List<Floor> generateFloors() {
        List<Floor> result = new ArrayList<Floor>();
        for (int i = 1; i <= amountFloors; i++) {
            Floor floor = new Floor(i);
            result.add(floor);
        }
        return result;
    }

    private static int randomNumber(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private void setPeopleToFloors() {
        int minDesiredFloor = floors.get(0).getNumberFloor();
        int maxDesiredFloor = amountFloors;
        floors.forEach(floor -> floor.generatePeopleForOneFloor(minDesiredFloor, maxDesiredFloor));
    }
}

