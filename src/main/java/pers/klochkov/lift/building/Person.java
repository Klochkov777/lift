package pers.klochkov.lift.building;

import pers.klochkov.lift.elevator.Condition;

import static pers.klochkov.lift.elevator.Condition.*;

public class Person {
    private int desiredFloor;
    private int hisFloor;

    public Condition getCondition() {
        return desiredFloor > hisFloor ? UP : DOWN;
    }

    public int getDesiredFloor() {
        return desiredFloor;
    }

    public void setHisFloor(int hisFloor) {
        this.hisFloor = hisFloor;
    }

    public void setDesiredFloor(int desiredFloor) {
        this.desiredFloor = desiredFloor;
    }

    public int getHisFloor() {
        return hisFloor;
    }

    @Override
    public String toString() {
        return "[" + desiredFloor +
                ']';
    }
}
