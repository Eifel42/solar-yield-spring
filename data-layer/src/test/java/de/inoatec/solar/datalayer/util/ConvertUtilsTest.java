package de.inoatec.solar.datalayer.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ConvertUtilsTest {

    @Test
    void convertStringToTimestamp() {
        java.sql.Date date = ConvertUtils.convertStringToDate("11.11.2011 11.11.11");
        assertNotNull(date);
    }

    @Test
    void convertStringToBigDecimal() {
        BigDecimal big = ConvertUtils.convertStringToBigDecimal("11000.11");
        assertNotNull(big);
    }
}