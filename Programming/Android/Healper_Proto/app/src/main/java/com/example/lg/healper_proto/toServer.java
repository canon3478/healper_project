package com.example.lg.healper_proto;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class toServer  {

    public static String LogIn() {
        String ID = MainActivity.ID_edit.getText().toString().trim().toLowerCase();
        String PW = MainActivity.PW_edit.getText().toString().trim().toLowerCase();

        return toJson(ID, PW);
    }

    public static String SignUp() {
        String ID = SignUpActivity.ID_edit.getText().toString().trim().toLowerCase();
        String PW = SignUpActivity.PW_edit.getText().toString().trim().toLowerCase();
        String Name = SignUpActivity.Name_edit.getText().toString().trim().toLowerCase();
        String Email = SignUpActivity.Email_edit.getText().toString().trim().toLowerCase();

        return toJson(ID, PW, Name, Email);
    }

    public static String RedudancyCheck() {
        String ID = SignUpActivity.ID_edit.getText().toString().trim().toLowerCase();
        return toJson(ID);
    }
    public static String toJson(String ID) {
        JSONArray json_req_data = new JSONArray();
        JSONObject json_reqdata = new JSONObject(); // jsonarray
        JSONObject json = new JSONObject();
        try {
            json_reqdata.put("uid", ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        json_req_data.put(json_reqdata);

        try {
            json.put("req_data", json_req_data);
            json.put("svccd", "MT0003");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return toURL(json);
    }
    public static String toJson(String ID, String PW) {
        JSONArray json_req_data = new JSONArray();
        JSONObject json_reqdata = new JSONObject(); // jsonarray
        JSONObject json = new JSONObject();
        try {
            json_reqdata.put("upw", PW);
            json_reqdata.put("uid", ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        json_req_data.put(json_reqdata);

        try {
            json.put("req_data", json_req_data);
            json.put("svccd", "MT0001");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return toURL(json);
    }
    public static String toJson(String ID, String PW, String Name, String Email) {
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

        return toURL(json);
    }

    public static String toURL(JSONObject s) {
        String temp= "http://172.20.10.4:8081/healper/gateway/?JSONData=" + s.toString();
        StringBuilder res_data = new StringBuilder("");

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
                res_data.append(line);
                reader.close();
                conn.disconnect();
            }
        }
        catch (Exception ex) {
            Log.e("SampleHTTP", "Exception in processing response.", ex);
            ex.printStackTrace();
        }
        return res_data.toString();
    }
}
