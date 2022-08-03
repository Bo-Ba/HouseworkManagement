package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;
import com.boba.houseworkmanagement.user.MachineUser;
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

    public MachineUser findNextAttender(Machine machine, MachineUser currentAttender) {
        var machineUsers = machine.getMachineUsers();
        int index = machineUsers.indexOf(currentAttender);

        return machineUsers.get((index + 1) % machineUsers.size());
    }
}
