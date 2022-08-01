package com.boba.houseworkmanagement.machine;

import com.boba.houseworkmanagement.User.MachineUser;
import com.boba.houseworkmanagement.User.MachineUserService;
import com.boba.houseworkmanagement.machineState.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class MachineService {

    private final MachineRepository machineRepository;
    private final MachineUserService machineUserService;
    private final HashMap<String, State> dishwasherStates;

    public MachineService(MachineRepository machineRepository, MachineUserService machineUserService,
                          HashMap<String, State> dishwasherStates) {
        this.machineRepository = machineRepository;
        this.machineUserService = machineUserService;
        this.dishwasherStates = dishwasherStates;
    }

    public List<MachineUser> getMachineUsers(Long machineId) {
        return machineRepository.findAllPeopleByMachineId(machineId);
    }

    public void addNewMachine(Machine machine) {
        var newMachine = new Machine();
        newMachine.setName(machine.getName());
        var person = machineUserService.getPersonByUsername("BoBa");
        person.addNewUserMachine(newMachine);
        newMachine.addNewMachineUser(person);
        machineRepository.save(newMachine);
    }
}
