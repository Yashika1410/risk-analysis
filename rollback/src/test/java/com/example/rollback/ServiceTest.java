package com.example.rollback;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.rollback.dao.TableBDao;
import com.example.rollback.entities.TableB;
import com.example.rollback.services.Service;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    /**
     *
     */
    @Mock
    private TableBDao tableBDao;
    /**
     *
     */
    @InjectMocks
    private Service service;
    

    // @BeforeEach
    // void initService() {
    //    var closeable = MockitoAnnotations.openMocks(this);
    // }

    // @AfterEach
    // void closeService() throws Exception {
    //     closeable.close();
    // }
    
    /**
     *
     */
    // @BeforeAll
    // void BeforeAll() {
    //     MockitoAnnotations.initMocks(this);
    // }

    /**
     *
     */
    @Test
    public void saveAllDataTest() {
        boolean result = service.saveAllData("testTable1", "testTable2");
        assertTrue(result);
    }

    

    /**
     *
     */
    @Test
    public void saveAllDataTest2() {
        Mockito.when(tableBDao.save("testTable4")).thenThrow(NullPointerException.class);
        boolean result2 = service.saveAllData("testTable3", "testTable4");
        assertFalse(result2);
    }

}
