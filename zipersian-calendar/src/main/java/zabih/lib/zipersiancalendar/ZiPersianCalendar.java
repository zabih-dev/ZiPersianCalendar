package zabih.lib.zipersiancalendar;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Persian Calendar class for managing and manipulating Persian (Jalali) dates.
 *
 * @author Zabih Atashbarg
 * @created 1 Shahrivar 1403
 *
 * License: Apache License 2.0
 */
public class ZiPersianCalendar extends Calendar {

  public static final int FIRST_DAY_OF_WEEK = Calendar.SATURDAY;

  public static final int WEEKDAY_HOLIDAY_NUMBER = Calendar.FRIDAY;

  /** The 0-based month **/
  public static final int MONTH_FARVARDIN = 0;
  public static final int MONTH_ORDIBEHESHT = 1;
  public static final int MONTH_KHORDAD = 2;
  public static final int MONTH_TIR = 3;
  public static final int MONTH_MORDAD = 4;
  public static final int MONTH_SHAHRIVAR = 5;
  public static final int MONTH_MEHR = 6;
  public static final int MONTH_ABAN = 7;
  public static final int MONTH_AZAR = 8;
  public static final int MONTH_DEY = 9;
  public static final int MONTH_BAHMAN = 10;
  public static final int MONTH_ESFAND = 11;

  protected final GregorianCalendar gCal;
  protected Locale locale;

  public ZiPersianCalendar() {
    this(TimeZone.getDefault());
  }

  public ZiPersianCalendar(TimeZone zone) {
    this(zone, ZiPersianCalendarConstants.PERSIAN_LOCALE_WITH_EN_DIGITS);
  }

  public ZiPersianCalendar(@NonNull TimeZone zone, @NonNull Locale locale) {
    this(zone, locale, true);
  }

  public ZiPersianCalendar(int year, int month, int dayOfMonth) {
    this(year, month, dayOfMonth, 0, 0, 0, 0);
  }

  public ZiPersianCalendar(int year, int month, int dayOfMonth, int hourOfDay,
                           int minute) {
    this(year, month, dayOfMonth, hourOfDay, minute, 0, 0);
  }

  public ZiPersianCalendar(int year, int month, int dayOfMonth, int hourOfDay,
                           int minute, int second) {
    this(year, month, dayOfMonth, hourOfDay, minute, second, 0);
  }

  ZiPersianCalendar(int year, int month, int dayOfMonth,
                    int hourOfDay, int minute, int second, int millis) {
    this(TimeZone.getDefault(), ZiPersianCalendarConstants.PERSIAN_LOCALE_WITH_EN_DIGITS, false);
    int[] ymd = ZiPersianCalendarUtils.jalali_to_gregorian(year, month + 1, dayOfMonth);
    gCal.set(ymd[0], ymd[1] - 1, ymd[2], hourOfDay, minute, second);
    setTimeInMillis(gCal.getTimeInMillis());
  }

  ZiPersianCalendar(TimeZone zone, Locale locale, boolean isSetTime) {
    super(zone, locale);
    this.locale = locale;
    gCal = new GregorianCalendar(zone, locale);
    if (isSetTime) {
      setTimeInMillis(gCal.getTimeInMillis());
    }
  }


  public int getYear() {
    return get(YEAR);
  }

  /** zero-based month, start from 0 to 11 **/
  public int getMonth() {
    return get(MONTH);
  }

  public int getDayOfMonth() {
    return get(DAY_OF_MONTH);
  }

  public int getDaysInMonth() {
    return getDaysInMonth(get(YEAR), get(MONTH));
  }

  protected int getDaysInMonth(int year, int month) {
    if (month < 6) {
      return 31;
    } else if (month < 11) {
      return 30;
    } else {
      if (ZiPersianCalendarUtils.isLeapYear(year)) return 30;
      else return 29;
    }
  }

  /**
   * calendar.getMaximum(Calendar.WEEK_OF_MONTH)
   * is always 6 for Persian Calendar
   **/
  public static int getMaximumMonthWeeks() {
    return 6;
  }


  // date formatter ---------------------------------------------------

  /**
   * @return persian month name
   */
  public String getMonthName() {
    return ZiPersianCalendarUtils.getMonthName(getMonth(), locale);
  }

  /**
   * @return persian weekday name, e.g.: شنبه
   */
  public String getWeekdayName() {
    return ZiPersianCalendarUtils.getWeekdayName(get(DAY_OF_WEEK), locale);
  }

  /**
   * @return A formatted date as long e.g.: چهارشنبه 01 فروردین 1403
   */
  public String getLongDate() {
    return ZiPersianCalendarUtils.getLongDate(
            getYear(), getMonth(), getDayOfMonth(), get(DAY_OF_WEEK), locale);
  }

  /**
   * @return A formatted date with time e.g.: چهارشنبه 01 فروردین 1403, 20:10:06
   */
  public String getLongDateTime() {
    return ZiPersianCalendarUtils.getLongDateTime(
            getYear(), getMonth(), getDayOfMonth(), get(DAY_OF_WEEK),
            get(HOUR_OF_DAY), get(MINUTE), get(SECOND),
            locale
    );
  }

  /**
   * @return date formatted by
   * <p>'yyyy[delimiter]mm[delimiter]dd'</p>
   * <p>default delimiter is '/'</p>
   * <p>e.g.: 1403/01/01</p>
   */
  public String getShortDate() {
    return getShortDate("/");
  }

  /**
   * @return date formatted by
   * <p>'yyyy[delimiter]mm[delimiter]dd'</p>
   * <p>e.g.: 1403/01/01</p>
   */
  public String getShortDate(String delimiter) {
    return ZiPersianCalendarUtils.getShortDate(getYear(), getMonth(), getDayOfMonth(), delimiter, locale);
  }

  /**
   * Parses a date string formatted with a specific delimiter.
   *
   * <p>This method accepts date strings in formats such as "yyy-MM-dd" or "yyyy/MM/dd", where
   * the year, month, and day are separated by a delimiter like a ('-') or ('/').
   * The method processes the string to extract the date components (year, month, day).</p>
   *
   * <p>Example usage:</p>
   * <pre>
   *     parse("1403-01-01");
   *     parse("1403/01/01");
   * </pre>
   *
   * @param dateString the date string to be parsed, in the format "yyyy-MM-dd" or "yyyy/MM/dd"
   */
  public void parse(String dateString) {
    ZiPersianCalendar pCal = ZiPersianCalendarUtils.parseOrNullToCompat(dateString);
    if (pCal != null) {
      setTimeInMillis(pCal.getTimeInMillis());
    }
  }


  /**
   * Determine whether a year is a leap year in the Persian calendar
   */
  public boolean isLeapYear() {
    return ZiPersianCalendarUtils.isLeapYear(get(YEAR));
  }

  /**
   * Calculate persian date from current Date and populates the corresponding
   * fields(persianYear, persianMonth, persianDay)
   */
  protected void calculatePersianDate() {
    int[] ymd = ZiPersianCalendarUtils.gregorian_to_jalali(
            gCal.get(YEAR), gCal.get(MONTH) + 1, gCal.get(DAY_OF_MONTH));
    set(YEAR, ymd[0]);
    set(MONTH, ymd[1] - 1);
    set(DAY_OF_MONTH, ymd[2]);
    set(DAY_OF_WEEK, gCal.get(DAY_OF_WEEK));
    set(HOUR, gCal.get(HOUR_OF_DAY)); // Hour in AM/PM (0-11)
    set(HOUR_OF_DAY, gCal.get(HOUR_OF_DAY)); // Hour of the day (0-23)
    set(MINUTE, gCal.get(MINUTE));
    set(SECOND, gCal.get(SECOND));
    set(MILLISECOND, gCal.get(MILLISECOND));
    set(AM_PM, gCal.get(AM_PM));
    set(ZONE_OFFSET, gCal.get(ZONE_OFFSET));
    set(DST_OFFSET, gCal.get(DST_OFFSET));
    time = gCal.getTimeInMillis();
    isTimeSet = true;
  }

  protected void pinDayOfMonth() {
    int monthLen = getDaysInMonth(internalGet(YEAR), internalGet(MONTH));
    int dom = internalGet(DAY_OF_MONTH);
    if (dom > monthLen) {
      set(DAY_OF_MONTH, monthLen);
    }
  }


  /**
   * Sets the value of the given calendar field. This method does
   * not affect any setting state of the field in this
   * {@code Calendar} instance.
   *
   * @throws IndexOutOfBoundsException if the specified field is out of range
   *             (<code>field &lt; 0 || field &gt;= FIELD_COUNT</code>).
   * @see #areFieldsSet
   * @see #isTimeSet
   * @see #set(int,int)
   */
  protected void internalSet(int field, int value)
  {
    fields[field] = value;
  }


  @Override
  protected void computeTime() {
    int[] ymd = ZiPersianCalendarUtils.jalali_to_gregorian(
            internalGet(YEAR), internalGet(MONTH) + 1, internalGet(DAY_OF_MONTH));
    gCal.set(ymd[0], ymd[1] - 1, ymd[2]);
    time = gCal.getTimeInMillis();
  }

  @Override
  protected void computeFields() {
    gCal.setTimeInMillis(time);
    calculatePersianDate();
  }

  @Override
  public void add(int field, int amount) {
    if (amount == 0) {
      return; // Do nothing!
    }

    if (field < 0 || field >= ZONE_OFFSET) {
      return;
    }

    // Sync the time and calendar fields.
    complete();

    if (field == YEAR) {
      int year = internalGet(YEAR);
      year += amount;
      // if year > 0 => do nothing
      if (year <= 0) {
        year = 1 - year;
      }

      set(YEAR, year);
      pinDayOfMonth();
      return;
    }
    if (field == MONTH) {
      int month = internalGet(MONTH) + amount;
      int year = internalGet(YEAR);
      int yAmount;

      if (month >= 0) {
        yAmount = month/12;
      } else {
        yAmount = (month+1)/12 - 1;
      }

      if (yAmount != 0) {
        year += yAmount;
        // if year > 0 => do nothing
        if (year <= 0) {
          year = 1 - year;
        }
      }

      if (month >= 0) {
        month = month % 12;
      } else {
        // month < 0
        month %= 12;
        if (month < 0) {
          month += 12;
        }
      }

      set(YEAR, year);
      set(MONTH, month);
      pinDayOfMonth();
      return;
    }
    //
    gCal.add(field, amount);
    time = gCal.getTimeInMillis();
    areFieldsSet = false;
  }

  @Override
  public void roll(int field, boolean up) {

  }

  @Override
  public int getMinimum(int field) {
    return 0;
  }

  @Override
  public int getMaximum(int field) {
    return 0;
  }

  @Override
  public int getGreatestMinimum(int field) {
    return 0;
  }

  @Override
  public int getLeastMaximum(int field) {
    return 0;
  }

  @NonNull
  @Override
  public String toString() {
    return getLongDate();
  }
}