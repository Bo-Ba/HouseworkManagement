package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.User.MachineUser;
import com.boba.houseworkmanagement.machine.Machine;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class State {
    @Autowired
    protected ApplicationContext context;

    @Getter
    protected final String name;

    public State(String name) {
        this.name = name;
    }

    abstract public void next(Machine machine);
    abstract public void prev(Machine machine);
}
