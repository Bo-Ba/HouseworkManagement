package com.boba.houseworkmanagement.machine;

import com.boba.houseworkmanagement.user.MachineUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="machine")
public class MachineController {

    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/{machineId}")
    public List<MachineUser> getMachines(@PathVariable Long machineId){
        return machineService.getMachineUsers(machineId);
    }

    @PostMapping
    public void addNewMachine(@RequestBody Machine machine) {
        machineService.addNewMachine(machine);
    }

    @GetMapping("/work/{machineId}")
    public Machine doWorkMachine(@PathVariable Long machineId) {
        return machineService.doWork(machineId);
    }

}
