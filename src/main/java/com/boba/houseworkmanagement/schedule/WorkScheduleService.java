package com.boba.houseworkmanagement.schedule;

import com.boba.houseworkmanagement.machine.Machine;
import com.boba.houseworkmanagement.user.MachineUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;


    public WorkScheduleService(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }

    public WorkSchedule getUserHousework(Long machineUserId) {
        return workScheduleRepository.findByAttenderMachineUserId(machineUserId)
                                     .orElseThrow();
    }

    public WorkSchedule getMachineHousework(Long machineId) {
        return workScheduleRepository.findByMachineMachineId(machineId)
                                     .orElseThrow();
    }

    public void addHousework(String machineUserUsername, Long machineId) {
        var machine = workScheduleRepository.findMachineById(machineId)
                                            .orElseThrow();

        var user = machine.getMachineUsers()
                          .stream()
                          .filter(m -> m.getUsername()
                                        .equals(machineUserUsername))
                          .findAny()
                          .orElseThrow();


        var workSchedule = new WorkSchedule(user, machine, LocalDateTime.now());
        workScheduleRepository.save(workSchedule);
    }

    public void updateWorkSchedule(WorkSchedule workSchedule) {
        workScheduleRepository.save(workSchedule);
    }

    public void updateWorkSchedule(MachineUser machineUser, Machine machine, LocalDateTime userNotificationDate) {
        var workSchedule = workScheduleRepository.findByMachineMachineId(machine.getMachineId())
                                                 .orElseThrow();

        workSchedule.setAttender(machineUser);
        workSchedule.setUserNotificationDate(userNotificationDate);
        workScheduleRepository.save(workSchedule);
    }

    public MachineUser getCurrentAttender(Machine machine) {
        var workSchedule = workScheduleRepository.findByMachineMachineId(machine.getMachineId())
                                                 .orElseThrow();

        return workSchedule.getAttender();
    }
}
