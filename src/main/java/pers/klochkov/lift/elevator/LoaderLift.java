package pers.klochkov.lift.elevator;


import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.reader.PropertiesLiftReader;

import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import static pers.klochkov.lift.elevator.Condition.*;

public class LoaderLift {
    public final int maxPerson;
    private int amountPerson;
    private PriorityQueue<Person> passengers = new PriorityQueue<>();
    private Lift lift;

    public LoaderLift(Lift lift) {
        PropertiesLiftReader properties = new PropertiesLiftReader();
        this.maxPerson = properties.getMaxPerson();
        this.lift = lift;
    }

    public int getAmountPerson() {
        return amountPerson;
    }

    public void loadLift(){
        Floor floor = lift.getFloor();
        if (lift.getCondition() == UP){
            passengers = getQueueInsideLift((Comparator.comparing(Person::getDesiredFloor)));
            loadLiftByCondition(floor, floor.dequeUP);
        }
        if (lift.getCondition() == DOWN){
            passengers = getQueueInsideLift(Comparator.comparing(Person::getDesiredFloor).reversed());
            loadLiftByCondition(floor, floor.dequeDown);
        }
    }

    private PriorityQueue<Person> getQueueInsideLift (Comparator<Person> comparator){
        PriorityQueue<Person> priorityQueue1 = new PriorityQueue<>(comparator);
        priorityQueue1.addAll(passengers);
        return priorityQueue1;

    }

    private void loadLiftByCondition(Floor floor, Deque<Person> deque){
        while (!deque.isEmpty()){
            if (amountPerson == 5) return;
            Person personToLift = deque.remove();
            passengers.add(personToLift);
            floor.people.remove(personToLift);
            amountPerson++;
        }
    }

    public void unload(){
        while (!passengers.isEmpty()){
            Person person = passengers.peek();
            if (lift.getNumberFloor() == person.getDesiredFloor()){
                passengers.poll();
                amountPerson--;
            }else return;
        }
    }


    public PriorityQueue<Person> getPassengers() {
        return passengers;
    }
}
