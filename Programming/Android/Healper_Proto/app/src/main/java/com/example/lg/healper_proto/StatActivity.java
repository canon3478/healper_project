package com.example.lg.healper_proto;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class StatActivity extends Fragment {

    protected GraphicalView mChartView;
    protected GraphicalView mChartView2;
    protected GraphicalView mChartView3;

    View view = null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //call_datas();
        //

        view = inflater.inflate(R.layout.stat_sub, container, false);
        LinearLayout layout1 = (LinearLayout) view.findViewById(R.id.Chart1);
        LinearLayout layout2 = (LinearLayout) view.findViewById(R.id.Chart2);
        LinearLayout layout3 = (LinearLayout) view.findViewById(R.id.Chart3);
        mChartView = ChartFactory.getBarChartView(getContext(), getBarChartDataset(), getRenderer(), BarChart.Type.DEFAULT);
        mChartView2 = ChartFactory.getBarChartView(getContext(), getBarChartDataset(), getRenderer(), BarChart.Type.DEFAULT);
        mChartView3 = ChartFactory.getBarChartView(getContext(), getBarChartDataset(), getRenderer(), BarChart.Type.DEFAULT);
        layout1.addView(mChartView);
        layout2.addView(mChartView2);
        layout3.addView(mChartView3);
        return view;
    }

    public XYMultipleSeriesDataset getBarChartDataset() {
        XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();
        XYSeries dataSeries = new XYSeries("data");

        dataSeries.add(1, 7);
        dataSeries.add(2, 8);
        dataSeries.add(3, 9);
        dataSeries.add(4, 8);
        dataSeries.add(5, 7);
        dataSeries.add(6, 6);
        dataSeries.add(7, 7.1);
        dataSeries.add(8, 8);
        dataSeries.add(9, 5);
        myData.addSeries(dataSeries);
        return myData;
    }

    public XYMultipleSeriesRenderer getRenderer() {
        XYSeriesRenderer renderer = new XYSeriesRenderer();

        renderer.setColor(Color.parseColor("#158aea"));

        XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
        myRenderer.addSeriesRenderer(renderer);

        myRenderer.setXAxisMin(0);
        myRenderer.setXAxisMax(10);
        myRenderer.setYAxisMin(0);
        myRenderer.setYAxisMax(20);

        myRenderer.setShowGrid(true);
        myRenderer.setGridColor(Color.parseColor("#c9c9c9"));

        myRenderer.setPanEnabled(true, false);
        myRenderer.setPanLimits(new double[]{0, 31.5, 0, 0});

        myRenderer.setShowLegend(false);

        myRenderer.setXLabels(10);
        myRenderer.setYLabels(20);
        myRenderer.setLabelsTextSize(20);
        myRenderer.setYLabelsAlign(Paint.Align.RIGHT);

        myRenderer.setShowAxes(false);
        myRenderer.setBarSpacing(0.5);
        myRenderer.setZoomEnabled(false, false);
        int margin[] = {20, 50, 50, 30};
        myRenderer.setMargins(margin);
        myRenderer.setMarginsColor(Color.parseColor("#FFFFFF"));

        return myRenderer;
    }
}

