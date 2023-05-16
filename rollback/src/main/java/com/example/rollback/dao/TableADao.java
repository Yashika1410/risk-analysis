package com.example.rollback.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.rollback.entities.TableA;

public class TableADao {
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
    public TableA save(final TableA data) {
        dao.begin(TableA.class);
        try {
            TableA tableA = dao.getSession().get(
                    TableA.class,
                    dao.getSession().save(data));
            dao.commit();
            return tableA;
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

    public void delete(int id) {
        dao.begin(TableA.class);
        try {
            dao.getSession().delete(dao.getSession().load(TableA.class, id));
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
