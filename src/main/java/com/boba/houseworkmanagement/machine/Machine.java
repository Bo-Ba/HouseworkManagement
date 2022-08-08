package com.boba.houseworkmanagement.machine;

import com.boba.houseworkmanagement.machineState.DishwasherToPackState;
import com.boba.houseworkmanagement.user.MachineUser;
import com.boba.houseworkmanagement.machineState.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;

@Entity
@Table
public class Machine {
    @Id
    @Getter
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
    private String name;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private State state;
    @Getter
    @ManyToMany(mappedBy = "userMachines")
    private List<MachineUser> machineUsers = new ArrayList<>();

    public Machine(Long machineId, String name, State state) {
        this.machineId = machineId;
        this.name = name;
        this.state = state;
        System.out.println("test");
    }

    public Machine() {
        System.out.println("test no param");
    }

    public void addNewMachineUser(MachineUser machineUser) {
        if(!machineUsers.contains(machineUser)) {
            machineUsers.add(machineUser);
        }
    }
    public void doWork(State state) {
        state.next(this);
    }

}
