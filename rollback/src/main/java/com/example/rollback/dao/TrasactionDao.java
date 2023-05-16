package com.example.rollback.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.rollback.entities.Trasaction;

public class TrasactionDao {
    /**
     * dao class object for session.
     */
    private Dao dao = new Dao();
    /**
     * logger object which is used to log.
     */
    private final Logger log = LoggerFactory.getLogger(TrasactionDao.class);

    /**
     * function which is used to save new objects to table.
     * 
     * @param data new trasaction job object.
     * @return Trasaction object.
     */
    public Trasaction save(final Trasaction data) {
        dao.begin(Trasaction.class);
        try {

            Trasaction trasaction = dao.getSession().get(
                    Trasaction.class,
                    dao.getSession().save(data));
            dao.commit();
            return trasaction;

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

    /**
     * function which is used to update existing objects to table.
     * 
     * @param jobTrasactions existing object which needs to be update.
     */
    public void updateTrasaction(
            final Trasaction trasaction) {
        dao.begin(Trasaction.class);
        try {

            dao.getSession().update(trasaction);
            dao.commit();

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
}
