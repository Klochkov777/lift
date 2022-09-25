package pers.klochkov.lift.lift;

import pers.klochkov.lift.OutPrinter;
import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.prog.Condition;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class ManagerLift {

    public void moveClosestFloorWithPeopleWhenLiftEmpty(List<Floor> floors, Lift lift) throws IOException {
        Floor result;
        List<Floor> floors1;
            floors1 = floors.stream().filter(floor -> !floor.people.isEmpty()).collect(Collectors.toList());
            result = findClosestFloorWithPeopleAndLiftEmpty(floors1, lift);
        if (result == null) {
            System.out.println("All people achieved their points destination");
            System.exit(0);  // it is right or wrong for normal exit from programme. It is easy set it to main but is it necessarily
        }
        lift.setNumberFloor(result.getNumberFloor());
    }

    private Floor findClosestFloorWithPeopleAndLiftEmpty(List<Floor> floors1, Lift lift){
        Floor result = null;
        if (floors1.isEmpty()) return null;
        int differenceBetweenFloors = Integer.MAX_VALUE;// here govno code or maybe it is possible variant
        for (Floor floor : floors1) {
            int differenceBetweenFloorsNow = Math.abs(lift.getNumberFloor() - floor.getNumberFloor());
            if (differenceBetweenFloorsNow < differenceBetweenFloors) {
                differenceBetweenFloors = differenceBetweenFloorsNow;
                result = floor;
            }
        }
        return result;
    }

    public PriorityQueue<Person> getPriorityQueuePersonInLift(Lift lift){
        PriorityQueue<Person> personPriorityQueue = new PriorityQueue<>();
        if (lift.getCondition().equals(Condition.UP)) {
            personPriorityQueue = new PriorityQueue<>(new MyComparatorFromLeast());
        } else if (lift.getCondition().equals(Condition.DOWN)){
            personPriorityQueue = new PriorityQueue<>(new MyComparatorFromMost());
        }
        personPriorityQueue.addAll(lift.loader.peoplePeopleInsideLift);
        return personPriorityQueue;
    }


    public void moveToFloorForPassenger(PriorityQueue<Person> priorityQueue, Lift lift){
        lift.setNumberFloor(priorityQueue.peek().getDesiredFloor());
    }

    public void moveToFloorOnTheWay(Building building, Lift lift, PriorityQueue<Person> peopleInLift, OutPrinter outPrinter){
        while (true) {
            if (lift.loader.getAmountPerson() == lift.loader.maxPerson) break;
            Floor floor = findFloorOnTheWay(peopleInLift, lift, building.floors, building, outPrinter);
            if (floor == null) break;
            lift.setNumberFloor(floor.getNumberFloor());
            lift.loader.loadLift(floor, lift, building);
            outPrinter.printBuilding(building.floors, lift);
        }
    }


    private Floor findFloorOnTheWay(PriorityQueue<Person> priorityQueue, Lift lift, List<Floor> floors, Building building, OutPrinter outPrinter) {
        //List<Floor> floors1 = new ArrayList<>(floors);
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


    static class MyComparatorFromLeast implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            if (o1.getDesiredFloor() > o2.getDesiredFloor()) return 1;
            else if (o1.getDesiredFloor() < o2.getDesiredFloor()) {
                return -1;
            }
            else
                return 0;
        }
    }


    static class MyComparatorFromMost implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            if (o1.getDesiredFloor() < o2.getDesiredFloor()) return 1;
            else if (o1.getDesiredFloor() > o2.getDesiredFloor()) {
                return -1;
            }
            else
                return 0;
        }
    }
}






