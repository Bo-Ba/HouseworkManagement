package com.boba.houseworkmanagement;

import com.boba.houseworkmanagement.machine.Machine;
import com.boba.houseworkmanagement.user.MachineUser;

import com.boba.houseworkmanagement.user.MachineUserRepository;
import com.boba.houseworkmanagement.user.MachineUserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MachineUserServiceUnitTests {

    @Mock
    private MachineUserRepository machineUserRepository;
    @InjectMocks
    private MachineUserService machineUserService;

    private static List<MachineUser> userList;

    @BeforeAll
    public static void init() {
        userList = new ArrayList<>();
        userList.add(new MachineUser(1L, "BoBa"));
        userList.add(new MachineUser(2L, "Steve"));

        userList.get(0)
                .addNewUserMachine(new Machine(1L, "Dishwasher Kitchen", "Not important"));
        userList.get(0)
                .addNewUserMachine(new Machine(2L, "Dishwasher Bathroom", "Not important"));
    }

    @Test
    public void addNewMachineUsersTestWithDuplicatedUser() {
        var newUser = new MachineUser(3L, "BoBa");
        given(machineUserRepository.findUserByUsername(newUser.getUsername())).willReturn(Optional.of(userList.get(0)));

        assertThrows(IllegalArgumentException.class, () -> machineUserService.addNewMachineUsers(newUser));
        verify(machineUserRepository, never()).save(any());
    }

    @Test
    public void addNewMachineUsersTestCorrect() {
        var newUser = new MachineUser(3L, "BoBa");
        given(machineUserRepository.findUserByUsername(newUser.getUsername())).willReturn(Optional.empty());

        machineUserService.addNewMachineUsers(newUser);

        verify(machineUserRepository).save(newUser);
    }

    @Test
    public void getUserByUsernameTestNotFound() {
        String username = "Not in the base";
        given(machineUserRepository.findUserByUsername(username)).willReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> machineUserService.getUserByUsername(username));
        verify(machineUserRepository, never()).save(any());
    }

    @Test
    public void getUserMachines() {
        Long bobaId = 1L;
        Long steveId = 2L;
        Long unknown = 3L;

        machineUserService.getUserMachines(bobaId);
        machineUserService.getUserMachines(steveId);
        machineUserService.getUserMachines(unknown);

        verify(machineUserRepository, times(3)).findAllMachinesByPersonId(any());
    }

    @Test
    public void getUserByIdTestNotFound() {
        Long notInBase = 3L;
        given(machineUserRepository.findById(notInBase)).willReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> machineUserService.getUserById(notInBase));
    }
}
