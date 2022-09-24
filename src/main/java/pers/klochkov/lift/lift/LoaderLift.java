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



    private Loading loading;
    public List<Person> peoplePeopleInsideLift = new ArrayList<>();

    public LoaderLift(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public int getAmountPerson() {
        return amountPerson;
    }

    public void setAmountPerson(int amountPerson) {
        if (amountPerson > maxPerson) throw new RuntimeException("Lift is overloaded");
        this.amountPerson = amountPerson;
    }

    public Loading getLoading() {
      if (maxPerson == amountPerson) return Loading.FULL;
      if (amountPerson == 0) return Loading.EMPTY;
      return Loading.NOT_FULL;
    }

    public void loadLift(Floor floor, Lift lift, Building building){
        if (lift.getNumberFloor() == building.floors.get(building.floors.size()-1).getNumberFloor()) lift.setCondition(Condition.DOWN);
        if (lift.getNumberFloor() == building.floors.get(0).getNumberFloor()) lift.setCondition(Condition.UP);

        if (lift.getCondition().equals(Condition.NOT_MOVE) && !floor.dequeUP.isEmpty()) lift.setCondition(Condition.UP);
        else if (lift.getCondition().equals(Condition.NOT_MOVE) && !floor.dequeDown.isEmpty()) lift.setCondition(Condition.DOWN); //!!!!!!!!!!!!!!!!!!!!!!!

        if (lift.getCondition().equals(Condition.UP)){
            loadLiftByCondition(floor, lift, floor.dequeUP);
        }
        if (lift.getCondition().equals(Condition.DOWN)){
            loadLiftByCondition(floor, lift, floor.dequeDown);
        }

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
        while (true){
            if (priorityQueue.isEmpty()) return;
            Person person = priorityQueue.peek();
            if (lift.getNumberFloor() == person.getDesiredFloor()){
                lift.loader.peoplePeopleInsideLift.remove(person);
                priorityQueue.poll();
                amountPerson --;
            }else return;
        }
    }



}
