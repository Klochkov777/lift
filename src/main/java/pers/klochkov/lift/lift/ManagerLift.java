package pers.klochkov.lift.lift;

import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.prog.Condition;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


public class ManagerLift {

    public void moveClosestFloorWithPeopleAndLiftEmpty(List<Floor> floors, Lift lift) throws IOException {
        Floor result;
        Condition condition = lift.getCondition();
        List<Floor> floors1 = null;
        if (condition.equals(Condition.NOT_MOVE)) {
            floors1 = floors.stream().filter(floor -> !floor.people.isEmpty()).collect(Collectors.toList());
        } else if (condition.equals(Condition.UP)){
            floors1 = floors.stream()
                    .filter(floor -> (!floor.dequeUP.isEmpty() && lift.getNumberFloor() < floor.getNumberFloor()))
                    .collect(Collectors.toList());
        } else{
            floors1 = floors.stream()
                    .filter(floor -> (!floor.dequeDown.isEmpty() && lift.getNumberFloor() > floor.getNumberFloor()))
                    .collect(Collectors.toList());
        }
            result = findClosestFloorWithPeopleAndLiftEmpty(floors1, lift);
        if (result == null) {
            System.out.println("All people achieved their points destination");
            System.exit(0);  // it is right or wrong for normal exit from programme
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

    class MyComparatorFromLeast implements Comparator<Person>{

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

    class MyComparatorFromMost implements Comparator<Person>{

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






