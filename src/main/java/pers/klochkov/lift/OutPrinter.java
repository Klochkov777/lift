package pers.klochkov.lift;

import pers.klochkov.lift.building.Building;
import pers.klochkov.lift.building.Floor;
import pers.klochkov.lift.building.Person;
import pers.klochkov.lift.elevator.Lift;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class OutPrinter {

    Building building;

    public OutPrinter(Building building) {
        this.building = building;
    }

    public void printBuilding(){
        List<Floor> floors = building.getFloors();
        Lift lift = building.getLift();
        List<String> listCopy = printByFormat(floors, lift.getNumberFloor());
        List<String> listForPrint = listCopy.subList(0, listCopy.size());
        Collections.reverse(listForPrint);
        listForPrint.stream().forEach(System.out::print);
        System.out.println("\n\n");
    }


    private List<String> printByFormat(List<Floor> floors, int numberLift){
     return floors.stream()
             .map(floor -> printByFormatOneString(floor.dequeUP, floor.dequeDown, String.valueOf(floor.getNumberFloor()), numberLift))
             .collect(Collectors.toList());

    }

    private String printByFormatOneString(Queue<Person> queueUP, Queue<Person> queueDown, String numberFloor, int numberLift) {
        String peopleInLift = "";
        if (Integer.valueOf(numberFloor).equals(numberLift)) peopleInLift = building.getLift().getLoader().getPriorityQueue().toString();
       return String.format("%2s|%35s | UP %s DOWN %s\n", numberFloor, peopleInLift, queueUP, queueDown);
    }
}
