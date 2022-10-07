package pers.klochkov.lift.elevator;

import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;

import java.util.*;
import java.util.stream.Collectors;

import static pers.klochkov.lift.elevator.Condition.UP;


public class ManagerLift {

    private Lift lift;

    public ManagerLift(Lift lift) {
    this.lift = lift;
    }

    //метод получения ближайшего этажа с ожидающими пассажирами при пустом лифте
    public Floor getClosestFloorLiftEmpty() {
        List<Floor> floors = lift.building.getFloors();
        Floor result;
        List<Floor> floors1;
            floors1 = floors.stream().filter(floor -> !floor.people.isEmpty()).collect(Collectors.toList());
            result = findClosestFloorWhenLiftEmpty(floors1, lift);
        return result;
    }

    //метод нахождения ближайшего этажа с ожидающими пассажирами при пустом лифте (он необходим для getClosestFloorLiftEmpty())
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

//Этот метод для получения этажа на котором лифт должен подобрать пассажиров по пути.
//Сначала фильтрую этажи в здании на которых есть пассажиры и которым по пути с направлением лифта и эти этажи ближе
//чем высадка из лифта и затем беру ближайший этаж из этого списка.
    public Floor findFloorForPickUp () {
        List<Floor> floors = lift.building.getFloors();
        PriorityQueue<Person> passengersQueue = lift.getLoader().getPassengers();
        if (lift.getCondition()== UP) {
            floors = floors.stream()
                    .filter(floor -> (floor.getNumberFloor() < passengersQueue.peek().getDesiredFloor() && floor.getNumberFloor() > lift.getNumberFloor()))
                    .collect(Collectors.toList());
        } else if (lift.getCondition().equals(Condition.DOWN)) {
            floors = floors.stream()
                    .filter(floor -> (floor.getNumberFloor() > passengersQueue.peek().getDesiredFloor() && floor.getNumberFloor() < lift.getNumberFloor()))
                    .collect(Collectors.toList());
            Collections.reverse(floors);
        } else {
            return null;
        }
        if (floors.isEmpty()) return null;
        return floors.get(0);
    }

        public int findNumberFloorForUnload(){
        return lift.getLoader().getPassengers().peek().getDesiredFloor();
    }
}






