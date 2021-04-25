package de.inoatec.solar.datalayer.util;

import java.math.BigDecimal;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ConvertUtils {

    public static Date convertStringToDate(final String stringDate) {
        final String pattern = "dd.MM.YYYY";
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date;
        try {
            date = sdf.parse(stringDate);
        } catch (ParseException ex) {
            date = new java.util.Date();
        }
        return new java.sql.Date (date.getTime());
    }

    public static BigDecimal convertStringToBigDecimal(final String stringValue) {
        return new BigDecimal(stringValue);
    }

}
