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
 *
 */
public class DateHelper {

    private static TimeZone TIMEZONES[] = { TimeZone.getTimeZone("UTC"), TimeZone.getTimeZone("America/Los_Angeles"), TimeZone.getTimeZone("PST"), TimeZone.getTimeZone("Europe/Warsaw"),
            TimeZone.getTimeZone("CET"), };

    @NonNull
    public static Map<@NonNull String, @NonNull String> dateNameFormatMap = new HashMap<>();

    static {
        DateHelper.dateNameFormatMap.put("DateYearMonthDaySlash", "yyyy/MM/dd");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayDash", "yyyy-MM-dd");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayDot", "yyyy.MM.dd");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearSlash", "dd/MM/yyyy");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearDash", "dd-MM-yyyy");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearDot", "dd.MM.yyyy");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayTHourSlash", "yyyy/MM/dd'T'HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayTHourDash", "yyyy-MM-dd'T'HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayTHourDot", "yyyy.MM.dd'T'HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayTHourDotTimeZone", "yyyy.MM.dd'T'HH:mm:ssZ");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayTHourDashTimeZone", "yyyy-MM-dd'T'HH:mm:ssZ");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearTHourSlash", "dd/MM/yyyy'T'HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearTHourDash", "dd-MM-yyyy'T'HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearTHourDot", "dd.MM.yyyy'T'HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayHourSlash", "yyyy/MM/dd HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayHourDash", "yyyy-MM-dd HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateYearMonthDayHourDot", "yyyy.MM.dd HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearHourSlash", "dd/MM/yyyy HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearHourDash", "dd-MM-yyyy HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateDayMonthYearHourDot", "dd.MM.yyyy HH:mm:ss");
        DateHelper.dateNameFormatMap.put("DateYear", "yyyy");
    }

    private DateHelper() {
        // singleton
    }

    /**
     * @param value
     *            String to parse
     * @return format of date, null if not exist
     */
    @Nullable
    public static String parseDate(@NonNull final String value) {
        for (final Entry<@NonNull String, @NonNull String> entry : DateHelper.dateNameFormatMap.entrySet()) {
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
        } catch (final ParseException e) {
            return false;
        }
        return false;
    }
}
