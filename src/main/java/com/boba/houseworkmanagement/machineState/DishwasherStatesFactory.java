package com.boba.houseworkmanagement.machineState;

import org.springframework.stereotype.Component;

@Component
public class DishwasherStatesFactory {
    public DishwasherRunningState createDishwasherRunningState() {
        return new DishwasherRunningState();
    }
    public DishwasherToPackState createDishwasherToPackState() {
        return new DishwasherToPackState();
    }

    public DishwasherToUnpackState createDishwasherToUnpackState() {
        return new DishwasherToUnpackState();
    }
}
