package com.example.lg.healper_proto;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    LinearLayout layout1;
    View view = null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.stat_sub, container, false);

        Button year_stat = (Button) view.findViewById(R.id.year);
        Button month_stat = (Button) view.findViewById(R.id.month);
        Button week_stat = (Button) view.findViewById(R.id.week);

        layout1 = (LinearLayout) view.findViewById(R.id.Chart1);
        //LinearLayout layout2 = (LinearLayout) view.findViewById(R.id.Chart2);
        //LinearLayout layout3 = (LinearLayout) view.findViewById(R.id.Chart3);
        mChartView = ChartFactory.getBarChartView(getContext(), getBarChartDataset_year(), getRenderer_year(), BarChart.Type.DEFAULT);
        mChartView2 = ChartFactory.getBarChartView(getContext(), getBarChartDataset_month(), getRenderer_month(), BarChart.Type.DEFAULT);
        mChartView3 = ChartFactory.getBarChartView(getContext(), getBarChartDataset_week(), getRenderer_week(), BarChart.Type.DEFAULT);

        layout1.addView(mChartView);

        year_stat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                layout1.removeAllViews();
                layout1.addView(mChartView);
            }
        });
        month_stat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                layout1.removeAllViews();
                layout1.addView(mChartView2);
            }
        });
        week_stat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                layout1.removeAllViews();
                layout1.addView(mChartView3);
            }
        });
        return view;
    }

    public XYMultipleSeriesDataset getBarChartDataset_year() {
        XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();
        XYSeries dataSeries = new XYSeries("data");
        int i;
        for(i=1;i<=12;i++) {
            dataSeries.add(i,TapActivity.data_years[i-1]);
        }
        myData.addSeries(dataSeries);
        return myData;
    }
    public XYMultipleSeriesDataset getBarChartDataset_month() {
        XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();
        XYSeries dataSeries = new XYSeries("data");
        int i;
        for(i=1;i<=30;i++) {
            dataSeries.add(i,TapActivity.data_months[i-1]);
        }
        myData.addSeries(dataSeries);
        return myData;
    }
    public XYMultipleSeriesDataset getBarChartDataset_week() {
        XYMultipleSeriesDataset myData = new XYMultipleSeriesDataset();
        XYSeries dataSeries = new XYSeries("data");
        int i;
        for(i=1;i<=7;i++) {
            dataSeries.add(i,TapActivity.data_weeks[i-1]);
        }
        myData.addSeries(dataSeries);
        return myData;
    }

    public XYMultipleSeriesRenderer getRenderer_year() {
        XYSeriesRenderer renderer = new XYSeriesRenderer();

        renderer.setColor(Color.parseColor("#158aea"));

        XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
        myRenderer.addSeriesRenderer(renderer);

        myRenderer.setXAxisMin(0);
        myRenderer.setXAxisMax(12);
        myRenderer.setYAxisMin(0);
        myRenderer.setYAxisMax(24);

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
    public XYMultipleSeriesRenderer getRenderer_month() {
        XYSeriesRenderer renderer = new XYSeriesRenderer();

        renderer.setColor(Color.parseColor("#158aea"));

        XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
        myRenderer.addSeriesRenderer(renderer);

        myRenderer.setXAxisMin(0);
        myRenderer.setXAxisMax(30);
        myRenderer.setYAxisMin(0);
        myRenderer.setYAxisMax(24);

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
    public XYMultipleSeriesRenderer getRenderer_week() {
        XYSeriesRenderer renderer = new XYSeriesRenderer();

        renderer.setColor(Color.parseColor("#158aea"));

        XYMultipleSeriesRenderer myRenderer = new XYMultipleSeriesRenderer();
        myRenderer.addSeriesRenderer(renderer);

        myRenderer.setXAxisMin(0);
        myRenderer.setXAxisMax(7);
        myRenderer.setYAxisMin(0);
        myRenderer.setYAxisMax(24);

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

