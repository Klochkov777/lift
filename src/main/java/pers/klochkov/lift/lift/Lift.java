package pers.klochkov.lift.lift;

import pers.klochkov.lift.prog.Condition;

public class Lift {
    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    private Condition condition;
}
