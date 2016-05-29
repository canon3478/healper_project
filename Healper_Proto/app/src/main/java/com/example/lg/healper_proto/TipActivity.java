package com.example.lg.healper_proto;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

public class TipActivity extends Fragment {
    View view = null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tip_sub, container, false);
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.yoga);
        VideoView video1 = (VideoView) view.findViewById(R.id.video);
        video1.setVideoURI(uri);
        video1.start();
        return view;
    }
}
