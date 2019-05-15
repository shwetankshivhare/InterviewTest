package com.shwetank.InterviewTest.q3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@Builder
public class BillEntity {

    private String billNo;
    private boolean isEmployee;
    private boolean isAffiliate;
    private boolean isGroceries;
    private Double yearWithStore;
    private Double totalBillAmount;
}
