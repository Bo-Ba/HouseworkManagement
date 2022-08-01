package com.boba.houseworkmanagement.User;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineUserService {

    private final MachineUserRepository machineUserRepository;

    public MachineUserService(MachineUserRepository machineUserRepository) {
        this.machineUserRepository = machineUserRepository;
    }

    public List<MachineUser> getPeople() {
        return machineUserRepository.findAll();
    }

    public void addNewPerson(MachineUser machineUser) {
        machineUserRepository.findPersonByUsername(machineUser.getUsername())
                             .ifPresentOrElse((p) -> {
                                             throw new IllegalArgumentException("Username already taken");
                                         },
                                         () -> machineUserRepository.save(machineUser));
    }

    public MachineUser getPersonByUsername(String username) {
        return machineUserRepository.findPersonByUsername(username).orElseThrow();
    }


    public List<Machine> getUserMachine(Long personId){
        return machineUserRepository.findAllMachinesByPersonId(personId);
    }
}
