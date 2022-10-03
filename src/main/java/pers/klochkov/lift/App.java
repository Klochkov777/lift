package pers.klochkov.lift;


import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.elevator.Lift;
import pers.klochkov.lift.elevator.LoaderLift;
import pers.klochkov.lift.elevator.ManagerLift;
import pers.klochkov.lift.prog.Condition;
import pers.klochkov.lift.reader.PropertiesBuildingReader;
import pers.klochkov.lift.reader.PropertiesFloorReader;
import pers.klochkov.lift.reader.PropertiesLiftReader;


import java.io.IOException;
import java.util.PriorityQueue;


public class App {
    public static void main(String[] args) throws IOException {
        PropertiesBuildingReader propertiesBuild = new PropertiesBuildingReader();
        int maxFloor = propertiesBuild.getMaxFloor();
        int minFloor = propertiesBuild.getMinFloor();
        Building building = BuildingGenerator.generateBuildingByChance(minFloor, maxFloor);
        building.floors = BuildingGenerator.generateFloors(building);
        PropertiesFloorReader propertiesFloor = new PropertiesFloorReader();
        int minPeople = propertiesFloor.getMinPeople();
        int maxPeople = propertiesFloor.getMaxPeople();
        BuildingGenerator.setPeopleToFloors(building, minPeople, maxPeople);
        BuildingGenerator.setDequeForAllFloors(building);
        PropertiesLiftReader propertiesLift = new PropertiesLiftReader();
        Lift lift = new Lift(new LoaderLift(maxPersonInLift));
        //ManagerLift managerLift = new ManagerLift();
        OutPrinter outPrinter = new OutPrinter();

//        System.out.println("START");
//        outPrinter.printBuilding(building.floors, lift);
//        while (true) {
//            if (lift.loader.peoplePeopleInsideLift.isEmpty()) {
//                lift.setCondition(Condition.NOT_MOVE);
//                managerLift.moveClosestFloorWithPeopleWhenLiftEmpty(building.floors, lift);
//            }
//            lift.loader.loadLift(building.floors.get(lift.getNumberFloor() - 1), lift, building);
//            outPrinter.printBuilding(building.floors, lift);
//            PriorityQueue<Person> peopleInLift = managerLift.getPriorityQueuePersonInLift(lift);
//            if (lift.loader.getAmountPerson() != lift.loader.maxPerson) {
//                managerLift.moveToFloorOnTheWay(building, lift, peopleInLift, outPrinter);
//            }
//            peopleInLift = managerLift.getPriorityQueuePersonInLift(lift);
//            managerLift.moveToFloorForPassenger(peopleInLift, lift);
//            lift.loader.unload(peopleInLift, lift);
//            lift.loader.loadLift(building.floors.get(lift.getNumberFloor() - 1), lift, building);
//            outPrinter.printBuilding(building.floors, lift);
//        }
    }

}
