package pers.klochkov.lift;


import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.elevator.Lift;
import pers.klochkov.lift.elevator.LoaderLift;
import pers.klochkov.lift.elevator.ManagerLift;
import pers.klochkov.lift.prog.Condition;
import java.io.IOException;



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
            int numberFloorUnload = managerLift.findNumberFloorForUnload();
            lift.setNumberFloor(numberFloorUnload);
            outPrinter.printBuilding();
            loaderLift.unload();
            outPrinter.printBuilding();
        }
    }
}
