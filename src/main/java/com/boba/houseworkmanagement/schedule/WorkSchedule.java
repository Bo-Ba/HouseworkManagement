package com.boba.houseworkmanagement.schedule;

import com.boba.houseworkmanagement.machine.Machine;
import com.boba.houseworkmanagement.user.MachineUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class WorkSchedule {

    @Id
    @SequenceGenerator(
            name = "schedule_sequence",
            sequenceName = "schedule_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "schedule_sequence"
    )
    private Long scheduleId;


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "MachineUserId")
    private MachineUser attender;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "MachineId")
    private Machine machine;

    @Getter
    @Setter
    private LocalDateTime userNotificationDate;

    public WorkSchedule(MachineUser attender, Machine machine, LocalDateTime userNotificationDate) {
        this.attender = attender;
        this.machine = machine;
        this.userNotificationDate = userNotificationDate;
    }

    public WorkSchedule() {}
}
