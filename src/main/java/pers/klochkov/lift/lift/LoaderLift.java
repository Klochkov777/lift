package pers.klochkov.lift.lift;

import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.prog.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoaderLift {
    private final int maxPerson;
    private int amountPerson;
    private Loading loading;
    List<Person> peoplePeopleInsideLift = new ArrayList<>();

    public LoaderLift(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public int getAmountPerson() {
        return amountPerson;
    }

    public void setAmountPerson(int amountPerson) {
        if (amountPerson > maxPerson) throw new RuntimeException("Lift is overloaded");
        this.amountPerson = amountPerson;
    }



    public Loading getLoading() {
      if (maxPerson == amountPerson) return Loading.FULL;
      if (amountPerson == 0) return Loading.EMPTY;
      return Loading.NOT_FULL;
    }

    public void loadLift(Floor floor, Lift lift, Condition condition){
//        if (condition.equals(Condition.DOWN) && floor.people.stream().findAny()){
//
//        }
    }
}
