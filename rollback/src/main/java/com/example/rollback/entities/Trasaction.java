package com.example.rollback.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "trasaction")
@Data
public class Trasaction {
    /**
     * Unique id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * timestamp start at.
     */
    @Column(name = "started_at")
    private Timestamp startedAt;
    /**
     * timestamp that represent process end time.
     */
    @Column(name = "ended_at")
    private Timestamp endedAt;
    /**
     * variable that store final status of the job.
     */
    @Column(name = "status")
    private String status;
}
