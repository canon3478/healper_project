package com.example.lg.healper_proto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingActivity extends Fragment {
    View view = null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting_sub, container, false);

        Spinner cmb_time = (Spinner) view.findViewById(R.id.combo);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.time, R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cmb_time.setAdapter(adapter1);

        return view;
    }
}
