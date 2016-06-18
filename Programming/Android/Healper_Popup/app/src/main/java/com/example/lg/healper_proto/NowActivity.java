package com.example.lg.healper_proto;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

public class NowActivity extends Fragment{
    View view = null;
    public static TextView textview;
    public static StringBuffer sitting_statue;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = print_time(inflater, container, savedInstanceState);
        return view;
    }

    public String callValues() {
        return "a";
    }
    public String algorithm_Values() {
        return "leg";
    }
    public int call_3Dposition() {
        return 1;
    }
    public View print_time(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.now_sub, container, false);
        Chronometer Chr = (Chronometer) view.findViewById(R.id.chr);
        Chronometer Chr2 = (Chronometer) view.findViewById(R.id.chr2);
        textview = (TextView)this.view.findViewById(R.id.sitting_statue);
        Chr.setBase(SystemClock.elapsedRealtime());
        Chr2.setBase(SystemClock.elapsedRealtime());
        Chr.start();
        Chr2.start();
        return view;
    }
}
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
