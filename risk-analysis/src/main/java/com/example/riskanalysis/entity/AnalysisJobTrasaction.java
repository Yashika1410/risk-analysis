package com.example.riskanalysis.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "analysis_job_trasactions")
public class AnalysisJobTrasaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "started_at")
    private Timestamp startedAt;
    @Column(name = "ended_at")
    private Timestamp endedAt;
    @Column(name = "trigger_by")
    private String triggerBy;
    @Column(name = "status")
    private String status;

}
