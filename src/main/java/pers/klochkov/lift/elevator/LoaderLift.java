package pers.klochkov.lift.elevator;

import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.prog.Condition;
import pers.klochkov.lift.reader.PropertiesLiftReader;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import static pers.klochkov.lift.prog.Condition.*;

//public class LoaderLift {
//    public final int maxPerson;
//    private int amountPerson;
//    private List<Person> peopleInsideLift = new ArrayList<>();
//
//    public LoaderLift() {
//        PropertiesLiftReader properties = new PropertiesLiftReader();
//        this.maxPerson = properties.getMaxPerson();
//    }
//
//    public int getAmountPerson() {
//        return amountPerson;
//    }
//
//    public void loadLift(Floor floor, Lift lift, Building building){
//        Condition conditionForLift = getConditionForLift(floor, lift, building);
//        lift.setCondition(conditionForLift);
//        if (lift.getCondition().equals(UP)){
//            loadLiftByCondition(floor, lift, floor.dequeUP);
//        }
//        if (lift.getCondition().equals(DOWN)){
//            loadLiftByCondition(floor, lift, floor.dequeDown);
//        }
//    }
//
//    private Condition getConditionForLift(Floor floor, Lift lift, Building building){
//        int numberLastFloor = building.floors.get(building.floors.size()-1).getNumberFloor();
//        int numberFirstFloor = building.floors.get(0).getNumberFloor();
//        if (lift.getNumberFloor() == numberLastFloor) return  DOWN;
//        else if (lift.getNumberFloor() == numberFirstFloor) return UP;
//        else if (lift.getCondition() == NOT_MOVE && !floor.dequeUP.isEmpty()) return UP;
//        else if (lift.getCondition() == NOT_MOVE && !floor.dequeDown.isEmpty()) return DOWN;
//        return lift.getCondition();
//    }
//
//    private void loadLiftByCondition(Floor floor, Lift lift, Deque<Person> dequeUpOrDown){
//        while (true){
//            if (dequeUpOrDown.isEmpty() || amountPerson == 5) return;
//            Person personToLift = dequeUpOrDown.remove();
//            lift.loader.peopleInsideLift.add(personToLift);
//            floor.people.remove(personToLift);
//            amountPerson ++;
//        }
//    }
//
//    public void unload(PriorityQueue<Person> priorityQueue, Lift lift){
//        while (!priorityQueue.isEmpty()){
//            Person person = priorityQueue.peek();
//            if (lift.getNumberFloor() == person.getDesiredFloor()){
//                lift.loader.peopleInsideLift.remove(person);
//                priorityQueue.poll();
//                amountPerson--;
//            }else return;
//        }
//    }
//
//
//
//}
