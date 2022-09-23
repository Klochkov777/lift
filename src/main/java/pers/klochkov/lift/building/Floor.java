package pers.klochkov.lift.building;

import pers.klochkov.lift.prog.Condition;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Floor {
    private int numberFloor;
    public List<Person> people = new ArrayList<>();
    private Condition buttonUp = Condition.NOT_MOVE;
    private Condition buttonDown = Condition.NOT_MOVE;
    private Deque<Person> dequeUP = new ArrayDeque();
    private Deque<Person> dequeDown = new ArrayDeque();


    public Floor(int numberFloor) {
        this.numberFloor = numberFloor;
    }

    public int getNumberFloor() {
        return numberFloor;
    }

    public Condition getButtonUp() {
        return buttonUp;
    }


    ////Задать свое проверяемое исключение
    public void setButtonUp(Condition buttonUp) throws IOException {
        if (buttonUp == Condition.DOWN) throw new IOException("ButtonUp can't be Down");
        this.buttonUp = buttonUp;
    }

    public Condition getButtonDown() {
        return buttonDown;
    }


    ////Задать свое проверяемое исключение
    public void setButtonDown(Condition buttonDown) throws IOException {
        if (buttonDown == Condition.UP) throw new IOException("ButtonDown can't be UP");
        this.buttonDown = buttonDown;
    }

    public void setDequeUP() {
        people.stream()
                .filter(person -> person.getCondition().equals(Condition.UP))
                .forEach(person -> dequeUP.push(person));

    }

    public void setDequeDown() {
        people.stream()
                .filter(person -> person.getCondition().equals(Condition.UP))
                .forEach(person -> dequeUP.push(person));
    }
}
