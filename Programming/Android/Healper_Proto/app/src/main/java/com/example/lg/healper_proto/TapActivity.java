package com.example.lg.healper_proto;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.Button;
import android.widget.TabHost;

public class TapActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_sub);

        init();
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
