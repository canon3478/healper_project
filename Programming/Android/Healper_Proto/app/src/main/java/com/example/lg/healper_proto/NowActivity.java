package com.example.lg.healper_proto;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

public class NowActivity extends Fragment{
    View view = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // callValues();
        // algorithm_Values();
        // call_3Dposition();
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

        Chr.setBase(SystemClock.elapsedRealtime());
        Chr2.setBase(SystemClock.elapsedRealtime());
        Chr.start();
        Chr2.start();
        return view;
    }
}
