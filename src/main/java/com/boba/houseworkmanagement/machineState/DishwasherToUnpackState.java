package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.stereotype.Component;

@Component
public class DishwasherToUnpackState extends State {
    private final static String STATE_NAME = "Dishwasher ready to unpack";

    public DishwasherToUnpackState() {
        super(STATE_NAME);
    }
    @Override
    public void next(Machine machine) {
        machine.setState(context.getBean(DishwasherToPackState.class).name);
    }

    @Override
    public void prev(Machine machine) {
        machine.setState(context.getBean(DishwasherRunningState.class).name);
    }
}
