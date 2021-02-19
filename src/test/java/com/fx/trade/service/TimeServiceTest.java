package com.fx.trade.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
public class TimeServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public TimeService employeeService() {
            return new TimeService();
        }
    }

    @Autowired
    private TimeService timeService;

    public boolean validateExcerciseStartDate(LocalDate tradeDate, LocalDate excerciseStartDate, LocalDate expiryDate) {
        return excerciseStartDate.isAfter(tradeDate) && excerciseStartDate.isBefore(expiryDate);
    }

    @Test
    public void whenInvalidExcerciseStartDate_thenResultIsFalseTest() {
        LocalDate tradeDate = parseDate("2021-02-18");
        LocalDate excerciseStartDate = parseDate("2021-02-18");
        LocalDate expiryDate = parseDate("2021-02-23");

        assertFalse("Wrong excerciseStartDate", timeService.validateExcerciseStartDate(tradeDate, excerciseStartDate, expiryDate));
    }

    @Test
    public void whenDateIsFriday_thenIsWorkingDateReturnTrueTest() {
        LocalDate friday = parseDate("2021-02-19");

        assertTrue("Wrong excerciseStartDate", timeService.isWorkingDate(friday));
    }

    @Test
    public void whenExpireAndPremiumDateAreBeforeDeliveryDate_thenResultIsTrueTest() {
        LocalDate expiryDate = parseDate("2021-02-18");
        LocalDate premiumDate = parseDate("2021-02-18");
        LocalDate deliveryDate = parseDate("2021-02-23");

        assertTrue("Wrong excerciseStartDate", timeService.validExpiryAndPremiumDates(expiryDate, premiumDate, deliveryDate));
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, dTF);
    }
}
