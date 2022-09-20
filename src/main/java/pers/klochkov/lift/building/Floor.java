package pers.klochkov.lift.building;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int numberFloor;
    List<Person> people = new ArrayList<Person>();


    public Floor(int numberFloor) {
        this.numberFloor = numberFloor;
    }

    public int getNumberFloor() {
        return numberFloor;
    }
}
