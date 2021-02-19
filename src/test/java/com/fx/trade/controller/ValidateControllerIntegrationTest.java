package com.fx.trade.controller;


import com.fx.trade.FXTradeValidateApplication;
import com.fx.trade.bean.AbstractDTO;
import com.fx.trade.bean.ForwardDTO;
import com.fx.trade.bean.OptionDTO;
import com.fx.trade.bean.SpotDTO;
import com.fx.trade.bean.TradeDTOBuilder;
import com.fx.trade.converter.ConvertorUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FXTradeValidateApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class ValidateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private OptionDTO optionDTO;
    private SpotDTO spotDTO;
    private ForwardDTO forwardDTO;

    @Before
    public void setup() throws ParseException {
        optionDTO = new TradeDTOBuilder()
                .customer("YODA1")
                .currencyPair("EURUSD")
                .style("EUROPEAN")
                .direction("BUY")
                .strategy("CALL")
                .tradeDate(parseDate("2020-08-11"))
                .amount1(1000000l)
                .amount2(1120000l)
                .rate(1.12f)
                .deliveryDate(parseDate("2020-08-22"))
                .expiryDate(parseDate("2020-08-19"))
                .payCurency("USD")
                .premium(0.2f)
                .premiumCurency("USD")
                .premiumType("%USD")
                .premiumDate(parseDate("2020-08-12"))
                .legalEntity("UBS AG")
                .trader("Josef Schoenberger")
                .buildOption();


        spotDTO = new TradeDTOBuilder()
                .customer("YODA1")
                .currencyPair("EURUSD")
                .direction("BUY")
                .tradeDate(parseDate("2020-08-11"))
                .amount1(1000000l)
                .amount2(1120000l)
                .rate(1.12f)
                .valueDate(parseDate("2020-08-15"))
                .legalEntity("UBS AG")
                .trader("Josef Schoenberger")
                .buildSpot();

        forwardDTO = new TradeDTOBuilder()
                .customer("YODA1")
                .currencyPair("EURUSD")
                .direction("BUY")
                .tradeDate(parseDate("2020-08-11"))
                .amount1(1000000l)
                .amount2(1120000l)
                .rate(1.12f)
                .valueDate(parseDate("2020-08-22"))
                .legalEntity("UBS AG")
                .trader("Josef Schoenberger")
                .buildForward();
    }

    @Test
    public void whenPostValidDTO_thenGetValidateResponseTest() throws Exception {
        optionDTO.setDeliveryDate(parseDate("2020-08-25"));

        RequestBuilder request = post("/api/fxtrade/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ConvertorUtils.toJson(optionDTO));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid", is(true)));
    }

    @Test
    public void whenPostInvalidValidOptionDTO_thenGetBadRequestResponseTest() throws Exception {
        optionDTO.setDeliveryDate(parseDate("2020-08-22"));
        optionDTO.setStyle("AMERICAN");
        optionDTO.setExcerciseStartDate(parseDate("2021-07-22"));
        optionDTO.setCcyPair("ABCDEF");
        optionDTO.setPremiumDate(parseDate("2021-08-22"));
        optionDTO.setExpiryDate(parseDate("2021-07-22"));
        optionDTO.setPremiumCcy("USDE");
        optionDTO.setPayCcy("US");


        RequestBuilder request = post("/api/fxtrade/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ConvertorUtils.toJson(optionDTO));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid", is(false)))
                .andExpect(jsonPath("$.validateMessages", hasSize(equalTo(6))));
    }

    @Test
    public void whenPostOptionDTOWithInvalidStyle_thenGetBadRequestResponseTest() throws Exception {
        optionDTO.setStyle("ASIAN");
        optionDTO.setDeliveryDate(parseDate("2020-08-25"));


        RequestBuilder request = post("/api/fxtrade/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ConvertorUtils.toJson(optionDTO));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid", is(false)))
                .andExpect(jsonPath("$.validateMessages", hasSize(equalTo(1))));
    }

    @Test
    public void whenPostInvalidSpotDTO_thenGetBadRequestResponseTest() throws Exception {
        RequestBuilder request = post("/api/fxtrade/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ConvertorUtils.toJson(spotDTO));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid", is(false)))
                .andExpect(jsonPath("$.validateMessages", hasSize(equalTo(2))));
    }

    @Test
    public void whenPostInvalidForwardDTO_thenGetBadRequestResponseTest() throws Exception {
        forwardDTO.setCustomer("CUST");
        forwardDTO.setTradeDate(parseDate("2020-08-31"));

        RequestBuilder request = post("/api/fxtrade/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ConvertorUtils.toJson(forwardDTO));


        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid", is(false)))
                .andExpect(jsonPath("$.validateMessages", hasSize(equalTo(4))));
    }

    @Test
    public void whenPostBulkValidate_thenGetDifferentValidationResultsTest() throws Exception {
        AbstractDTO[] abstractDTOs= new AbstractDTO[]{optionDTO, spotDTO, forwardDTO};
        optionDTO.setDeliveryDate(parseDate("2020-08-25"));

        RequestBuilder request = post("/api/fxtrade/bulk/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ConvertorUtils.toJson(abstractDTOs));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].valid", is(true)))
                .andExpect(jsonPath("$[1].valid", is(false)))
                .andExpect(jsonPath("$[2].valid", is(false)));
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, dTF);
    }
}