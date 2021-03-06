package com.gmail.buckleyniall100.myapplication;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class jsonData {
    public static JSONObject jsonObj;
    public JSONArray list = new JSONArray();

    {
        try {
            Date date = new Date();
            jsonObj = new JSONObject().put("Date/ Time", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static JSONObject getObj() {
        return jsonObj;
    }


    //write the errors to json file
    public void writeToJson(String current ){
        list.put(current);
        //replace with address.

        try {
            jsonObj.put("Error", list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void writeMetaToJson(int ticked, int hoursSlept, int hoursSinceSlept){
        try {
            jsonObj.put("numTicked", ticked);
            jsonObj.put("hoursSlept", hoursSlept);
            jsonObj.put("hoursSinceSlept", hoursSinceSlept);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
