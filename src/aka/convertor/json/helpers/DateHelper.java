package aka.convertor.json.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

/**
 * Helper to check if string can be a date
 *
 * @author Charlotte
 *
 */
public class DateHelper {

	private static TimeZone TIMEZONES[] = { TimeZone.getTimeZone("UTC"), TimeZone.getTimeZone("America/Los_Angeles"), TimeZone.getTimeZone("PST"), TimeZone.getTimeZone("Europe/Warsaw"),
	        TimeZone.getTimeZone("CET"), };

	public static Map<String, String> dateNameFormatMap = new HashMap<>();
	static {
		dateNameFormatMap.put("DateYearMonthDaySlash", "yyyy/MM/dd");
		dateNameFormatMap.put("DateYearMonthDayDash", "yyyy-MM-dd");
		dateNameFormatMap.put("DateYearMonthDayDot", "yyyy.MM.dd");
		dateNameFormatMap.put("DateDayMonthYearSlash", "dd/MM/yyyy");
		dateNameFormatMap.put("DateDayMonthYearDash", "dd-MM-yyyy");
		dateNameFormatMap.put("DateDayMonthYearDot", "dd.MM.yyyy");
		dateNameFormatMap.put("DateYearMonthDayTHourSlash", "yyyy/MM/dd'T'HH:mm:ss");
		dateNameFormatMap.put("DateYearMonthDayTHourDash", "yyyy-MM-dd'T'HH:mm:ss");
		dateNameFormatMap.put("DateYearMonthDayTHourDot", "yyyy.MM.dd'T'HH:mm:ss");
		dateNameFormatMap.put("DateYearMonthDayTHourDotTimeZone", "yyyy.MM.dd'T'HH:mm:ssZ");
		dateNameFormatMap.put("DateYearMonthDayTHourDashTimeZone", "yyyy-MM-dd'T'HH:mm:ssZ");
		dateNameFormatMap.put("DateDayMonthYearTHourSlash", "dd/MM/yyyy'T'HH:mm:ss");
		dateNameFormatMap.put("DateDayMonthYearTHourDash", "dd-MM-yyyy'T'HH:mm:ss");
		dateNameFormatMap.put("DateDayMonthYearTHourDot", "dd.MM.yyyy'T'HH:mm:ss");
		dateNameFormatMap.put("DateYearMonthDayHourSlash", "yyyy/MM/dd HH:mm:ss");
		dateNameFormatMap.put("DateYearMonthDayHourDash", "yyyy-MM-dd HH:mm:ss");
		dateNameFormatMap.put("DateYearMonthDayHourDot", "yyyy.MM.dd HH:mm:ss");
		dateNameFormatMap.put("DateDayMonthYearHourSlash", "dd/MM/yyyy HH:mm:ss");
		dateNameFormatMap.put("DateDayMonthYearHourDash", "dd-MM-yyyy HH:mm:ss");
		dateNameFormatMap.put("DateDayMonthYearHourDot", "dd.MM.yyyy HH:mm:ss");
		dateNameFormatMap.put("DateYear", "yyyy");
	}

	private DateHelper() {
		// singleton
	}

	/**
	 * @param value
	 *            String to parse
	 * @return format of date, null if not exist
	 */
	public static String parseDate(final String value) {
		for (final Entry<String, String> entry : dateNameFormatMap.entrySet()) {
			final String pattern = entry.getValue();
			final String name = entry.getKey();
			final boolean d = parseWithPatterns(value, pattern);
			if (d) {
				return name;
			}
		}
		return null;
	}

	private static boolean parseWithPatterns(final String s, final String pattern) {
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			final Date date = sdf.parse(s);
			if (pattern.contains("Z")) {
				for (final TimeZone timeZone : TIMEZONES) {
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
