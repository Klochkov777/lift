package pers.klochkov.lift.lift;

import pers.klochkov.lift.prog.Condition;

public class Lift {

    LoaderLift loader;
    ManagerLift managerLift;


    int numberFloor = 1;
    private Condition condition;

    public Lift(LoaderLift loader, ManagerLift managerLift) {
        this.loader = loader;
        this.managerLift = managerLift;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }




}
