package productions.funky.com.smokenomore.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import productions.funky.com.smokenomore.R;
import productions.funky.com.smokenomore.database.Counter;

/**
 * Created by hborah on 9/11/16.
 */
public class Graphs extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Graphs newInstance(int sectionNumber) {
        Graphs fragment = new Graphs();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Graphs() {
    }

    private void testDB() {
        Counter c = new Counter(new Date().toString(), 5);
        c.save();

        Date date = new Date();
        List<Counter> dbAboutMe = Counter.find(Counter.class, "date = ?", date.toString());
        for (Counter counter : dbAboutMe) {
            System.out.println(counter.getCounts() + " " + counter.getDate());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_counter, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        testDB();
        return rootView;
    }
}