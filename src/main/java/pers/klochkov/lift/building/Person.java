package pers.klochkov.lift.building;

import pers.klochkov.lift.prog.Condition;

public class Person {
    private int desiredFloor;


    private int hisFloor;
    private Condition condition;

    public Condition getCondition() {
        if (desiredFloor > hisFloor) {
            return Condition.UP;
        }
        return Condition.DOWN;
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
