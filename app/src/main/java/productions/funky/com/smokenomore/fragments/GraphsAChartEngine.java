package productions.funky.com.smokenomore.fragments;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import productions.funky.com.smokenomore.R;
import productions.funky.com.smokenomore.database.SmokeRecord;
import productions.funky.com.smokenomore.util.Constants;
import productions.funky.com.smokenomore.util.ResourceMapper;

/**
 * Created by hborah on 9/11/16.
 */
public class GraphsAChartEngine extends Fragment implements NoSmokeFragmentInterface{
    private View rootView;
    LinearLayout graphPage_layout;

    public static GraphsAChartEngine newInstance() {
        GraphsAChartEngine fragment = new GraphsAChartEngine();
        Bundle args = new Bundle();
        return fragment;
    }

    public GraphsAChartEngine() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graphs__achart_engine, container, false);
        graphPage_layout = (LinearLayout) view.findViewById(R.id.graphs_achart_layout);
        int count = SmokeRecord.getCurrentState();
        graphPage_layout.setBackgroundResource(ResourceMapper.getDrawableForCount(count));
        drawGraphs(view);
        return view;
    }
    private void drawGraph(LinearLayout chartContainer, String name) {
        int[] x = { 0,1,2,3,4,5,6,7, 8, 9, 10, 11 };
        int[] expense = { 1000,1200,1500,1800,2000,2500,3000,3800, 1000, 1300,2300,2400};
        // Creating an XYSeries for Expense
        XYSeries expenseSeries = new XYSeries(name);
        for(int i = 0;i < x.length; i++){
            expenseSeries.add(i,expense[i]);
        }
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(expenseSeries);
        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.CYAN); //color of the graph set to cyan
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(0.5f);
        expenseRenderer.setDisplayChartValues(true);
        expenseRenderer.setDisplayChartValues(true);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);

        multiRenderer.setChartTitle("Expense Chart");


        //setting text size of the title
        multiRenderer.setChartTitleTextSize(40);
        //setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(0);
        //setting text size of the graph lable
        multiRenderer.setLabelsTextSize(36);
        //setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(false);
        //setting pan enablity which uses graph to move on both axis
        multiRenderer.setPanEnabled(false, false);
        //setting click false on graph
        multiRenderer.setClickEnabled(false);
        //setting zoom to false on both axis
        multiRenderer.setZoomEnabled(false, false);
        //setting lines to display on y axis
        multiRenderer.setShowGridY(false);
        //setting lines to display on x axis
        multiRenderer.setShowGridX(false);
        multiRenderer.setShowLegend(false);
        //setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
        //setting displaying line on grid
        multiRenderer.setShowGrid(false);
        //setting zoom to false
        multiRenderer.setZoomEnabled(false);
        //setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(false);
        //setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
        //setting to in scroll to false
        multiRenderer.setInScroll(false);
        //setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
        //setting x axis label align
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
        //setting y axis label to align
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
        //setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        //setting no of values to display in y axis
        multiRenderer.setYLabels(12);
        multiRenderer.setShowLabels(true, false);
        /* setting y axis max value, Since i'm using static values inside the graph
        * so i'm setting y max value to 4000.
        */
        // if you use dynamic values then get the max y value and set here
        multiRenderer.setYAxisMax(4000);
        //setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMin(-1);
        //setting max values to be display in x axis
        multiRenderer.setXAxisMax(12);
        multiRenderer.setBarWidth(3.0f);
        //setting bar size or space between two bars
        //Setting background color of the graph to transparent
        //multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        //Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(DefaultRenderer.NO_COLOR);
        //multiRenderer.setApplyBackgroundColor(true);

        //setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});

        // Adding expenseRenderer to multipleRenderer
        multiRenderer.addSeriesRenderer(expenseRenderer);


        //remove any views before u paint the chart
        chartContainer.removeAllViews();
        //drawing bar chart
        View chart = ChartFactory.getBarChartView(getActivity(), dataset,
                multiRenderer, BarChart.Type.DEFAULT);
        //adding the view to the linearlayout
        chartContainer.addView(chart);
    }

    private void drawGraphs(View rootView) {
        //this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout) rootView.findViewById(R.id.chart_container);
        drawGraph(chartContainer, "Today");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //drawGraphs();
    }

    @Override
    public void setCountValue(final int count) {
        if (graphPage_layout != null)
        {
            Log.i("GraphAChartEngine", "Setting count to " + count);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    graphPage_layout.setBackgroundResource(ResourceMapper.getDrawableForCount(count));
                    graphPage_layout.invalidate();
                }
            });
        }
    }
}