package com.example.kadastermock;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(KadasterMockApplication.class);

    @Test
    void verifyModularity(){
        System.out.println(modules.toString());
    }
}
