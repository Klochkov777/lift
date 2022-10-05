package pers.klochkov.lift.elevator;

import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.prog.Condition;
import pers.klochkov.lift.reader.PropertiesLiftReader;

import static pers.klochkov.lift.prog.Condition.*;
import static pers.klochkov.lift.prog.Condition.DOWN;

public class Lift {
    private LoaderLift loader;
    private int numberFloor;
    private Condition condition;
    Building building;
    ManagerLift managerLift;

    public Lift(Building building) {
        PropertiesLiftReader propertiesLift = new PropertiesLiftReader();
        numberFloor = propertiesLift.getStartFloor();
        condition = Condition.NOT_MOVE;
        this.building = building;
        loader = new LoaderLift(this);
        this.managerLift = new ManagerLift(this);
    }



    public Condition getConditionForLift(){
        Floor floor = getFloor();
        int numberLastFloor = building.getNumberLastFloor();
        int numberFirstFloor = building.getNumberFirsFloor();
        if (numberFloor == numberLastFloor) {return  DOWN;}
        else if (numberFloor == numberFirstFloor) {return UP;}
        else if (condition == NOT_MOVE && !floor.dequeUP.isEmpty()) {return UP;}
        else if (condition == NOT_MOVE && !floor.dequeDown.isEmpty()) {return DOWN;}
        return condition;
    }

    public Floor getFloor() {
        return building.getFloors().stream()
                .filter(floor1 -> floor1.getNumberFloor() == numberFloor).findFirst().get();
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

    public LoaderLift getLoader() {
        return loader;
    }

    public ManagerLift getManagerLift() {
        return managerLift;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }




}
