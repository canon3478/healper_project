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
        Spinner spinner = (Spinner) view.findViewById(R.id.combo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_array1, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        return view;
    }
}
