package pers.klochkov.lift.lift;

import pers.klochkov.lift.prog.Condition;

public class Lift {

    int numberFloor = 1;
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }


}
