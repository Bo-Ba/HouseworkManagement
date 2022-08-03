package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.stereotype.Component;

@Component
public class DishwasherToPackState extends State {
    public final static String STATE_NAME = "Dishwasher ready to pack";

    public DishwasherToPackState() {
        super(STATE_NAME);
    }

    @Override
    public void next(Machine machine) {
        machine.setState(context.getBean(DishwasherRunningState.class).name);
    }

    @Override
    public void prev(Machine machine) {
        machine.setState(context.getBean(DishwasherToUnpackState.class).name);
    }
}
