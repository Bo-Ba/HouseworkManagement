package com.boba.houseworkmanagement.machineState;

import com.boba.houseworkmanagement.machine.Machine;
import com.boba.houseworkmanagement.user.MachineUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@DiscriminatorColumn(name="state_type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class State {
    @Id
    @Getter
    @Setter
    @SequenceGenerator(
            name = "state_sequence",
            sequenceName = "state_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "state_sequence"
    )
    private Long stateId;

    @Autowired
    @Transient
    protected ApplicationContext context;

    @Getter
    protected String name;

    @OneToMany
    private List<MachineUser> stateAttenders;

    public State() {
        stateAttenders = new ArrayList<>();
    }

    private void addStateAttender(MachineUser machineUser) {
        if(!stateAttenders.contains(machineUser))
            stateAttenders.add(machineUser);
    }

    public State(String name) {
        this.name = name;
    }

    abstract public void next(Machine machine);
    abstract public void prev(Machine machine);

    public MachineUser findNextAttender(MachineUser currentAttender) {
        int index = stateAttenders.indexOf(currentAttender);

        return stateAttenders.get((index + 1) % stateAttenders.size());
    }
}
