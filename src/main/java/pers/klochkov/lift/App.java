package pers.klochkov.lift;


import pers.klochkov.lift.building.BuildingGenerator;
import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.lift.Lift;
import pers.klochkov.lift.lift.LoaderLift;
import pers.klochkov.lift.lift.ManagerLift;
import pers.klochkov.lift.prog.Condition;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
        Building building = BuildingGenerator.generateBuildingByChance(10, 12);
        building.floors = BuildingGenerator.generateFloors(building);
        BuildingGenerator.setPeopleToFloors(building, 0, 10);
        BuildingGenerator.setDequeForAllFloors(building);
        Lift lift = new Lift(new LoaderLift(5));
        OutPrinter outPrinter = new OutPrinter();
        System.out.println("START");
        outPrinter.printBuilding(building.floors, lift);


        ManagerLift managerLift = new ManagerLift();
        while (true) {
            if (lift.loader.peoplePeopleInsideLift.isEmpty()) {
                lift.setCondition(Condition.NOT_MOVE);
                managerLift.moveClosestFloorWithPeopleWhenLiftEmpty(building.floors, lift);
            }
            lift.loader.loadLift(building.floors.get(lift.getNumberFloor() - 1), lift, building);//////////////
            outPrinter.printBuilding(building.floors, lift);////////////
            PriorityQueue<Person> peopleInLift = managerLift.getPriorityQueuePersonInLift(lift);
            if (lift.loader.getAmountPerson() != 5) {
                while (true) {
                    if (lift.loader.getAmountPerson() == lift.loader.maxPerson) break;
                    Floor floor = findFloorOnTheWay(peopleInLift, lift, building.floors, building, outPrinter);
                    if (floor == null) break;
                    lift.setNumberFloor(floor.getNumberFloor());
                    lift.loader.loadLift(floor, lift, building);
                    outPrinter.printBuilding(building.floors, lift);
                }
            }
            peopleInLift = managerLift.getPriorityQueuePersonInLift(lift);
            managerLift.moveToFloorForPassenger(peopleInLift, lift);
            lift.loader.unload(peopleInLift, lift);
            lift.loader.loadLift(building.floors.get(lift.getNumberFloor() - 1), lift, building);//?????????
            outPrinter.printBuilding(building.floors, lift);
        }
    }


//    private static Floor moveToFloorOnTheWay(PriorityQueue<Person> priorityQueue, Lift lift, List<Floor> floors, Building building, OutPrinter outPrinter) {
//        List<Floor> floors1 = new ArrayList<>(floors);
//        if (lift.getCondition().equals(Condition.UP)) {
//            floors = floors.stream()
//                    .filter(floor -> (floor.getNumberFloor() < priorityQueue.peek().getDesiredFloor() && floor.getNumberFloor() > lift.getNumberFloor()))
//                    .collect(Collectors.toList());
//        } else if (lift.getCondition().equals(Condition.DOWN)) {
//            floors = floors.stream()
//                    .filter(floor -> (floor.getNumberFloor() > priorityQueue.peek().getDesiredFloor() && floor.getNumberFloor() < lift.getNumberFloor()))
//                    .collect(Collectors.toList());
//            Collections.reverse(floors);
//        } else {
//            return null;
//        }
//        if (floors.isEmpty()) return null;
//        lift.setNumberFloor(floors.get(0).getNumberFloor());
//        lift.loader.loadLift(floors.get(0), lift, building);
//        outPrinter.printBuilding(floors1, lift);
//        return floors.get(0);
//
//    }

    private static void moveToFloorOnTheWay(){

    }


    private static Floor findFloorOnTheWay(PriorityQueue<Person> priorityQueue, Lift lift, List<Floor> floors, Building building, OutPrinter outPrinter) {
        List<Floor> floors1 = new ArrayList<>(floors);
        if (lift.getCondition().equals(Condition.UP)) {
            floors = floors.stream()
                    .filter(floor -> (floor.getNumberFloor() < priorityQueue.peek().getDesiredFloor() && floor.getNumberFloor() > lift.getNumberFloor()))
                    .collect(Collectors.toList());
        } else if (lift.getCondition().equals(Condition.DOWN)) {

            floors = floors.stream()
                    .filter(floor -> (floor.getNumberFloor() > priorityQueue.peek().getDesiredFloor() && floor.getNumberFloor() < lift.getNumberFloor()))
                    .collect(Collectors.toList());
            Collections.reverse(floors);
        } else {
            return null;
        }
        if (floors.isEmpty()) return null;
        return floors.get(0);
    }

}
