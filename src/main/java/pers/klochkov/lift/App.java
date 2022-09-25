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
        outPrinter.printBuilding(building.floors, lift);


        ManagerLift managerLift = new ManagerLift();
        while (true) {
            if (lift.loader.peoplePeopleInsideLift.isEmpty()) {
                lift.setCondition(Condition.NOT_MOVE);
                managerLift.moveClosestFloorWithPeopleAndLiftEmpty(building.floors, lift);
            }
            lift.loader.loadLift(building.floors.get(lift.getNumberFloor() - 1), lift, building);//////////////
            outPrinter.printBuilding(building.floors, lift);////////////
            PriorityQueue<Person> peopleInLift = managerLift.getPriorityQueuePersonInLift(lift);
            if (lift.loader.getAmountPerson() != 5) {
                while (true) {
                    Floor floor = moveToFloorOnTheWay(peopleInLift, lift, building.floors, building, outPrinter);
                    if (floor == null) break;
                }
            }

//            lift.loader.loadLift(building.floors.get(lift.getNumberFloor() - 1), lift, building);
//            outPrinter.printBuilding(building.floors, lift);

           peopleInLift = managerLift.getPriorityQueuePersonInLift(lift);

            //moveToFloorOnTheWay(peopleInLift, lift, building.floors, building, outPrinter);

            managerLift.moveToFloorForPassenger(peopleInLift, lift);
            lift.loader.unload(peopleInLift, lift);
            lift.loader.loadLift(building.floors.get(lift.getNumberFloor() - 1), lift, building);//?????????
            outPrinter.printBuilding(building.floors, lift);
        }
    }



    private static Floor moveToFloorOnTheWay(PriorityQueue<Person> priorityQueue, Lift lift, List<Floor> floors, Building building, OutPrinter outPrinter) {
        System.out.println("Enter");
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
        } else {System.out.println("null"); return null;
            }
        if (floors.isEmpty()) return null;
        lift.setNumberFloor(floors.get(0).getNumberFloor());
        lift.loader.loadLift(floors.get(0), lift, building);
        outPrinter.printBuilding(floors1, lift);
        System.out.println("Exit");
        return floors.get(0);

    }


}
