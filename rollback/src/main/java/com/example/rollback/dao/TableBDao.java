package com.example.rollback.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.rollback.entities.TableB;

public class TableBDao {
    /**
     * dao class object for session.
     */
    private Dao dao = new Dao();
    /**
     * logger object which is used to log.
     */
    private final Logger log = LoggerFactory.getLogger(TableADao.class);
    /**
     * function which is used to save new objects to table.
     * 
     * @param data tableA class object.
     */
    public TableB save(final TableB data) {
        dao.begin(TableB.class);
        try {
            TableB tableB = dao.getSession().get(
                    TableB.class,
                    dao.getSession().save(data));
            dao.commit();
            return tableB;
        } catch (Throwable ex) {
            if (dao.getSession().getTransaction() != null) {
                dao.rollback();
            }
            log.error("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            dao.close();
        }
    }

    public void delete(int id){
        dao.begin(TableB.class);
        try {
            dao.getSession().delete(dao.getSession().load(TableB.class, id));
            dao.commit();

        } catch (Throwable ex) {
            if (dao.getSession().getTransaction() != null)
                dao.rollback();
            log.error("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            dao.close();
        }
    }
}
