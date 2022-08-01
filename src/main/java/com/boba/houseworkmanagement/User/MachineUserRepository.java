package com.boba.houseworkmanagement.User;

import com.boba.houseworkmanagement.machine.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineUserRepository extends JpaRepository<MachineUser, Long> {
    @Query("SELECT p FROM MachineUser p WHERE p.username = ?1")
    Optional<MachineUser> findPersonByUsername(String username);


    @Query("SELECT p.userMachines FROM MachineUser p WHERE p.machineUserId = ?1")
    List<Machine> findAllMachinesByPersonId(Long personId);

}
