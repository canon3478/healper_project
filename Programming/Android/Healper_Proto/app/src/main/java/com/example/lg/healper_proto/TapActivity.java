package com.example.lg.healper_proto;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class TapActivity extends FragmentActivity {
    private static final int REQUEST_ENABLE_BT = 1;

    BluetoothAdapter bluetoothAdapter;

    ArrayList<BluetoothDevice> pairedDeviceArrayList;
    ListView listViewPairedDevice;
    ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    private UUID myUUID;
    private final String UUID_STRING_WELL_KNOWN_SPP = "00001101-0000-1000-8000-00805F9B34FB";

    ThreadConnectBTdevice myThreadConnectBTdevice;
    ThreadConnected myThreadConnected;

    private FragmentTabHost mTabHost;
    public static float[] data = new float[360];
    public static float[] data_years = new float[12];
    public static float[] data_months = new float[30];
    public static float[] data_weeks = new float[7];
    NowActivity nowActivity = new NowActivity();
    SettingActivity settingActivity = new SettingActivity();
    static int time_pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_sub);
        listViewPairedDevice = (ListView)findViewById(R.id.pairedlist);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
            Toast.makeText(this, "FEATURE_BLUETOOTH NOT support", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        //using the well-known SPP UUID
        myUUID = UUID.fromString(UUID_STRING_WELL_KNOWN_SPP);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this hardware platform", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        //Turn ON BlueTooth if it is OFF
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

        setup();

        init();
    }
    private void setup() {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            pairedDeviceArrayList = new ArrayList<BluetoothDevice>();

            for (BluetoothDevice device : pairedDevices) {
                pairedDeviceArrayList.add(device);
            }

            pairedDeviceAdapter = new ArrayAdapter<BluetoothDevice>(this, android.R.layout.simple_list_item_1, pairedDeviceArrayList);
            listViewPairedDevice.setAdapter(pairedDeviceAdapter);
            listViewPairedDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    BluetoothDevice device = (BluetoothDevice) parent.getItemAtPosition(position);
                    myThreadConnectBTdevice = new ThreadConnectBTdevice(device);
                    myThreadConnectBTdevice.start();
                }
            });

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(myThreadConnectBTdevice!=null){
            myThreadConnectBTdevice.cancel();
        }
    }
    private void startThreadConnected(BluetoothSocket socket){

        myThreadConnected = new ThreadConnected(socket);
        myThreadConnected.start();
    }

    private class ThreadConnectBTdevice extends Thread {

        private BluetoothSocket bluetoothSocket = null;
        private final BluetoothDevice bluetoothDevice;


        private ThreadConnectBTdevice(BluetoothDevice device) {
            bluetoothDevice = device;

            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(myUUID);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            boolean success = false;
            try {
                bluetoothSocket.connect();
                success = true;
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    bluetoothSocket.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if(success){
                //connect successful
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        listViewPairedDevice.setVisibility(View.GONE);
                        mTabHost.setVisibility(View.VISIBLE);
                    }});

                startThreadConnected(bluetoothSocket);
            }else{
                //fail
            }
        }

        public void cancel() {

            Toast.makeText(getApplicationContext(),
                    "close bluetoothSocket",
                    Toast.LENGTH_LONG).show();

            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    private class ThreadConnected extends Thread {
        private final BluetoothSocket connectedBluetoothSocket;
        private final InputStream connectedInputStream;
        private final OutputStream connectedOutputStream;

        public ThreadConnected(BluetoothSocket socket) {
            connectedBluetoothSocket = socket;
            InputStream in = null;
            OutputStream out = null;

            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            connectedInputStream = in;
            connectedOutputStream = out;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = connectedInputStream.read(buffer);
                    final String strReceived = new String(buffer, 0, bytes);

                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            switch(strReceived) {
                                case "1":
                                    Left_Leg(nowActivity);
                                    break;
                                case "2":
                                    Right_Leg(nowActivity);
                                    break;
                                default:
                                    Default_Leg(nowActivity);
                                    break;
                            }


                        }});

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void write(byte[] buffer) {
            try {
                connectedOutputStream.write(buffer);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                connectedBluetoothSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void Left_Leg(NowActivity nowActivity) {
        nowActivity.cnt_bad ++;
        nowActivity.cnt_correct = 0;
        nowActivity.imageview.setImageResource(R.drawable.left_leg);
        nowActivity.Chr.stop();
        nowActivity.Chr2.start();
        //if(nowActivity.cnt_bad > TapActivity.time_pos)
        //    popup();
    }
    public static void Right_Leg(NowActivity nowActivity) {
        nowActivity.cnt_bad ++;
        nowActivity.cnt_correct = 0;
        nowActivity.imageview.setImageResource(R.drawable.right_leg);
        nowActivity.Chr.stop();
        nowActivity.Chr2.start();
        //if(nowActivity.cnt_bad > TapActivity.time_pos)
        //    popup();
    }
    public static void Default_Leg(NowActivity nowActivity) {
        nowActivity.cnt_bad = 0;
        nowActivity.cnt_correct ++;
        nowActivity.imageview.setImageResource(R.drawable.default_leg);
        nowActivity.Chr2.stop();
        nowActivity.Chr.start();
        //if(nowActivity.cnt_bad > TapActivity.time_pos)
        //    popup();

    }

    public static void stat_init() {
        int i, j;
        float sum=0;
        for(i=0;i<12;i++) {
            for (j = 0; j < 30; j++) {
                sum += data[i * 30 + j];
            }
            data_years[i] = sum/30;
            sum=0;
        }

        for (i = 1; i <= 30; i++) {
            data_months[30-i] = data[360-i];
        }

        for(i=1;i<=7;i++) {
            data_weeks[7-i] = data[360-i];
        }

    }
    public void init() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);

        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("tab1");
        Button btn = new Button(this);
        btn.setText("now");
        tabSpec.setIndicator(btn);
        Bundle b = new Bundle();
        mTabHost.addTab(tabSpec, nowActivity.getClass(), b);

        tabSpec = mTabHost.newTabSpec("tab2");
        btn = new Button(this);
        btn.setText("Stat");
        tabSpec.setIndicator(btn);
        b = new Bundle();
        mTabHost.addTab(tabSpec, StatActivity.class, b);

        tabSpec = mTabHost.newTabSpec("tab3");
        btn = new Button(this);
        btn.setText("Tip");
        tabSpec.setIndicator(btn);
        b = new Bundle();
        mTabHost.addTab(tabSpec, TipActivity.class, b);

        tabSpec = mTabHost.newTabSpec("tab4");
        btn = new Button(this);
        btn.setText("Setting");
        tabSpec.setIndicator(btn);
        b = new Bundle();
        mTabHost.addTab(tabSpec, SettingActivity.class, b);


    }
}
