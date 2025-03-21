package com.fullstack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custId;

    private String custName;

    private String custAddress;

    @Column(unique = true)
    private long custContactNumber;

    private double custAccountBalance;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date custDOB;

    @Column(unique = true)
    private String custEmailId;

    private String custPassword;

    @Column(name = "Deleted",nullable = false)
    private boolean deleted = false; //default : Not deleted

}
