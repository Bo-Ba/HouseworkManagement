package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "2")
public class DishwasherToPackState extends State {
    public final static String STATE_NAME = "Dishwasher ready to pack";

    @Transient
    @Autowired
    private DishwasherStatesFactory dishwasherStatesFactory;

    public DishwasherToPackState(DishwasherStatesFactory dishwasherStatesFactory) {
        super(STATE_NAME);
        this.dishwasherStatesFactory = dishwasherStatesFactory;
    }

    public DishwasherToPackState() {
        super(STATE_NAME);
    }

    @Override
    public void next(Machine machine) {
        machine.setState(dishwasherStatesFactory.createDishwasherRunningState());
    }

    @Override
    public void prev(Machine machine) {
        machine.setState(dishwasherStatesFactory.createDishwasherToUnpackState());
    }
}
