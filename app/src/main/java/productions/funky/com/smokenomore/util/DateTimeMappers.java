package productions.funky.com.smokenomore.util;

/**
 * Created by hborah on 9/11/16.
 */
public class DateTimeMappers {
    String Week[] = {"Su", "M", "T", "W", "Th", "F", "S"};
    String Month[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    int numDays = 8;
    int numMonths = 6;

    public String[] getMonths(int currMonth) {
        String lastMonths[] = new String[numMonths];
        for (int i = 0; i < numMonths; i++) {
            int monthIndex = (12 + currMonth - numMonths + i + 1) % 12;
            lastMonths[i] = Month[monthIndex];
        }
        return lastMonths;
    }

    public String[] getDates(int currDay) {
        String []lastDays = new String[numDays];
        for (int i = 0; i < numDays; i++) {
            lastDays[i] = Week[(currDay + i) % 7];
        }
        return lastDays;
    }

    public String[] getVerticalMappers(int maxItem) {
        String[] verticalMappers = new String[maxItem+3];
        for (int i = 0; i < maxItem + 3; i++) {
            verticalMappers[i] = i+"";
        }
        return verticalMappers;
    }

}
