package com.shwetank.InterviewTest.q3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BillResponse {

    private String billNo;
    private Double amtAfterDiscount;
}
