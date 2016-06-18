package com.example.lg.healper_proto;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class TapActivity extends FragmentActivity {

    static PopupWindow popupWindow;
    private static final int REQUEST_ENABLE_BT = 1;

    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice device;

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
    static int time_pos = 0;
    boolean standup = false;

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
                    device = (BluetoothDevice) parent.getItemAtPosition(position);
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
                                case "3":
                                    Front_Hunched(nowActivity);
                                    break;
                                case "4":
                                    Right_Shoulder(nowActivity);
                                    break;
                                case "5":
                                    Left_Shoulder(nowActivity);
                                    break;
                                case "0":
                                    Default_Leg(nowActivity);
                                    break;
                                default:
                                    Stand_Up(nowActivity);
                                    break;
                            }
                            //if(nowActivity.cnt_bad > time_pos * 60 + 10)
                            //    nowActivity.callPopup();
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
    public void Right_Shoulder(NowActivity nowActivity) {
        if(standup || nowActivity.cnt_bad == 0) {
            standup = false;
            nowActivity.Chr2.setBase(SystemClock.elapsedRealtime() - nowActivity.timer2);
            nowActivity.Chr2.start();
            nowActivity.Chr.stop();
            nowActivity.timer = SystemClock.elapsedRealtime() - nowActivity.Chr.getBase();
        }
        nowActivity.cnt_bad ++;
        nowActivity.cnt_correct = 0;
        nowActivity.imageview.setImageResource(R.drawable.right_shoulder);
        if(nowActivity.cnt_bad > time_pos * 60 + 10)
            callPopup("Don't lean right!");
    }
    public void Left_Shoulder(NowActivity nowActivity) {
        if(standup || nowActivity.cnt_bad == 0) {
            standup = false;
            nowActivity.Chr2.setBase(SystemClock.elapsedRealtime() - nowActivity.timer2);
            nowActivity.Chr2.start();
            nowActivity.Chr.stop();
            nowActivity.timer = SystemClock.elapsedRealtime() - nowActivity.Chr.getBase();
        }
        nowActivity.cnt_bad ++;
        nowActivity.cnt_correct = 0;
        nowActivity.imageview.setImageResource(R.drawable.left_shoulder);
        if(nowActivity.cnt_bad > time_pos * 60 + 10)
            callPopup("Don't lean left!");
    }
    public void Front_Hunched(NowActivity nowActivity) {
        if(standup || nowActivity.cnt_bad == 0) {
            standup = false;
            nowActivity.Chr2.setBase(SystemClock.elapsedRealtime() - nowActivity.timer2);
            nowActivity.Chr2.start();
            nowActivity.Chr.stop();
            nowActivity.timer = SystemClock.elapsedRealtime() - nowActivity.Chr.getBase();
        }
        nowActivity.cnt_bad ++;
        nowActivity.cnt_correct = 0;
        nowActivity.imageview.setImageResource(R.drawable.front);
        if(nowActivity.cnt_bad > time_pos * 60 + 10)
            callPopup("Don't lean forward!");
    }
    public void Left_Leg(NowActivity nowActivity) {
        if(standup || nowActivity.cnt_bad == 0) {
            standup = false;
            nowActivity.Chr2.setBase(SystemClock.elapsedRealtime() - nowActivity.timer2);
            nowActivity.Chr2.start();
            nowActivity.Chr.stop();
            nowActivity.timer = SystemClock.elapsedRealtime() - nowActivity.Chr.getBase();
        }
        nowActivity.cnt_bad ++;
        nowActivity.cnt_correct = 0;
        nowActivity.imageview.setImageResource(R.drawable.left_leg);
        if(nowActivity.cnt_bad > time_pos * 60 + 10)
            callPopup("Don't cross your left leg!");
    }
    public void Right_Leg(NowActivity nowActivity) {
        if(standup || nowActivity.cnt_bad == 0) {
            standup = false;
            nowActivity.Chr2.setBase(SystemClock.elapsedRealtime() - nowActivity.timer2);
            nowActivity.Chr2.start();
            nowActivity.Chr.stop();
            nowActivity.timer = SystemClock.elapsedRealtime() - nowActivity.Chr.getBase();
        }
        nowActivity.cnt_bad ++;
        nowActivity.cnt_correct = 0;
        nowActivity.imageview.setImageResource(R.drawable.right_leg);
        if(nowActivity.cnt_bad > time_pos * 60 + 10)
            callPopup("Don't cross your right leg!");
    }
    public void Default_Leg(NowActivity nowActivity) {
        if(standup || nowActivity.cnt_correct == 0) {
            standup = false;
            nowActivity.Chr2.stop();
            nowActivity.timer2 = SystemClock.elapsedRealtime() - nowActivity.Chr2.getBase();
            nowActivity.Chr.setBase(SystemClock.elapsedRealtime() - nowActivity.timer);
            nowActivity.Chr.start();
        }
        nowActivity.cnt_bad = 0;
        nowActivity.cnt_correct ++;
        nowActivity.imageview.setImageResource(R.drawable.default_leg);

    }
    public void Stand_Up(NowActivity nowActivity) {
        standup =  true;
        if(nowActivity.cnt_correct == 0) {
            nowActivity.Chr2.stop();
            nowActivity.timer2 = SystemClock.elapsedRealtime() - nowActivity.Chr2.getBase();
        }
        else if(nowActivity.cnt_bad == 0) {
            nowActivity.Chr.stop();
            nowActivity.timer = SystemClock.elapsedRealtime() - nowActivity.Chr.getBase();
        }
        nowActivity.imageview.setImageResource(R.drawable.default_leg);

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

    public void callPopup(String s) {
        nowActivity.Chr.stop();
        nowActivity.timer = SystemClock.elapsedRealtime() - nowActivity.Chr.getBase();
        nowActivity.Chr2.stop();
        nowActivity.timer2 = SystemClock.elapsedRealtime() - nowActivity.Chr2.getBase();
        nowActivity.cnt_bad = 0;

        LayoutInflater layoutInflater = (LayoutInflater) TapActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View popupView = layoutInflater.inflate(R.layout.popup, null);

        popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,
                true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        TextView textview = (TextView) popupView.findViewById(R.id.tv);
        textview.setText(s);
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
                        nowActivity.cnt_bad = 0;
                    }
                });

    }
}
