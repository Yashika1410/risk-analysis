package com.example.risklambda.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.risklambda.entity.JobTrasactions;

/**
 * Trasaction Dao class to perform operation on trasaction table.
 */
public class TransactionsDao {
    /**
     * dao class object for session.
     */
    private Dao dao = new Dao();
    /**
     * logger object which is used to log.
     */
    private final Logger log = LoggerFactory.getLogger(OutputDao.class);

    /**
     * function which is used to add new objects to table.

     * @param jobTrasactions new trasaction job object.
     * @return JobTrasactions object.
     */
    public JobTrasactions addTrasaction(final JobTrasactions jobTrasactions) {
        dao.begin(JobTrasactions.class);
        try {

            JobTrasactions jTrasactions = dao.getSession().get(
                    JobTrasactions.class,
                    dao.getSession().save(jobTrasactions));
            dao.commit();
            return jTrasactions;

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

     * @param jobTrasactions existing object which needs to be update.
     */
    public void updateTrasaction(
            final JobTrasactions jobTrasactions) {
        dao.begin(JobTrasactions.class);
        try {

            dao.getSession().update(jobTrasactions);
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
