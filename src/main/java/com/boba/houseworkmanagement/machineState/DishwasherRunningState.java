package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "1")
public class DishwasherRunningState extends State {
    private final static String STATE_NAME = "Dishwasher running";
    @Transient
    @Autowired
    private DishwasherStatesFactory dishwasherStatesFactory;

    public DishwasherRunningState() {
        super(STATE_NAME);
    }

    @Override
    public void next(Machine machine) {
        machine.setState(dishwasherStatesFactory.createDishwasherToUnpackState());
    }

    @Override
    public void prev(Machine machine) {
        machine.setState(dishwasherStatesFactory.createDishwasherToPackState());
    }
}
