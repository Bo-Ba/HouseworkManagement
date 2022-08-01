package com.boba.houseworkmanagement.User;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class MachineUserController {

    private final MachineUserService personService;

    public MachineUserController(MachineUserService machineUserService) {
        this.personService = machineUserService;
    }

    @GetMapping
    public List<MachineUser> getPeople(){
        return personService.getPeople();
    }

    @GetMapping("/{username}")
    public MachineUser getPeople(@PathVariable String username){
        return personService.getPersonByUsername(username);
    }

    @PostMapping
    public void registerNewPerson(@RequestBody MachineUser machineUser) {
        personService.addNewPerson(machineUser);
    }

    @GetMapping("/machines/{personId}")
    public List<Machine> getUserMachine(@PathVariable Long personId) {
        return personService.getUserMachine(personId);
    }
}
