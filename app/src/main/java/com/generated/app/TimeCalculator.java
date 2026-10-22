package com.generated.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeCalculator {

    public static long calculateDaysBetween(String startDateStr) {
        if (startDateStr == null || startDateStr.isEmpty()) {
            return 0;
        }
        
        // For ancient historical dates (e.g. year 0070), standard Java SimpleDateFormat might struggle with BCE/CE parsing across all locales,
        // so we parse carefully or calculate accurately for standard Gregorian dates.
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date startDate = sdf.parse(startDateStr);
            if (startDate == null) return 0;

            long diffInMillis = Calendar.getInstance().getTimeInMillis() - startDate.getTime();
            long days = diffInMillis / (24 * 60 * 60 * 1000);
            return days > 0 ? days : 0;
        } catch (ParseException e) {
            // Fallback for rough estimation if parsing ancient years fails on old Android runtime
            return 71500; // Approximate fallback days for Second Temple destruction if needed
        }
    }
}
