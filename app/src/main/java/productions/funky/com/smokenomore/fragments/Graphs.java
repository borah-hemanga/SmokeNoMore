package productions.funky.com.smokenomore.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import productions.funky.com.smokenomore.R;
import productions.funky.com.smokenomore.database.Counter;
import productions.funky.com.smokenomore.util.Constants;
import productions.funky.com.smokenomore.util.DateTimeMappers;

/**
 * Created by hborah on 9/11/16.
 */
public class Graphs extends Fragment{
    private View rootView;
    DateTimeMappers dTMappers;

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

    private void drawGraph(int viewId, Constants.GRAPH_TYPES type) {
        GraphView graph = (GraphView) rootView.findViewById(viewId);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        BarGraphSeries<DataPoint> series = null;
        int maxVal = 20;

        if (type == Constants.GRAPH_TYPES.GRAPH_TYPE_DAYS) {
            staticLabelsFormatter.setHorizontalLabels(dTMappers.getDates(2));
            series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(0, 1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3),
                    new DataPoint(3, 2),
                    new DataPoint(4, 8),
                    new DataPoint(5, 2),
                    new DataPoint(6, 2),
                    new DataPoint(7, 3),
            });
            maxVal = 8;
        } else if (type == Constants.GRAPH_TYPES.GRAPH_TYPE_MONTH) {
            staticLabelsFormatter.setHorizontalLabels(dTMappers.getMonths(2));
            series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(0, 1),
                    new DataPoint(1, 25),
                    new DataPoint(2, 17),
                    new DataPoint(3, 18),
                    new DataPoint(4, 19),
                    new DataPoint(5, 22),
                    new DataPoint(6, 15),
                    new DataPoint(7, 12),
            });
            maxVal = 25;
        }

        graph.addSeries(series);
        graph.getGridLabelRenderer().setGridColor(0xFFFFFF);
        graph.getGridLabelRenderer().setVerticalLabelsColor(0xFFFFFF);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(7);

        staticLabelsFormatter.setVerticalLabels(dTMappers.getVerticalMappers(maxVal));
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                System.out.println(data.getX() + " " + data.getY());

                return Color.rgb((int) data.getX() * 255 / 4, 0, 0);
            }
        });

        series.setSpacing(30);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
    }

    private void drawGraphs() {
        drawGraph(R.id.graph_days, Constants.GRAPH_TYPES.GRAPH_TYPE_DAYS);
        drawGraph(R.id.graph_months, Constants.GRAPH_TYPES.GRAPH_TYPE_MONTH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.graphs, container, false);
        dTMappers = new DateTimeMappers();
        testDB();
        drawGraphs();
        return rootView;
    }
}