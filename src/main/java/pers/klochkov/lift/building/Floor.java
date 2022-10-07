package pers.klochkov.lift.building;
import pers.klochkov.lift.elevator.Condition;
import pers.klochkov.lift.reader.PropertiesFloorReader;
import java.util.*;

//В задании сказано что
//На каждом этаже k количество пассажиров, где k - случайное число генерируемое при старте программы в диапазоне 0 <= k <= 10.
//Для меня не было понятно для каждого этажа генерируется случайное число или генерируется случ число и устанавливается для каждого этажа
//Я сделал что для каждого этажа отдельно генерируется отдельное случайное число.

public class Floor {
    private final int numberFloor;
    public List<Person> people = new ArrayList<>();
    public Deque<Person> dequeUP = new ArrayDeque<>();
    public Deque<Person> dequeDown = new ArrayDeque<>();
    int minPeople;
    int maxPeople;

    public Floor(int numberFloor) {
        this.numberFloor = numberFloor;
        PropertiesFloorReader propertiesFloorReader = new PropertiesFloorReader();
        minPeople = propertiesFloorReader.getMinPeople();
        maxPeople = propertiesFloorReader.getMaxPeople();
    }

    public int getNumberFloor() {
        return numberFloor;
    }

    protected void generatePeopleForOneFloor(int minDesiredFloor, int maxDesiredFloor) {
        int amountPeople = randomNumber(minPeople, maxPeople);
        for (int i = 0; i <= amountPeople; i++) {
            Person person = generatePerson(minDesiredFloor, maxDesiredFloor);
            people.add(person);
        }
        setDequeDown();
        setDequeUP();
    }

    private Person generatePerson(int minDesiredFloor, int maxDesiredFloor) {
        Person person = new Person();
        person.setHisFloor(getNumberFloor());
        int randomDesiredFloor = getRandomDesiredFloor(minDesiredFloor, maxDesiredFloor);
        person.setDesiredFloor(randomDesiredFloor);
        return person;
    }

    private int getRandomDesiredFloor (int min, int max) {
        int result;
        while (true) {
            if ((result = randomNumber(min, max)) != numberFloor) return result;
        }
    }

    private static int randomNumber(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private void setDequeUP() {
        people.stream()
                .filter(person -> person.getCondition().equals(Condition.UP))
                .forEach(person -> dequeUP.push(person));

    }

    private void setDequeDown() {
        people.stream()
                .filter(person -> person.getCondition().equals(Condition.DOWN))
                .forEach(person -> dequeDown.push(person));
    }

}
