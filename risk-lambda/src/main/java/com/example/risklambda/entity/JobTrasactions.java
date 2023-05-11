package com.example.risklambda.entity;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity class which is used to represent transaction table.
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "analysis_job_trasactions")
public class JobTrasactions {
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

    /**
     * collection that stores failed companies id.
     */
    @ElementCollection
    private Set<Integer> listOfFailedCompaniesId;
    /**
     * variable that stores count of total companies.
     */
    @Column(name = "total_companies")
    private long totalCompanies;
    /**
     * variable that stores count of processed companies.
     */
    @Column(name = "processed_companies")
    private long processedCompanies;
}
