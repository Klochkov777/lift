package pers.klochkov.lift.lift;

import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.prog.Condition;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

public class LoaderLift {
    private final int maxPerson;
    private int amountPerson;
    public List<Person> peoplePeopleInsideLift = new ArrayList<>();

    public LoaderLift(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public int getAmountPerson() {
        return amountPerson;
    }

    public void loadLift(Floor floor, Lift lift, Building building){
        Condition conditionForLift = getConditionForLift(floor, lift, building);
        lift.setCondition(conditionForLift);
        if (lift.getCondition().equals(Condition.UP)){
            loadLiftByCondition(floor, lift, floor.dequeUP);
        }
        if (lift.getCondition().equals(Condition.DOWN)){
            loadLiftByCondition(floor, lift, floor.dequeDown);
        }
    }

    private Condition getConditionForLift(Floor floor, Lift lift, Building building){
        int numberLastFloor = building.floors.get(building.floors.size()-1).getNumberFloor();
        int numberFirstFloor = building.floors.get(0).getNumberFloor();
        if (lift.getNumberFloor() == numberLastFloor) return  Condition.DOWN;
        if (lift.getNumberFloor() == numberFirstFloor)return Condition.UP;
        if (lift.getCondition().equals(Condition.NOT_MOVE) && !floor.dequeUP.isEmpty()) return Condition.UP;
        else if (lift.getCondition().equals(Condition.NOT_MOVE) && !floor.dequeDown.isEmpty()) return  Condition.DOWN;
        return lift.getCondition();
    }

    private void loadLiftByCondition(Floor floor, Lift lift, Deque<Person> dequeUpOrDown){
        while (true){
            if (dequeUpOrDown.isEmpty() || amountPerson == 5) return;
            Person personToLift = dequeUpOrDown.remove();
            lift.loader.peoplePeopleInsideLift.add(personToLift);
            floor.people.remove(personToLift);
            amountPerson ++;
        }
    }

    public void unload(PriorityQueue<Person> priorityQueue, Lift lift){
        while (!priorityQueue.isEmpty()){
            Person person = priorityQueue.peek();
            if (lift.getNumberFloor() == person.getDesiredFloor()){
                lift.loader.peoplePeopleInsideLift.remove(person);
                priorityQueue.poll();
                amountPerson --;
            }else return;
        }
    }



}
