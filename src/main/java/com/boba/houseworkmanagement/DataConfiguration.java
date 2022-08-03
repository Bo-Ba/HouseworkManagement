package com.boba.houseworkmanagement;

import com.boba.houseworkmanagement.machine.Machine;
import com.boba.houseworkmanagement.machine.MachineRepository;
import com.boba.houseworkmanagement.machineState.DishwasherToPackState;
import com.boba.houseworkmanagement.user.MachineUser;
import com.boba.houseworkmanagement.user.MachineUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(MachineUserRepository machineUserRepository, MachineRepository machineRepository) {
        return args -> {
            var boba = new MachineUser(1L, "BoBa");
            var steve = new MachineUser(2L, "Steve");

            var dishwasher = new Machine(1L, "Dishwasher", DishwasherToPackState.STATE_NAME);
            var washingMachine = new Machine(2L, "washingMachine", DishwasherToPackState.STATE_NAME);

            machineUserRepository.saveAll(List.of(boba, steve));
            machineRepository.saveAll(List.of(dishwasher, washingMachine));

            boba.addNewUserMachine(dishwasher);
            boba.addNewUserMachine(washingMachine);
            steve.addNewUserMachine(dishwasher);

            dishwasher.addNewMachineUser(boba);
            dishwasher.addNewMachineUser(steve);
            washingMachine.addNewMachineUser(boba);

            machineUserRepository.saveAll(List.of(boba, steve));
            machineRepository.saveAll(List.of(dishwasher, washingMachine));
        };
    }
}
