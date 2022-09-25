package pers.klochkov.lift.building;

import pers.klochkov.lift.prog.Condition;

import java.io.IOException;
import java.util.*;

public class Floor {
    private int numberFloor;
    public List<Person> people = new ArrayList<>();
    public Deque<Person> dequeUP = new ArrayDeque();
    public Deque<Person> dequeDown = new ArrayDeque();


    public Floor(int numberFloor) {
        this.numberFloor = numberFloor;
    }

    public int getNumberFloor() {
        return numberFloor;
    }

}
