package com.example.lg.healper_proto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;

public class NowActivity extends Fragment {
    View view = null;
    public static ImageView imageview;
    public static Chronometer Chr;
    public static Chronometer Chr2;
    public long timer, timer2 = 0;
    public static int cnt_bad=0, cnt_correct=0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.now_sub, container, false);

        view = print_time(inflater, container, savedInstanceState);
        imageview = (ImageView) view.findViewById(R.id.IV);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public View print_time(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.now_sub, container, false);
        Chr = (Chronometer) view.findViewById(R.id.chr);
        Chr2 = (Chronometer) view.findViewById(R.id.chr2);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
