package com.boba.houseworkmanagement.schedule;

import com.boba.houseworkmanagement.machine.Machine;
import com.boba.houseworkmanagement.user.MachineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {

    Optional<WorkSchedule> findByMachineMachineId(Long machineId);
    Optional<WorkSchedule> findByAttenderMachineUserId(Long machineUserId);

    @Query("Select mu from MachineUser mu where mu.username =?1")
    Optional<MachineUser> findMachineUserById(String username);

    @Query("Select m from Machine m where m.machineId =?1")
    Optional<Machine> findMachineById(Long machineId);
}
