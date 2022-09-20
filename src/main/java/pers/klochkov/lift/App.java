package pers.klochkov.lift;

import pers.klochkov.lift.building.BuilderException;
import pers.klochkov.lift.building.BuildingGenerator;
import pers.klochkov.lift.building.Building;


import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws BuilderException {
        Building building = BuildingGenerator.generateBuildingByChance(5, 20);
        building.floors = BuildingGenerator.generateFloors(building);
        BuildingGenerator.setPeopleToFloors(building,0,10);
    }
}
