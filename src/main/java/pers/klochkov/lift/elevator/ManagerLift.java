package pers.klochkov.lift.elevator;

import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static pers.klochkov.lift.elevator.Condition.UP;


public class ManagerLift {

    private Lift lift;

    public ManagerLift(Lift lift) {
    this.lift = lift;
    }

    public Floor findClosestFloorWithPeopleWhenLiftEmpty() throws IOException {
        List<Floor> floors = lift.building.getFloors();
        Floor result;
        List<Floor> floors1;
            floors1 = floors.stream().filter(floor -> !floor.people.isEmpty()).collect(Collectors.toList());
            result = findClosestFloorWhenLiftEmpty(floors1, lift);
        return result;
    }

    private Floor findClosestFloorWhenLiftEmpty(List<Floor> floors1, Lift lift){
        Floor result = null;
        if (floors1.isEmpty()) return null;
        int differenceBetweenFloors = Integer.MAX_VALUE;
        for (Floor floor : floors1) {
            int differenceBetweenFloorsNow = Math.abs(lift.getNumberFloor() - floor.getNumberFloor());
            if (differenceBetweenFloorsNow < differenceBetweenFloors) {
                differenceBetweenFloors = differenceBetweenFloorsNow;
                result = floor;
            }
        }
        return result;
    }


    public Floor findFloorForPickUp () {
        List<Floor> floors = lift.building.getFloors();
        PriorityQueue<Person> priorityQueue = lift.getLoader().getPriorityQueue();
        if (lift.getCondition()== UP) {
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

        public int findNumberFloorForUnload(){
        return lift.getLoader().getPriorityQueue().peek().getDesiredFloor();
    }
}






