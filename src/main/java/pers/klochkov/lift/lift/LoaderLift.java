package pers.klochkov.lift.lift;

import pers.klochkov.lift.prog.Condition;

public class LoaderLift {
    private final int maxPerson;
    private int amountPerson;
    private Loading loading;

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
        Loading loading;
        switch(amountPerson) {
            case maxPerson: return Loading.FULL;


        }


    }
}
