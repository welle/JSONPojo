package aka.convertor.json.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Helper to check if string can be a date
 *
 * @author Charlotte
 */
public class DateHelper {

    private static TimeZone TIMEZONES[] = { TimeZone.getTimeZone("UTC"), TimeZone.getTimeZone("America/Los_Angeles"), TimeZone.getTimeZone("PST"), TimeZone.getTimeZone("Europe/Warsaw"),
            TimeZone.getTimeZone("CET"), };

    /**
     * Map to match date format.
     */
    @NonNull
    public static Map<@NonNull String, @NonNull String> DATENAMEFORMATMAP = new HashMap<>();

    static {
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDaySlash", "yyyy/MM/dd");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayDash", "yyyy-MM-dd");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayDot", "yyyy.MM.dd");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearSlash", "dd/MM/yyyy");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearDash", "dd-MM-yyyy");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearDot", "dd.MM.yyyy");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayTHourSlash", "yyyy/MM/dd'T'HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayTHourDash", "yyyy-MM-dd'T'HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayTHourDot", "yyyy.MM.dd'T'HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayTHourDotTimeZone", "yyyy.MM.dd'T'HH:mm:ssZ");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayTHourDashTimeZone", "yyyy-MM-dd'T'HH:mm:ssZ");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearTHourSlash", "dd/MM/yyyy'T'HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearTHourDash", "dd-MM-yyyy'T'HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearTHourDot", "dd.MM.yyyy'T'HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayHourSlash", "yyyy/MM/dd HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayHourDash", "yyyy-MM-dd HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateYearMonthDayHourDot", "yyyy.MM.dd HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearHourSlash", "dd/MM/yyyy HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearHourDash", "dd-MM-yyyy HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateDayMonthYearHourDot", "dd.MM.yyyy HH:mm:ss");
        DateHelper.DATENAMEFORMATMAP.put("DateYear", "yyyy");
    }

    private DateHelper() {
        // singleton
    }

    /**
     * Parse the given date.
     *
     * @param value String to parse
     * @return format of date, null if not exist
     */
    @Nullable
    public static String parseDate(@NonNull final String value) {
        for (final Entry<@NonNull String, @NonNull String> entry : DateHelper.DATENAMEFORMATMAP.entrySet()) {
            final String pattern = entry.getValue();
            final String name = entry.getKey();
            final boolean d = DateHelper.parseWithPatterns(value, pattern);
            if (d) {
                return name;
            }
        }
        return null;
    }

    private static boolean parseWithPatterns(@NonNull final String s, @NonNull final String pattern) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            final Date date = sdf.parse(s);
            if (pattern.contains("Z")) {
                for (final TimeZone timeZone : DateHelper.TIMEZONES) {
                    sdf.setTimeZone(timeZone);
                    if (s.equals(sdf.format(date))) {
                        return true;
                    }
                }
            } else {
                return s.equals(sdf.format(date));
            }
        } catch (@SuppressWarnings("unused") final ParseException e) {
            return false;
        }
        return false;
    }
}
