package productions.funky.com.smokenomore.util;

/**
 * Created by hborah on 9/11/16.
 */
public class DateTimeMappers {
    String Week[] = {"Su", "M", "T", "W", "Th", "F", "S"};
    String Month[] = {"Ja", "Fe", "Mr", "Ap", "My", "Jn", "Jy", "Ag", "Sp", "Oc", "No", "De"};
    int numDays = 8;

    public String[] getDates(int currDay) {
        String []last8Days = new String[numDays];
        for (int i = 0; i < numDays; i++) {
            last8Days[i] = Week[(currDay + i) % 7];
        }
        return last8Days;
    }

    public String[] getVerticalMappers(int maxItem) {
        String[] verticalMappers = new String[maxItem+3];
        for (int i = 0; i < maxItem + 3; i++) {
            verticalMappers[i] = i+"";
        }
        return verticalMappers;
    }

}
