package com.boba.houseworkmanagement.schedule;

import com.boba.houseworkmanagement.dto.UserMachineIdsDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "schedule")
public class WorkScheduleController {
    private final WorkScheduleService workScheduleService;

    public WorkScheduleController(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    @GetMapping(path = "housework/user/{machineUserId}")
    public WorkSchedule getUserHousework(@PathVariable  Long machineUserId) {
        return workScheduleService.getUserHousework(machineUserId);
    }

    @GetMapping(path = "housework/machine/{machineId}")
    public WorkSchedule getMachineHousework(@PathVariable  Long machineId) {
        return workScheduleService.getMachineHousework(machineId);
    }

    @PostMapping
    public void addHouseWork(@RequestBody UserMachineIdsDto userMachineIdsDto) {
        workScheduleService.addHousework(userMachineIdsDto.getMachineUserUsername(), userMachineIdsDto.getMachineId());
    }

}
