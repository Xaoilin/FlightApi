package com.travix.medusa.busyflights.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
    private DateUtils() {}

    public static String convertLocalDateTimeToIsoLocalDate(String date) {
        return LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(date)).toString();
    }

    public static String convertIsoInstantToIsoLocalDate(String date) {
        return LocalDate.from(DateTimeFormatter.ISO_INSTANT.parse(date)).toString();
    }
}
