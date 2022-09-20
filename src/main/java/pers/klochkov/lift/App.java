package pers.klochkov.lift;

import pers.klochkov.lift.building.BuildingGenerator;
import pers.klochkov.lift.building.Building;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Building building = BuildingGenerator.generateBuildingByChance(5, 20);
        building.floors = BuildingGenerator.generateFloors(building);
    }
}
