package pers.klochkov.lift.building;

import java.util.ArrayList;
import java.util.List;

public class BuildingGenerator {
    public static Building generateBuildingByChance(int minCountFloor, int maxCountFloor){
        return new Building(randomNumber(minCountFloor, maxCountFloor));
    }

    public static List<Floor> generateFloors(Building building){
        List<Floor> result = new ArrayList<Floor>();
        for (int i = 1; i <= building.COUNT_FLOOR; i++) {
            Floor floor = new Floor(i);
            result.add(floor);
        }
        return result;
    }

    // change to my own exception extended from IOException
    private static List<Person> generatePeopleForOneFloor(Building building, Floor floor, int minCountPerson, int maxCountPerson){
        if (building.floors.isEmpty()){
            throw new RuntimeException("No floors inside building. At first must be created floors and only after that people");
        }
        List<Person> result = new ArrayList<Person>();
        int countPerson = randomNumber(minCountPerson, maxCountPerson);
        for (int i = 0; i <= countPerson; i++) {
            Person person = new Person();

        }
        return result;
    }

    private static Person generatePerson(Building building, Floor floor){
        Person result = new Person();
        List<Floor> floors = building.floors;
        result.setHisFloor(floor.getNumberFloor());
        result.setDesiredFloor(randomNumberExceptNumber(floors.get(0).getNumberFloor(),
                floors.get(floors.size() - 1).getNumberFloor(),floor.getNumberFloor()));
        return result;
    }

    private static int randomNumberExceptNumber(int min, int max, int exceptNumber){
        int result;
        while (true){
            if ((result = randomNumber(min, max)) != exceptNumber) return result;
        }
    }

    private static int randomNumber(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}