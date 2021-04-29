package sample.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private static final String date_pattern = "yyyy-MM-dd";
    private static final String time_pattern = "HH:mm";
    private static final String date_time_pattern = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern(date_pattern);
    private static final DateTimeFormatter time_formatter = DateTimeFormatter.ofPattern(time_pattern);
    private static final DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern(date_time_pattern);

    public static String dateToString(LocalDate date) { return date_formatter.format(date); }

    public static String timeToString(LocalDate date) { return time_formatter.format(date); }

    public static LocalDate toDateFormat (String date, String time){
        String date_time = date + " " + time;
        LocalDate new_date_time = LocalDate.parse(date_time, date_time_formatter);
        return new_date_time;
    }

    public static LocalDate toDateFormat (String date_time){
        LocalDate new_date_time = LocalDate.parse(date_time, date_time_formatter);
        return new_date_time;
    }
}
