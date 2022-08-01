package com.boba.houseworkmanagement.machine;

import com.boba.houseworkmanagement.User.MachineUser;
import com.boba.houseworkmanagement.machineState.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Machine {

    @Id
    @SequenceGenerator(
            name = "machine_sequence",
            sequenceName = "machine_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "machine_sequence"
    )
    private Long machineId;

    @Getter
    @Setter
    private String name, state;

    @ManyToMany(mappedBy = "userMachines")
    private List<MachineUser> machineUsers = new ArrayList<>();

    public void addNewMachineUser(MachineUser machineUser) {
        if(!machineUsers.contains(machineUser)) {
            machineUsers.add(machineUser);
        }
    }

    public void doWork(State state) {
        state.next(this);
    }
}
