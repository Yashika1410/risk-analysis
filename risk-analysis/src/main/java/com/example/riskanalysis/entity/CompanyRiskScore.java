package com.example.riskanalysis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "company_risk_score")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CompanyRiskScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyId;
    @Column(name = "company_name", nullable = false)
    @NonNull
    private String companyName;
    @Column(name = "information_security")
    @NonNull
    private double informationSecurity;
    @Column(name = "resilince")
    @NonNull
    private double resilience;
    @Column(name = "conduct")
    @NonNull
    private double conduct;

}
