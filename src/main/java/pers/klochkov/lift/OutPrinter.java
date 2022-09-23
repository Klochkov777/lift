package pers.klochkov.lift;

import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OutPrinter {
    List<Person> peopleInsideLift = new ArrayList<>();

    public void printBuilding(List<Floor> floors){
        printByFormat(floors).stream().forEach(System.out::print);
    }


    private List<String> printByFormat(List<Floor> floors){
     return floors.stream().map(floor -> printByFormatOneString(floor.people, String.valueOf(floor.getNumberFloor()))).collect(Collectors.toList());

    }

    private String printByFormatOneString(List<Person> peopleFloor, String numberFloor) {
        String peopleInLift = "";
        if (!peopleInsideLift.isEmpty()) peopleInLift = peopleInsideLift.toString();
       return String.format("%2s|%60s | %s + \n", numberFloor, peopleInLift, peopleFloor);
    }
}
