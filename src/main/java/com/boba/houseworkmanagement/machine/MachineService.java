package com.boba.houseworkmanagement.machine;

import com.boba.houseworkmanagement.schedule.WorkScheduleService;
import com.boba.houseworkmanagement.user.MachineUser;
import com.boba.houseworkmanagement.user.MachineUserService;
import com.boba.houseworkmanagement.machineState.State;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class MachineService {

    private final MachineRepository machineRepository;
    private final MachineUserService machineUserService;
    private final HashMap<String, State> dishwasherStates;
    private final WorkScheduleService workScheduleService;

    public MachineService(MachineRepository machineRepository, MachineUserService machineUserService,
                          HashMap<String, State> dishwasherStates, WorkScheduleService workScheduleService) {
        this.machineRepository = machineRepository;
        this.machineUserService = machineUserService;
        this.dishwasherStates = dishwasherStates;
        this.workScheduleService = workScheduleService;
    }

    public List<MachineUser> getMachineUsers(Long machineId) {
        return machineRepository.findAllPeopleByMachineId(machineId);
    }

    public void addNewMachine(Machine machine) {
        var newMachine = new Machine();
        newMachine.setName(machine.getName());
        var person = machineUserService.getUserByUsername("BoBa");
        person.addNewUserMachine(newMachine);
        newMachine.addNewMachineUser(person);
        machineRepository.save(newMachine);
    }

    public Machine getMachineById(Long machineId){
        return machineRepository.findById(machineId).orElseThrow();
    }

    public Machine doWork(Long machineId) {
        var housework = workScheduleService.getMachineHousework(machineId);
        var machine = housework.getMachine();
        var attender = housework.getAttender();

        machine.doWork(dishwasherStates.get(machine.getState()));
        housework.setAttender(dishwasherStates.get(machine.getState()).findNextAttender(machine, attender));
        housework.setUserNotificationDate(LocalDateTime.now());

        machineRepository.save(machine);
        workScheduleService.updateWorkSchedule(housework);

        return machine;
    }
}
