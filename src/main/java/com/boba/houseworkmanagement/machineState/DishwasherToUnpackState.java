package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "3")
public class DishwasherToUnpackState extends State {
    private final static String STATE_NAME = "Dishwasher ready to unpack";

    @Transient
    @Autowired
    private DishwasherStatesFactory dishwasherStatesFactory;

    public DishwasherToUnpackState() {
        super(STATE_NAME);
    }
    @Override
    public void next(Machine machine) {
        machine.setState(dishwasherStatesFactory.createDishwasherToPackState());
    }

    @Override
    public void prev(Machine machine) {
        machine.setState(dishwasherStatesFactory.createDishwasherRunningState());
    }
}
