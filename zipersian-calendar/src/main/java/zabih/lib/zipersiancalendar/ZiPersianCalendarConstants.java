package zabih.lib.zipersiancalendar;

import android.os.Build;

import java.util.Locale;

public class ZiPersianCalendarConstants {

    public static final Locale PERSIAN_LOCALE = new Locale("fa", "IR");

    public static final Locale PERSIAN_LOCALE_WITH_EN_DIGITS =
            (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    ? Locale.forLanguageTag("fa-IR-u-nu-latn")
                    : PERSIAN_LOCALE;

    public static final String[] WEEKDAY_NAMES = {
            "یک‌شنبه", "دوشنبه", "سه‌شنبه", "چهارشنبه", "پنج‌شنبه", "جمعه", "شنبه"
    };

    public static final String[] WEEKDAY_NAMES_SHORT = {
            "ی", "د", "س", "چ", "پ", "ج", "ش"
    };

    public static final String[] MONTH_NAMES = {
            "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور",
            "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"
    };

    public static final String[] WEEKDAY_NAMES_IN_ENGLISH = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    public static final String[] WEEKDAY_NAMES_SHORT_IN_ENGLISH = {
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };

    public static final String[] MONTH_NAMES_IN_ENGLISH = {
            "Farvardin", "Ordibehesht", "Khordad", "Tir", "Mordad", "Shahrivar",
            "Mehr", "Aban", "Azar", "Dey", "Bahman", "Esfand"
    };

}
