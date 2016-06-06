package com.example.lg.healper_proto;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Handler;

import java.util.UUID;

/**
 * Created by LG on 2016-05-29.
 */
public class BluetoothService {

    private static final int REQUEST_ENABLE_BT = 2;
    private static final String TAG = "BluetoothService";

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter btAdapter;
    private Activity mActivity;
    private Handler mHandler;

    public BluetoothService(Activity activity, Handler handler) {
        mActivity = activity;
        mHandler = handler;

        //bluetoothAdapter 얻기
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }
}
