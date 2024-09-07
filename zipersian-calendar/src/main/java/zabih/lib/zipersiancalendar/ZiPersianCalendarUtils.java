package zabih.lib.zipersiancalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;
import java.util.Set;

public class ZiPersianCalendarUtils {

	/**
	 * Persian (Shamsi) leap years from 1200 to 1600
	 **/
	static final Set<Integer> leapYears = Set.of(
			1201, 1205,
			1210, 1214, 1218, 1222, 1226, 1230, 1234, 1238, 1243,
			1247, 1251, 1255, 1259, 1263, 1267, 1271, 1276,
			1280, 1284, 1288, 1292, 1296, 1300, 1304, 1309,
			1313, 1317, 1321, 1325, 1329, 1333, 1337, 1342,
			1346, 1350, 1354, 1358, 1362, 1366, 1370, 1375,
			1379, 1383, 1387, 1391, 1395, 1399, 1403, 1408,
			1412, 1416, 1420, 1424, 1428, 1432, 1436, 1441,
			1445, 1449, 1453, 1457, 1461, 1465, 1469, 1474,
			1478, 1482, 1486, 1490, 1494, 1498,
			1502, 1507, 1511, 1515, 1519, 1523, 1527, 1531, 1535, 1540, 1544, 1548,
			1552, 1556, 1560, 1564, 1568, 1573, 1577, 1581, 1585, 1589, 1593, 1597
	);

	public static boolean isLeapYear(int year) {
		if (year >= 1200 && year <= 1500) {
			return leapYears.contains(year);
		}
		int remainder = year % 33;
		switch (remainder) {
			case 1:
			case 5:
			case 9:
			case 13:
			case 17:
			case 22:
			case 26:
			case 30:
				return true;
			default:
				return false;
		}
	}

	/**
	 * Author: JDF.SCR.IR
	 * License: GNU/LGPL _ Open Source & Free :: Version: 2.80 : [2020=1399]
	 *
	 * @param gy gregorian year
	 * @param gm gregorian month - start from 1 to 12
	 * @param gd gregorian day
	 */
	static int[] gregorian_to_jalali(int gy, int gm, int gd) {
		int[] out = {
				(gm > 2) ? (gy + 1) : gy,
				0,
				0
		};
		{
			int[] g_d_m = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
			out[2] = 355666 + (365 * gy) + ((int) ((out[0] + 3) / 4)) - ((int) ((out[0] + 99) / 100))
					+ ((int) ((out[0] + 399) / 400)) + gd + g_d_m[gm - 1];
		}
		out[0] = -1595 + (33 * ((int) (out[2] / 12053)));
		out[2] %= 12053;
		out[0] += 4 * ((int) (out[2] / 1461));
		out[2] %= 1461;
		if (out[2] > 365) {
			out[0] += (int) ((out[2] - 1) / 365);
			out[2] = (out[2] - 1) % 365;
		}
		if (out[2] < 186) {
			out[1] = 1 + (int) (out[2] / 31);
			out[2] = 1 + (out[2] % 31);
		} else {
			out[1] = 7 + (int) ((out[2] - 186) / 30);
			out[2] = 1 + ((out[2] - 186) % 30);
		}
		return out;
	}

	/**
	 * Author: JDF.SCR.IR
	 * License: GNU/LGPL _ Open Source & Free :: Version: 2.80 : [2020=1399]
	 *
	 * @param jy persian year
	 * @param jm persian month - start from 1 to 12
	 * @param jd persian day
	 */
	static int[] jalali_to_gregorian(int jy, int jm, int jd) {
		jy += 1595;
		int[] out = {
				0,
				0,
				-355668 + (365 * jy) + (((int) (jy / 33)) * 8) + ((int) (((jy % 33) + 3) / 4)) + jd + (
						(jm < 7) ? (jm - 1) * 31 : ((jm - 7) * 30) + 186)
		};
		out[0] = 400 * ((int) (out[2] / 146097));
		out[2] %= 146097;
		if (out[2] > 36524) {
			out[0] += 100 * ((int) (--out[2] / 36524));
			out[2] %= 36524;
			if (out[2] >= 365) {
				out[2]++;
			}
		}
		out[0] += 4 * ((int) (out[2] / 1461));
		out[2] %= 1461;
		if (out[2] > 365) {
			out[0] += (int) ((out[2] - 1) / 365);
			out[2] = (out[2] - 1) % 365;
		}
		int[] sal_a = {0, 31, ((out[0] % 4 == 0 && out[0] % 100 != 0) || (out[0] % 400 == 0)) ? 29 : 28,
				31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		for (out[2]++; out[1] < 13 && out[2] > sal_a[out[1]]; out[1]++) {
			out[2] -= sal_a[out[1]];
		}
		return out;
	}

	public static String getWeekdayName(int dayOfWeek, Locale locale) {
		boolean isPersian = locale.getLanguage().equals("fa");
		dayOfWeek -= 1;
		return isPersian
				? ZiPersianCalendarConstants.WEEKDAY_NAMES[dayOfWeek]
				: ZiPersianCalendarConstants.WEEKDAY_NAMES_SHORT_IN_ENGLISH[dayOfWeek];
	}

	/**
	 * @param month 0-based int - starts from 0 to 11
	 * @return the related month name
	 */
	public static String getMonthName(int month, Locale locale) {
		if (locale.getLanguage().equals("fa")) {
			return ZiPersianCalendarConstants.MONTH_NAMES[month];
		}
		return ZiPersianCalendarConstants.MONTH_NAMES_IN_ENGLISH[month];
	}

	@NonNull
	public static String formatToTwoDigits(int num, Locale locale) {
		return String.format(locale, "%02d", num);
	}

	/**
	 *
	 * @param year year
	 * @param month 0-based int - starts from 0 to 11
	 * @param day day
	 * @param dayOfWeek weekday
	 * @param locale locale
	 * @return A long date e.g.: چهارشنبه 01 فروردین 1403
	 */
	@NonNull
	public static String getLongDate(int year, int month, int day, int dayOfWeek, Locale locale) {
		return getWeekdayName(dayOfWeek, locale) + " " +
				formatToTwoDigits(day, locale) + " " +
				getMonthName(month, locale) + " " +
				formatToTwoDigits(year, locale);
	}

	/**
	 *
	 * @param year year
	 * @param month 0-based int - starts from 0 to 11
	 * @param day day
	 * @param dayOfWeek weekday
	 * @param hour hour
	 * @param minute minute
	 * @param second second
	 * @param locale locale
	 * @return A long date with time e.g.: چهارشنبه 01 فروردین 1403, 20:10:06
	 */
	@NonNull
	public static String getLongDateTime(
			int year, int month, int day, int dayOfWeek,
			int hour, int minute, int second, Locale locale) {
		return getLongDate(year, month, day, dayOfWeek, locale)+
				", " +
				formatToTwoDigits(hour, locale) + ":" +
				formatToTwoDigits(minute, locale) + ":" +
				formatToTwoDigits(second, locale);
	}


	/**
	 *
	 * @param year year
	 * @param month 0-based int - starts from 0 to 11
	 * @param day day
	 * @param delimiter delimiter
	 * @param locale locale
	 * @return date formatted by
	 * <p>'YYYY[delimiter]mm[delimiter]dd'</p>
	 * <p>e.g.: 1403/01/01</p>
	 */
	@NonNull
	public static String getShortDate(int year, int month, int day, String delimiter, Locale locale) {
		return formatToTwoDigits(year, locale) + delimiter +
				formatToTwoDigits(month + 1, locale) + delimiter +
				formatToTwoDigits(day, locale);
	}


	@Nullable
	public static ZiPersianCalendar parseOrNullToCompat(String dateString) {
		return parseOrNullToCompat(dateString, "/");
	}

	@Nullable
	public static ZiPersianCalendar parseOrNullToCompat(String dateString, String delimiter) {
		if (dateString == null || dateString.isEmpty()) {
			return null;
		}
		if (delimiter == null || delimiter.isEmpty() || !dateString.contains(delimiter)) {
			if (dateString.contains("/")) delimiter = "/";
			else if (dateString.contains("-")) delimiter = "-";
			else return null;
		}

		String[] tokens = dateString.split(delimiter);
		if (tokens.length != 3)
			return null;

		try {
			int year = Integer.parseInt(tokens[0].trim());
			int month = Integer.parseInt(tokens[1].trim());
			int day = Integer.parseInt(tokens[2].trim());
            return new ZiPersianCalendar(year, month - 1, day);
		}
		catch (Exception e) {
			return null;
		}
	}

}
