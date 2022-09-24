package pers.klochkov.lift;


import pers.klochkov.lift.building.BuildingGenerator;
import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.lift.Lift;
import pers.klochkov.lift.lift.LoaderLift;
import pers.klochkov.lift.lift.ManagerLift;
import pers.klochkov.lift.prog.Condition;


import java.io.IOException;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) throws IOException {
        Building building = BuildingGenerator.generateBuildingByChance(5, 20);
        building.floors = BuildingGenerator.generateFloors(building);
        BuildingGenerator.setPeopleToFloors(building, 0, 10);
        BuildingGenerator.setDequeForAllFloors(building);
        Lift lift = new Lift(new LoaderLift(5));
        OutPrinter outPrinter = new OutPrinter();
        outPrinter.printBuilding(building.floors, lift);


        ManagerLift managerLift = new ManagerLift();
        while (true) {
            if (lift.loader.peoplePeopleInsideLift.isEmpty()) {
                lift.setCondition(Condition.NOT_MOVE);
                managerLift.moveClosestFloorWithPeopleAndLiftEmpty(building.floors, lift);
            }
            lift.loader.loadLift(building.floors.get(lift.getNumberFloor() - 1), lift, building);
            outPrinter.printBuilding(building.floors, lift);

            PriorityQueue<Person> personInLift = managerLift.getPriorityQueuePersonInLift(lift);
            managerLift.moveToFloorForPassenger(personInLift, lift);
            lift.loader.unload(personInLift, lift);
            outPrinter.printBuilding(building.floors, lift);
        }
    }
}
