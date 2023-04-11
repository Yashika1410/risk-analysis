package com.example.riskanalysis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyId;
    /**
     *
     */
    @Column(name = "company_name", nullable = false)
    @NonNull
    @NotNull(message = "Company Name is mandatory")
    @NotEmpty(message = "Company Name is mandatory")
    private String companyName;
    /**
     *
     */
    @Column(name = "information_security")
    @NonNull
    @NotEmpty(message = "information security is mandatory")
    @NotNull(message = "information security is mandatory")
    private double informationSecurity;
    /**
     *
     */
    @Column(name = "resilince")
    @NonNull
    @NotEmpty(message = "resilience is mandatory")
    @NotNull(message = "resilience is mandatory")
    private double resilience;
    /**
     *
     */
    @Column(name = "conduct")
    @NonNull
    @NotEmpty(message = "Conduct is mandatory")
    @NotNull(message = "Conduct is mandatory")
    private double conduct;

}
