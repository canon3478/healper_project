package com.example.lg.healper_proto;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TabHost;

public class TapActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    public static float[] data = new float[360];
    public static float[] data_years = new float[12];
    public static float[] data_months = new float[30];
    public static float[] data_weeks = new float[7];
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_sub);

        init();
    }
    public static void stat_init() {
        int i, j;
        float sum=0;
        for(i=0;i<12;i++) {
            for (j = 0; j < 30; j++) {
                sum += data[i * 30 + j];
            }
            data_years[i] = sum/30;
            sum=0;
        }

        for (i = 1; i <= 30; i++) {
            data_months[30-i] = data[360-i];
        }

        for(i=1;i<=7;i++) {
            data_weeks[7-i] = data[360-i];
        }

    }
    public void init() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);

        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("tab1");
        Button btn = new Button(this);
        btn.setText("now");
        tabSpec.setIndicator(btn);
        Bundle b = new Bundle();
        mTabHost.addTab(tabSpec, NowActivity.class, b);

        tabSpec = mTabHost.newTabSpec("tab2");
        btn = new Button(this);
        btn.setText("Stat");
        tabSpec.setIndicator(btn);
        b = new Bundle();
        mTabHost.addTab(tabSpec, StatActivity.class, b);

        tabSpec = mTabHost.newTabSpec("tab3");
        btn = new Button(this);
        btn.setText("Tip");
        tabSpec.setIndicator(btn);
        b = new Bundle();
        mTabHost.addTab(tabSpec, TipActivity.class, b);

        tabSpec = mTabHost.newTabSpec("tab4");
        btn = new Button(this);
        btn.setText("Setting");
        tabSpec.setIndicator(btn);
        b = new Bundle();
        mTabHost.addTab(tabSpec, SettingActivity.class, b);

    }

}
