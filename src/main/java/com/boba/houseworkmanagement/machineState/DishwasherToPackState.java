package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "2")
public class DishwasherToPackState extends State {
    public final static String STATE_NAME = "Dishwasher ready to pack";

    public DishwasherToPackState() {
        super(STATE_NAME);
    }

    @Override
    public void next(Machine machine) {
        machine.setState(new DishwasherRunningState());
    }

    @Override
    public void prev(Machine machine) {
        machine.setState(new DishwasherToUnpackState());
    }
}
