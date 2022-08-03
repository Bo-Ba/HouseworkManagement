package com.boba.houseworkmanagement.user;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineUserService {

    private final MachineUserRepository machineUserRepository;

    public MachineUserService(MachineUserRepository machineUserRepository) {
        this.machineUserRepository = machineUserRepository;
    }

    public List<MachineUser> getMachineUsers() {
        return machineUserRepository.findAll();
    }

    public void addNewMachineUsers(MachineUser machineUser) {
        machineUserRepository.findUserByUsername(machineUser.getUsername())
                             .ifPresentOrElse((p) -> {
                                             throw new IllegalArgumentException("Username already taken");
                                         },
                                         () -> machineUserRepository.save(machineUser));
    }

    public MachineUser getUserByUsername(String username) {
        return machineUserRepository.findUserByUsername(username).orElseThrow();
    }


    public List<Machine> getUserMachines(Long machineUserId){
        return machineUserRepository.findAllMachinesByPersonId(machineUserId);
    }

    public MachineUser getUserById(Long machineUserId){
        return machineUserRepository.findById(machineUserId).orElseThrow();
    }
}
