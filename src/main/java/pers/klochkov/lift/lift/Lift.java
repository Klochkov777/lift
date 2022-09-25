package pers.klochkov.lift.lift;

import pers.klochkov.lift.prog.Condition;

public class Lift {
    public LoaderLift loader;
    private int numberFloor = 1;
    private Condition condition = Condition.NOT_MOVE;

    public Lift(LoaderLift loader) {
        this.loader = loader;
    }

    public int getNumberFloor() {
        return numberFloor;
    }

    public void setNumberFloor(int numberFloor) {
        this.numberFloor = numberFloor;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }




}
