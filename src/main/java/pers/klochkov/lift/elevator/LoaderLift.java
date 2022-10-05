package pers.klochkov.lift.elevator;


import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.reader.PropertiesLiftReader;

import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import static pers.klochkov.lift.prog.Condition.*;

public class LoaderLift {
    public final int maxPerson;
    private int amountPerson;
    //private List<Person> peopleInsideLift = new ArrayList<>();
    private PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
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
            priorityQueue = getQueueInsideLift((Comparator.comparing(Person::getDesiredFloor)));
            loadLiftByCondition(floor, floor.dequeUP);
        }
        if (lift.getCondition() == DOWN){
            priorityQueue = getQueueInsideLift(Comparator.comparing(Person::getDesiredFloor).reversed());
            loadLiftByCondition(floor, floor.dequeDown);
        }
    }

    private PriorityQueue<Person> getQueueInsideLift (Comparator<Person> comparator){
        PriorityQueue<Person> priorityQueue1 = new PriorityQueue<>(comparator);
        priorityQueue1.addAll(priorityQueue);
        return priorityQueue1;

    }

    private void loadLiftByCondition(Floor floor, Deque<Person> deque){
        while (!deque.isEmpty()){
            if (amountPerson == 5) return;
            Person personToLift = deque.remove();
            priorityQueue.add(personToLift);
            floor.people.remove(personToLift);
            amountPerson++;
        }
    }

    public void unload(PriorityQueue<Person> priorityQueue){
        while (!priorityQueue.isEmpty()){
            Person person = priorityQueue.peek();
            if (lift.getNumberFloor() == person.getDesiredFloor()){
                priorityQueue.poll();
                amountPerson--;
            }else return;
        }
    }


    public PriorityQueue<Person> getPriorityQueue() {
        return priorityQueue;
    }
}
