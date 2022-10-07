package pers.klochkov.lift.building;

import pers.klochkov.lift.elevator.Lift;
import pers.klochkov.lift.reader.PropertiesBuildingReader;


import java.util.ArrayList;
import java.util.List;

public final class Building {
    private final int amountFloors;
    private List<Floor> floors;
    private final int numberFirsFloor;
    private final int numberLastFloor;
    private Lift lift;

    //Получился большой конструктор, который не слишком гибкий возможно (может я и не прав). Насколько это неправильно? Или он норм?
    //Его написание сильно сократило код в других частях.
    public Building() {
        PropertiesBuildingReader propertiesBuildingReader = new PropertiesBuildingReader();
        int maxFloors = propertiesBuildingReader.getMaxFloor();
        int minFloors = propertiesBuildingReader.getMinFloor();
        amountFloors = randomNumber(minFloors, maxFloors);
        floors = generateFloors();
        setPeopleToFloors();
        numberFirsFloor = floors.get(0).getNumberFloor();
        numberLastFloor = floors.get(floors.size() - 1).getNumberFloor();
        lift = new Lift(this);
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

    public List<Floor> getFloors() {
        return floors;
    }

    public int getNumberFirsFloor() {
        return numberFirsFloor;
    }

    public int getNumberLastFloor() {
        return numberLastFloor;
    }

    public Lift getLift() {
        return lift;
    }
}

