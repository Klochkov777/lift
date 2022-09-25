package pers.klochkov.lift.lift;

import pers.klochkov.lift.prog.Condition;

public class Lift {

    public Lift(LoaderLift loader) {
        this.loader = loader;
    }

    public LoaderLift loader;


    public int getNumberFloor() {
        return numberFloor;
    }

    public void setNumberFloor(int numberFloor) {
        this.numberFloor = numberFloor;
    }

    private int numberFloor = 1;
    private Condition condition = Condition.NOT_MOVE;

//    public Lift(LoaderLift loader, ManagerLift managerLift) {
//        this.loader = loader;
//        this.managerLift = managerLift;
//    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }




}
