package pers.klochkov.lift;


import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
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
        Building building = new Building();
        OutPrinter outPrinter = new OutPrinter(building);
        Lift lift = building.getLift();
        LoaderLift loaderLift = lift.getLoader();
        ManagerLift managerLift = lift.getManagerLift();


        while (true) {
            if (loaderLift.getPriorityQueue().isEmpty()) {
                lift.setCondition(Condition.NOT_MOVE);
                Floor floor = managerLift.findClosestFloorWithPeopleWhenLiftEmpty();
                if (floor == null){
                    System.out.println("All people achieved their points destination");
                    return;
                }
                lift.setNumberFloor(floor.getNumberFloor());
                outPrinter.printBuilding();
                lift.setCondition(lift.getConditionForLift());
//                loaderLift.loadLift();
//                outPrinter.printBuilding();
            }
            loaderLift.loadLift();
            outPrinter.printBuilding();
            if (loaderLift.getAmountPerson() != loaderLift.maxPerson){
                while (loaderLift.getAmountPerson() != loaderLift.maxPerson){
                    Floor floor = managerLift.findFloorOnTheWay();
                    if (floor == null) break;
                    lift.setNumberFloor(floor.getNumberFloor());
                    lift.getLoader().loadLift();
                    outPrinter.printBuilding();
                }
            }

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
}
