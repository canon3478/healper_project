package com.example.lg.healper_proto;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends Fragment {
    View view = null;
    ArrayAdapter sAdapter;
    PopupWindow popupWindow;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting_sub, container, false);

        Spinner cmb_time = (Spinner) view.findViewById(R.id.combo);
        sAdapter = ArrayAdapter.createFromResource(getContext(), R.array.time, android.R.layout.simple_dropdown_item_1line);
        cmb_time.setAdapter(sAdapter);
        cmb_time.setSelection(TapActivity.time_pos);
        cmb_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String number = (String)sAdapter.getItem(position);
                TapActivity.time_pos = position;
                Toast.makeText(getContext(), number, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        Button logout = (Button) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    // 위치 설정시 팝업 창
    public void callPopup() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View popupView = layoutInflater.inflate(R.layout.popup, null);

        popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,
                true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        //bg.getBackground().setAlpha(100);

        popupWindow .showAtLocation(popupView, Gravity.CENTER, 0, 0);
        //Name = (EditText) popupView.findViewById(R.id.edtimageName);
        // role= (Spinner) popupView.findViewById(R.id.spinner);
        // si = (Spinner) popupView.findViewById(R.id.spinner2);

        ((ImageView) popupView.findViewById(R.id.check))
                .setOnClickListener(new View.OnClickListener() {

                    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
                    public void onClick(View arg0) {
                        popupWindow.dismiss();
                    }
                });

    }

}
