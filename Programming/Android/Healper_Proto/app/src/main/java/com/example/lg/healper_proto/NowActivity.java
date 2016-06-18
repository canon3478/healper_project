package com.example.lg.healper_proto;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class NowActivity extends Fragment {
    View view = null;
    PopupWindow popupWindow;
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