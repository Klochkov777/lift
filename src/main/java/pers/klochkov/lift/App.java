package pers.klochkov.lift;

import pers.klochkov.lift.building.BuilderException;
import pers.klochkov.lift.building.BuildingGenerator;
import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.lift.Lift;

public class App {
    public static void main(String[] args) throws BuilderException {
        Building building = BuildingGenerator.generateBuildingByChance(5, 20);
        building.floors = BuildingGenerator.generateFloors(building);
        BuildingGenerator.setPeopleToFloors(building, 0, 10);
        BuildingGenerator.setDequeForAllFloors(building);
        Lift lift = new Lift();
        OutPrinter outPrinter = new OutPrinter();
        outPrinter.printBuilding(building.floors, lift);

    }
}
