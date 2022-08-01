package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.stereotype.Component;

@Component
public class DishwasherRunningState extends State {
    private final static String STATE_NAME = "Dishwasher running";

    public DishwasherRunningState() {
        super(STATE_NAME);
    }

    @Override
    public void next(Machine machine) {
        machine.setState(context.getBean(DishwasherToUnpackState.class).name);
    }

    @Override
    public void prev(Machine machine) {
        machine.setState(context.getBean(DishwasherToPackState.class).name);
    }
}
