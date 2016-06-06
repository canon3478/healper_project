package com.example.lg.healper_proto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {

    public static EditText ID_edit;
    public static EditText PW_edit;
    public static EditText Name_edit;
    public static EditText Email_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_sub);

        ID_edit = (EditText) findViewById(R.id.ID);
        PW_edit = (EditText) findViewById(R.id.PW);
        Name_edit = (EditText) findViewById(R.id.Name);
        Email_edit = (EditText) findViewById(R.id.Email);

        Button Redudancy_check = (Button) findViewById(R.id.Re_Check);
        Redudancy_check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, toServer.RedudancyCheck(), Toast.LENGTH_LONG).show();
            }
        });

        Button Sign_Up = (Button) findViewById(R.id.Signup);
        Sign_Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, toServer.SignUp(), Toast.LENGTH_LONG).show();
            }
        });

        Button Cancel = (Button) findViewById((R.id.Cancel));
        Cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    /*
    public void RegisterUser() {
        String ID = ID_edit.getText().toString().trim().toLowerCase();
        String PW = PW_edit.getText().toString().trim().toLowerCase();
        String Name = Name_edit.getText().toString().trim().toLowerCase();
        String Email = Email_edit.getText().toString().trim().toLowerCase();

        toJson(ID, PW, Name, Email);
    }

    public void toJson(String ID, String PW, String Name, String Email) {
        JSONArray json_req_data = new JSONArray();
        JSONObject json_reqdata = new JSONObject(); // jsonarray
        JSONObject json = new JSONObject();
        try {
            json_reqdata.put("uemail", Email);
            json_reqdata.put("uname", Name);
            json_reqdata.put("upw", PW);
            json_reqdata.put("uid", ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        json_req_data.put(json_reqdata);

        try {
            json.put("req_data", json_req_data);
            json.put("svccd", "MT0002");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        toURL(json);
    }

    public void toURL(JSONObject s) {
        String temp= "http://172.20.10.4:8081/healper/gateway/?JSONData=" + s.toString();

        StringBuilder output = new StringBuilder();

        try {
            URL url = new URL(temp);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn != null) {
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();
            }
            int resCode = conn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = reader.readLine();
                Toast.makeText(SignUpActivity.this, line, Toast.LENGTH_LONG).show();
                reader.close();
                conn.disconnect();
            }
        }
        catch (Exception ex) {
            Log.e("SampleHTTP", "Exception in processing response.", ex);
            ex.printStackTrace();
        }

    }*/
}
