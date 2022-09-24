package pers.klochkov.lift.lift;

import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.prog.Condition;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



public class ManagerLift {

    public Floor findClosestFloorWithPeople(List<Floor> floors, Lift lift) throws IOException {
        Floor result = null;
        if (lift.getCondition().equals(Condition.NOT_MOVE)) {
            List<Floor> floors1;
            floors1 = floors.stream().filter(floor -> !floor.people.isEmpty()).collect(Collectors.toList());
            int differenceBetweenFloors = 0;
            for (Floor floor : floors1) {
               int differenceBetweenFloorsNow = Math.abs(lift.getNumberFloor()) - floor.getNumberFloor();
                if (differenceBetweenFloorsNow < differenceBetweenFloors) {
                    differenceBetweenFloors = differenceBetweenFloorsNow;
                    result = floor;
                }
            }
        }
        else throw new IOException("lift had be stopped after unload");
        return result;

    }





}
