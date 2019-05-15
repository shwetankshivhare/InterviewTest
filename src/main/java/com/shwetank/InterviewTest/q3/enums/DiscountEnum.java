package com.shwetank.InterviewTest.q3.enums;

public enum DiscountEnum {

    ISEMPLOYEE(0.3),
    ISAFFILIATE(0.1),
    YEARSWITHSTORE(0.05),
    FIXEDAMTDISCOUNT(5.0);

    private Double discount;

    DiscountEnum(Double discount) {
        this.discount = discount;
    }

    public Double getDiscount() {
        return this.discount;
    }
}
