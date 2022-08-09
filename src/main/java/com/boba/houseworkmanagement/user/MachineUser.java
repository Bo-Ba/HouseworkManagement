package com.boba.houseworkmanagement.user;

import com.boba.houseworkmanagement.machine.Machine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class MachineUser {

    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private Long machineUserId;


    @Getter
    private String username;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "MachineUser_Machine",
            joinColumns = {@JoinColumn(name = "MachineUserId")},
            inverseJoinColumns = {@JoinColumn(name = "MachineId")}
    )
    private final List<Machine> userMachines = new ArrayList<>();

    public MachineUser() {

    }

    public MachineUser(String username) {
        this.username = username;
    }

    public void addNewUserMachine(Machine machine) {
        if (!userMachines.contains(machine)) {
            userMachines.add(machine);
        }
    }
}
