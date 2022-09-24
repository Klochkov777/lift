package pers.klochkov.lift.lift;

import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.prog.Condition;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class ManagerLift {

    public void moveClosestFloorWithPeopleAndLiftEmpty(List<Floor> floors, Lift lift) throws IOException {
        Floor result;
        List<Floor> floors1;
        floors1 = floors.stream().filter(floor -> !floor.people.isEmpty()).collect(Collectors.toList());
            result = findClosestFloorWithPeopleAndLiftEmpty(floors1, lift);
        if (result == null) {
            System.out.println("All people achieved their destination");
            System.exit(0);  // Так будет правильно?????????????????????????????
        }
        lift.setNumberFloor(result.getNumberFloor());
    }

    private Floor findClosestFloorWithPeopleAndLiftEmpty(List<Floor> floors1, Lift lift){
        Floor result = null;
        int differenceBetweenFloors = lift.getNumberFloor();
        for (Floor floor : floors1) {
            int differenceBetweenFloorsNow = Math.abs(lift.getNumberFloor() - floor.getNumberFloor());
            if (differenceBetweenFloorsNow < differenceBetweenFloors) {
                differenceBetweenFloors = differenceBetweenFloorsNow;
                result = floor;
            }
        }
        return result;
    }

}






