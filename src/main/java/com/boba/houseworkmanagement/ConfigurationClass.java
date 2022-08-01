package com.boba.houseworkmanagement;

import com.boba.houseworkmanagement.machineState.DishwasherRunningState;
import com.boba.houseworkmanagement.machineState.DishwasherToPackState;
import com.boba.houseworkmanagement.machineState.DishwasherToUnpackState;
import com.boba.houseworkmanagement.machineState.State;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Configuration
public class ConfigurationClass {

    public HashMap<String, State> dishwasherStates(DishwasherToPackState dishwasherToPackState,
                                                   DishwasherToUnpackState dishwasherToUnpackState,
                                                   DishwasherRunningState dishwasherRunningState) {
        var map = new HashMap<String, State>();
        map.put(dishwasherToUnpackState.getName(), dishwasherToUnpackState);
        map.put(dishwasherRunningState.getName(), dishwasherRunningState);
        map.put(dishwasherToPackState.getName(), dishwasherToPackState);

        return map;
    }
}
