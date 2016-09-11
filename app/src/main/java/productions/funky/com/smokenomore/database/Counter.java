package productions.funky.com.smokenomore.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

/**
 * Created by hborah on 9/11/16.
 */
public class Counter extends SugarRecord {
    @Unique
    String date;
    int counts;

    public Counter() {
    }

    public Counter (String date, int counts) {
        this.date = date;
        this.counts = counts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
