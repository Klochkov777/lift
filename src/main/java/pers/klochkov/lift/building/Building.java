package pers.klochkov.lift.building;

import pers.klochkov.lift.reader.PropertiesBuildingReader;

import java.util.List;

public final class Building {
    public final int AMOUNT_FLOOR;
    public List<Floor> floors;
    public Building(int AMOUNT_FLOOR) {
       this.AMOUNT_FLOOR = AMOUNT_FLOOR;
    }
}

