package com.boba.houseworkmanagement;

import com.boba.houseworkmanagement.machine.Machine;
import com.boba.houseworkmanagement.machine.MachineRepository;
import com.boba.houseworkmanagement.machineState.DishwasherToPackState;
import com.boba.houseworkmanagement.machineState.StateRepository;
import com.boba.houseworkmanagement.user.MachineUser;
import com.boba.houseworkmanagement.user.MachineUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(MachineUserRepository machineUserRepository,
                                        MachineRepository machineRepository, StateRepository stateRepository) {
        return args -> {
            var boba = new MachineUser("BoBa");
            var steve = new MachineUser("Steve");

            var dishwasher = new Machine("Dishwasher", new DishwasherToPackState());
            var washingMachine = new Machine("Washing Machine", new DishwasherToPackState());

            machineUserRepository.saveAll(List.of(boba, steve));
            machineRepository.saveAll(List.of(dishwasher, washingMachine));

            boba.addNewUserMachine(dishwasher);
            boba.addNewUserMachine(washingMachine);
            steve.addNewUserMachine(dishwasher);

            dishwasher.addNewMachineUser(boba);
            dishwasher.addNewMachineUser(steve);
            washingMachine.addNewMachineUser(boba);

//            dishwasher.doWork();

            machineUserRepository.saveAll(List.of(boba, steve));
            machineRepository.saveAll(List.of(dishwasher, washingMachine));
        };
    }
}
