package com.example.rollback.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.rollback.dao.TableADao;
import com.example.rollback.dao.TableBDao;
import com.example.rollback.dao.TrasactionDao;
import com.example.rollback.entities.TableA;
import com.example.rollback.entities.TableB;
import com.example.rollback.entities.Trasaction;

public class Service {
    /**
     * logger object which is used to log.
     */
    private final Logger log = LoggerFactory.getLogger(Service.class);
    public boolean saveAllData(String tableAName,String tableBName){
    TableADao tableADao = new TableADao();
    TableBDao tableBDao = new TableBDao();
    TrasactionDao trasactionDao = new TrasactionDao();
    TableA newTableA = null;
    TableB newTableB = null;
    Trasaction newTrasaction = new Trasaction();
    newTrasaction.setStartedAt(
            new Timestamp(System.currentTimeMillis()));
    newTrasaction.setStatus("Started");
    Trasaction trasaction = trasactionDao.save(
            newTrasaction);
    try {
        TableA tableA = new TableA();
        TableB tableB = new TableB();
        tableA.setName(tableAName);
        tableB.setName(tableBName);
        newTableA = tableADao.save(tableA);
        newTableB = tableBDao.save(tableB);
        trasaction.setEndedAt(
                new Timestamp(System.currentTimeMillis()));
        trasaction.setStatus("Completed");
        trasactionDao.updateTrasaction(trasaction);
        return true;
    } catch (Exception e) {
        if (newTableA != null && newTableB == null){
            tableADao.delete(newTableA.getId());
            log.info("Save TableB failed");
        }
        else if(newTableA==null && newTableB == null){
            log.info("Save TableA and TableB both are failed");
        }
        log.info(e.getMessage());
        trasaction.setEndedAt(
                new Timestamp(System.currentTimeMillis()));
        trasaction.setStatus("Failed");
        trasactionDao.updateTrasaction(trasaction);
        return false;
    }
    }
}
