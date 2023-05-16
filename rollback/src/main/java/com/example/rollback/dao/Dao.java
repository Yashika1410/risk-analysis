package com.example.rollback.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao class fo session begin close and roll back.
 */
public class Dao {
  /**
   * logger object which is used to log.
   */
  private final Logger log = LoggerFactory.getLogger(Dao.class);
  /**
   * configuration object.
   */
  private Configuration conf;
  /**
   * session factory object.
   */
  private SessionFactory sessionFactory;
  /**
   * session object.
   */
  private Session session;

  /**
   * to get session object.
   *
   * @return session object.
   */
  public Session getSession() {
    return session;

  }

  /**
   * to begin transaction.
   *
   * @param clazz type of class.
   */
  @SuppressWarnings("rawtypes")
  public void begin(final Class clazz) {
    conf = new Configuration().configure().addAnnotatedClass(clazz);

    conf.setProperty("hibernate.connection.url",
    "jdbc:mysql://localhost:3306/testrollback");
        // System.getenv("DATABASE_URL"));

    conf.setProperty("hibernate.connection.username",
    "root");
        // System.getenv("DATABASE_USER_NAME"));

    conf.setProperty("hibernate.connection.password",
    "root");
        // System.getenv("DATABASE_USER_PASSWORD"));

    sessionFactory = conf.buildSessionFactory();
    session = sessionFactory.openSession();
    session.beginTransaction();

  }

  /**
   * commit the changes in db.
   */
  public void commit() {
    log.info("Committing transaction.");
    session.getTransaction().commit();

  }

  /**
   * close the session.
   */
  public void close() {
    log.info("Closing session.");
    session.close();

  }

  /**
   * roll back all the changes or operations.
   */
  public void rollback() {
    log.info("Rollback transaction.");
    session.getTransaction().rollback();

  }
}
