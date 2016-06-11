package com.example.lg.healper_proto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = "MAIN";
    public static EditText ID_edit;
    public static EditText PW_edit;
    public static Boolean loginresult = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sub);

        ID_edit = (EditText) findViewById(R.id.ID);
        PW_edit = (EditText) findViewById(R.id.PW);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());


        Button Log_In = (Button) findViewById(R.id.Login);
        Log_In.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginresult = toJSON.execute(toServer.LogIn());
                if (loginresult) {
                    Intent intent = new Intent(getApplicationContext(), TapActivity.class);
                    startActivity(intent);
                    TapActivity.stat_init();
                } else
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
        Button bt_connect = (Button) findViewById(R.id.btconnect);
        bt_connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                startActivity(intent);
            }
        });

    }
}
