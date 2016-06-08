package com.example.lg.healper_proto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    //static final int REQUEST_ENABLE_BT = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //ArrayAdapter mAdapter;
    private static final String TAG = "MAIN";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    public static EditText ID_edit;
    public static EditText PW_edit;
    public static Boolean loginresult = false;

    private BluetoothService bluetoothService_obj = null;

    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sub);

        ID_edit = (EditText) findViewById(R.id.ID);
        PW_edit = (EditText) findViewById(R.id.PW);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        //BLTConnection();

        Button Log_In = (Button) findViewById(R.id.Login);
        Log_In.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,toServer.LogIn(),Toast.LENGTH_LONG).show();
                loginresult = toJSON.execute(toServer.LogIn());
                //Toast.makeText(MainActivity.this, toJSON.execute(toServer.LogIn()), Toast.LENGTH_LONG).show();
                if(loginresult) {
                    Intent intent = new Intent(getApplicationContext(), TapActivity.class);
                    startActivity(intent);
                    TapActivity.stat_init();
                }
                else
                    Toast.makeText(MainActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_LONG).show();
            }
        });
        Button Sign_Up = (Button) findViewById(R.id.signup);
        Sign_Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(mReceiver);
    }
    /*
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Device deviceClass = new Device();
                deviceClass.Name = device.getName();
                deviceClass.Address = device.getAddress();
                mAdapter.add(deviceClass);
            }
        }
    };
    public void BLTConnection() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
        }
        else {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBIntent, REQUEST_ENABLE_BT);
            }
        }

        mBluetoothAdapter.startDiscovery();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0){ // 하나 이상 검색될 경우

            for(BluetoothDevice device : pairedDevices){
                mAdapter.clear();
                Device deviseClass = new Device();
                deviseClass.Name = device.getName();
                deviseClass.Address = device.getAddress();
                mAdapter.add(deviseClass);
            }
    }

    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
    if (pairedDevices.size() > 0) {
        for (BluetoothDevice device : pairedDevices) {
            mAdapter.clear();
            BluetoothClass.Device deviceClass = new BluetoothClass.Device();

        }
    }
        */
}
