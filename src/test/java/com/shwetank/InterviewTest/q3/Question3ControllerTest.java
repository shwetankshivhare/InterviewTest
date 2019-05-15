package com.shwetank.InterviewTest.q3;


import com.shwetank.InterviewTest.q3.controller.Question3Controller;
import com.shwetank.InterviewTest.q3.entity.BillEntity;
import com.shwetank.InterviewTest.q3.entity.BillResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class Question3ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testController() throws Exception{
        MvcResult mvcResult = mockMvc.perform(post("/discount/bill").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"billNo\" : \"1\",\"isEmployee\" : true,\"isAffiliate\" : false,\"isGroceries\" : false,\"yearWithStore\" : 2.0,\"totalBillAmount\" : 440.0}")
        ).andExpect(MockMvcResultMatchers.status()
                .isOk())
                .andReturn();
    }

    @Test
    public void applyDiscount_test(){

        Question3Controller question3Controller = new Question3Controller();
        BillResponse billResponse ;

        // 30% employee discount check
        billResponse = question3Controller.applyDiscount(BillEntity.builder().billNo("1").isEmployee(true).totalBillAmount(100.0).build());
        assertThat(billResponse.getAmtAfterDiscount() == 70.0);

        //10% affiliate discount
        billResponse = question3Controller.applyDiscount(BillEntity.builder().billNo("1").isAffiliate(true).totalBillAmount(100.0).build());
        assertThat(billResponse.getAmtAfterDiscount() == 90.0);

        //5% discount for loyal customers, 2 years and more
        billResponse = question3Controller.applyDiscount(BillEntity.builder().billNo("1").yearWithStore(2.1).totalBillAmount(100.0).build());
        assertThat(billResponse.getAmtAfterDiscount() == 95.0);

        //employee discount and bill over than 100
        billResponse = question3Controller.applyDiscount(BillEntity.builder().billNo("1").isEmployee(true).totalBillAmount(150.0).build());
        assertThat(billResponse.getAmtAfterDiscount() == 100.0);

    }
}
