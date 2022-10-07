package pers.klochkov.lift;


import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.elevator.Lift;
import pers.klochkov.lift.elevator.LoaderLift;
import pers.klochkov.lift.elevator.ManagerLift;
import pers.klochkov.lift.elevator.Condition;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws IOException {
        Building building = new Building();
        ConsolePrinter consolePrinter = new ConsolePrinter(building);
        Lift lift = building.getLift();
        LoaderLift loaderLift = lift.getLoader();
        ManagerLift managerLift = lift.getManagerLift();

        while (true) {
            if (loaderLift.getPassengers().isEmpty()) {
                lift.setCondition(Condition.NOT_MOVE);
                Floor floor = managerLift.getClosestFloorLiftEmpty();
                if (floor == null) {
                    System.out.println("All people achieved their points destination");
                    return;
                }
                lift.setNumberFloor(floor.getNumberFloor());
                consolePrinter.printBuilding();
                lift.setCondition(lift.getConditionForLift());
            }
            loaderLift.loadLift();
            consolePrinter.printBuilding();
            while (loaderLift.getAmountPerson() != loaderLift.maxPerson) {
                Floor floor = managerLift.findFloorForPickUp();
                if (floor == null) {
                    break;}
                lift.setNumberFloor(floor.getNumberFloor());
                lift.getLoader().loadLift();
                consolePrinter.printBuilding();
            }

            int numberFloorUnload = managerLift.findNumberFloorForUnload();
            lift.setNumberFloor(numberFloorUnload);
            consolePrinter.printBuilding();
            loaderLift.unload();
            consolePrinter.printBuilding();
        }
    }
}
