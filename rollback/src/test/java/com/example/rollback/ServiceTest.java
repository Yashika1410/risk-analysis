package com.example.rollback;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.example.rollback.services.Service;

public class ServiceTest {

    @Test
    public void saveAllDataTest(){
        Service service = new Service();
        boolean result = service.saveAllData("testTable1", "testTable2");
        assertTrue(result);
    }

    @Test
    public void saveAllDataTest2(){
        boolean result2 = service.saveAllData("testTable3", "testTable4");
    }
    
}
