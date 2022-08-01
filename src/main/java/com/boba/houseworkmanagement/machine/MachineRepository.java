package com.boba.houseworkmanagement.machine;


import com.boba.houseworkmanagement.User.MachineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    @Query("SELECT m.machineUsers FROM Machine m WHERE m.machineId = ?1")
    List<MachineUser> findAllPeopleByMachineId(Long machineId);
}
