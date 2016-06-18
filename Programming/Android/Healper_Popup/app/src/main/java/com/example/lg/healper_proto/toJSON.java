package com.example.lg.healper_proto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class toJSON {

    public static Boolean execute(String s) {

        try {
            JSONObject json = new JSONObject(s);
            String jsonStr = json.getString("res_data");
            JSONArray json_resdata = json.getJSONArray("res_data");
            return exe_svccd(json_resdata);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean exe_svccd(JSONArray jsonarray) {
        try {
            JSONObject res_data = jsonarray.getJSONObject(0);

            if(res_data.getString("svccd").equals("MT0001")) {
                return login(res_data);
            } else if(res_data.getString("svccd").equals("MT0002")) {
                return signup(res_data);
            } else if(res_data.getString("svccd").equals("MT0003")) {
                return re_check(res_data);
            }
            else
                return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean login(JSONObject json) {
        try {
            String logInResult = (String)json.get("Log-In");
            if(logInResult.equals("Success")){
                JSONArray data= (JSONArray)json.get("data");
                int i=0;
                while(i < 360) {
                    if (i < data.length())
                        TapActivity.data[359-i] = (float)data.getInt(i);
                    else
                        TapActivity.data[359-i] = 0;
                    i++;
                }
                return true;
            }
            else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean signup(JSONObject json) {
        try {
            String SignUpResult = (String)json.get("INSERT");
            if(SignUpResult.equals("Success")){
                return true;
            }
            else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Boolean re_check(JSONObject json) {
        try {
            String SignUpResult = (String)json.get("isOK");
            if(SignUpResult.equals("Possible")){
                return true;
            }
            else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
