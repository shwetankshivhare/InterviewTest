package com.shwetank.InterviewTest.q3.controller;

import com.shwetank.InterviewTest.q3.entity.BillEntity;
import com.shwetank.InterviewTest.q3.entity.BillResponse;
import com.shwetank.InterviewTest.q3.enums.DiscountEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount")
@Slf4j
public class Question3Controller {

    @PostMapping(path = "/bill")
    public ResponseEntity<?> checkDiscount(@RequestBody BillEntity billEntityRequest) {

        try {
            log.info("Payload Received ::"+billEntityRequest.toString());
            return new ResponseEntity<BillResponse>(applyDiscount(billEntityRequest), HttpStatus.OK);
        } catch (Exception e) {
            log.error("--> Error Occured for bill {} and error {}",billEntityRequest.getBillNo(),e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public BillResponse applyDiscount(BillEntity billEntity) {

        if (!billEntity.isGroceries()) {
            if (billEntity.isEmployee()) {
                billEntity.setTotalBillAmount(billEntity.getTotalBillAmount() - (billEntity.getTotalBillAmount() * DiscountEnum.ISEMPLOYEE.getDiscount()));
            } else if (billEntity.isAffiliate()) {
                billEntity.setTotalBillAmount(billEntity.getTotalBillAmount() - (billEntity.getTotalBillAmount() * DiscountEnum.ISAFFILIATE.getDiscount()));
            } else if (billEntity.getYearWithStore() > 2) {
                billEntity.setTotalBillAmount(billEntity.getTotalBillAmount() - (billEntity.getTotalBillAmount() * DiscountEnum.YEARSWITHSTORE.getDiscount()));
            }
        }
        // assuming - after percentage discount only need to put  fixed discount which is 5$ per 100$
        int multipleOfFive = (int)(billEntity.getTotalBillAmount() / 100);
        return BillResponse.builder()
                .billNo(billEntity.getBillNo())
                .amtAfterDiscount(billEntity.getTotalBillAmount() - (multipleOfFive * DiscountEnum.FIXEDAMTDISCOUNT.getDiscount()))
                .build();
    }


}
