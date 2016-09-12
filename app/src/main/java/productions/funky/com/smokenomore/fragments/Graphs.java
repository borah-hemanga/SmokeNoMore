package productions.funky.com.smokenomore.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import productions.funky.com.smokenomore.R;
import productions.funky.com.smokenomore.database.Counter;

/**
 * Created by hborah on 9/11/16.
 */
public class Graphs extends Fragment {
    private View rootView;

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
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date todayWithZeroTime = new Date();
        try {
            todayWithZeroTime = formatter.parse(formatter.format(todayWithZeroTime));
        } catch (Exception e) {
        }

        Counter c = new Counter(todayWithZeroTime.toString(), 5);
        c.save();

        List<Counter> dbAboutMe = Counter.listAll(Counter.class);
        for (Counter counter : dbAboutMe) {
            System.out.println(counter.getCounts() + " " + counter.getDate());
        }
    }

    private void drawLast7Months() {
        //GraphView graph = (GraphView) rootView.findViewById(R.id.graph_5_months);
        GraphView line_graph = (GraphView) rootView.findViewById(R.id.graph_5_months);
        BarGraphSeries <DataPoint> line_series = new BarGraphSeries <DataPoint >
                        (new DataPoint[] {
                new DataPoint(0, 17),
                new DataPoint(1, 5),
                new DataPoint(2, 45),
                new DataPoint(3, 5),
                new DataPoint(4, 3)
        });

        line_graph.addSeries(line_series);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(line_graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Jan", "Feb", "March"});
        staticLabelsFormatter.setVerticalLabels(new String[]{"Sun", "Mon", "Tue"});
        line_graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
    }

    private void drawLastSevenDays() {
        GraphView graph = (GraphView) rootView.findViewById(R.id.graph_7_days);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(-6, 1),
                new DataPoint(-5, 5),
                new DataPoint(-4, 3),
                new DataPoint(-3, 2),
                new DataPoint(-2, 6),
                new DataPoint(-1, 2),
                new DataPoint(0, 1),
                //new DataPoint(5, 5),
                //new DataPoint(6, 6),
        });

        graph.addSeries(series);
        graph.getViewport().setMaxX(1);
        graph.getGridLabelRenderer().setGridColor(0xFFFFFF);
        graph.getGridLabelRenderer().setVerticalLabelsColor(0xFFFFFF);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(0xFFFFFF);

        graph.getViewport().setXAxisBoundsManual(true);

        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });

        series.setSpacing(20);

        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
    }

    private void drawGraphs() {
        drawLastSevenDays();
        drawLast7Months();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.graphs, container, false);
        testDB();
        drawGraphs();
        return rootView;
    }
}