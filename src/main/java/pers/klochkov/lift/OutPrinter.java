package pers.klochkov.lift;

import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class OutPrinter {
    List<Person> peopleInsideLift = new ArrayList<>();

    public void printBuilding(List<Floor> floors){
        List<String> listCopy = printByFormat(floors);
        List<String> listForPrint = listCopy.subList(0, listCopy.size());
        Collections.reverse(listForPrint);
        listForPrint.stream().forEach(System.out::print);
    }


    private List<String> printByFormat(List<Floor> floors){
     return floors.stream()
             .map(floor -> printByFormatOneString(floor.dequeUP, floor.dequeDown, String.valueOf(floor.getNumberFloor())))
             .collect(Collectors.toList());

    }

    private String printByFormatOneString(Queue<Person> queueUP, Queue<Person> queueDown, String numberFloor) {
        String peopleInLift = "";
        if (!peopleInsideLift.isEmpty()) peopleInLift = peopleInsideLift.toString();
       return String.format("%2s|%60s | UP %s DOWN %s\n", numberFloor, peopleInLift, queueUP, queueDown);
    }
}
