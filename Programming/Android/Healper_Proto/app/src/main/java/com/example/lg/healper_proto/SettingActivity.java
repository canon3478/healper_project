package com.example.lg.healper_proto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends Fragment {
    View view = null;
    ArrayAdapter sAdapter;
    public static int time_pos=0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting_sub, container, false);

        Spinner cmb_time = (Spinner) view.findViewById(R.id.combo);
        sAdapter = ArrayAdapter.createFromResource(getContext(), R.array.time, android.R.layout.simple_dropdown_item_1line);
        cmb_time.setAdapter(sAdapter);
        cmb_time.setSelection(time_pos);
        cmb_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String number = (String)sAdapter.getItem(position);
                time_pos = position;
                Toast.makeText(getContext(), number, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        return view;
    }
}
