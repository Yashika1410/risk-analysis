package com.example.risklambda.dao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.risklambda.entity.Output;

/**
 * Output Dao class to perform operation on output table.
 */
public class OutputDao {
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

     * @param output output class object.
     */
    public void addOutput(final Output output) {
        dao.begin(Output.class);
        try {
            dao.getSession().save(output);
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

    /**
     * get list of output objects.

     * @return list of latest output objects.
     */
    public List<Output> getLatestOutputs() {
        dao.begin(Output.class);
        try {
            String sql = "SELECT * from "
                    + "(SELECT *,  ROW_NUMBER()"
                    + " over (PARTITION BY company_name ORDER BY"
                    + " timestamp desc) as rowss FROM output_table) a "
                    + "where a.rowss=1 order by a.id";
            List<Output> listOutputs = (List<Output>) dao.getSession()
                    .createNativeQuery(sql, Output.class).list();
            return listOutputs;
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
