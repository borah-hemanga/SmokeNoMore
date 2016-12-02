package productions.funky.com.smokenomore.database;

import android.util.Log;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.List;

/**
 * Created by RezaulAkram on 10/10/2016.
 */
public class SmokeRecord extends SugarRecord {
    int year;

    public SmokeRecord() {
    }

    int month;
    int date;
    int hour;
    int minute;
    int second;
    int dayOfTheMonth;
    int dayOfTheWeek;
    int region;
    int daylightSavings;
    String timezoneId;

    public SmokeRecord(int year, int month, int date, int hour, int minute, int second, int dayOfTheMonth, int dayOfTheWeek, int region, int isDayLightSavings, String timezoneId) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.dayOfTheMonth = dayOfTheMonth;
        this.dayOfTheWeek = dayOfTheWeek;
        this.region = region;
        this.daylightSavings = isDayLightSavings;
        this.timezoneId = timezoneId;
    }



    static public int getCurrentState() {
        Calendar c = Calendar.getInstance();
        List<SmokeRecord> result = SmokeRecord.find(SmokeRecord.class, "year = ? and month = ? and date = ?",
                 Integer.toString(c.get(Calendar.YEAR))
                , Integer.toString(c.get(Calendar.MONTH))
                , Integer.toString(c.get(Calendar.DATE)));
        return result.size();
    }

    static public void storeRecord()
    {
        Calendar c = Calendar.getInstance();
        SmokeRecord s = new SmokeRecord(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DATE),
                c.get(Calendar.HOUR),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND),
                c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.DAY_OF_WEEK),
                c.getTimeZone().getRawOffset(),
                c.getTimeZone().getDSTSavings(),
                c.getTimeZone().getID()
        );

        long result = s.save();
        Log.i("SmokeRecord","Result " + result + " for " +
                c.get(Calendar.YEAR) + ":" +
                c.get(Calendar.MONTH) + ":" +
                c.get(Calendar.DATE) + " " +
                c.get(Calendar.HOUR) + ":" +
                c.get(Calendar.MINUTE)+ ":" +
                c.get(Calendar.SECOND)+ " " +
                c.get(Calendar.DAY_OF_MONTH)+ ":" +
                c.get(Calendar.DAY_OF_WEEK)+ " " +
                c.getTimeZone().getRawOffset() + " " +
                c.getTimeZone().getDSTSavings() + " " +
                c.getTimeZone().getID());
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getDayOfTheMonth() {
        return dayOfTheMonth;
    }

    public void setDayOfTheMonth(int dayOfTheMonth) {
        this.dayOfTheMonth = dayOfTheMonth;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getDaylightSavings() {
        return daylightSavings;
    }

    public void setDaylightSavings(int daylightSavings) {
        this.daylightSavings = daylightSavings;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }
}
