package com.example.dawid.dietalpha.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dawid on 2015-09-04.
 */
public class JSONParser {
    private static final String KEY_LIST = "list";
    private static final String KEY_REPORT = "report";
    private static final String KEY_ITEM = "item";
    private static final String KEY_FOODS = "foods";

    public static List<JsonData> parseGroups(JSONObject response){
        ArrayList<JsonData> res = new ArrayList<>();
        if(response == null || response.length() == 0) return Collections.emptyList();

        try {
            JSONArray groups = response.getJSONObject(KEY_LIST).getJSONArray(KEY_ITEM);
            for(int x = 0; x < groups.length(); ++x){
                JSONObject tmp = groups.getJSONObject(x);
                res.add(new JsonData(tmp.getString("name"), tmp.getString("id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static List<JsonData> parseProducts(JSONObject response) {
        ArrayList<JsonData> res = new ArrayList<>();
        if(response == null || response.length() == 0) return Collections.emptyList();

        try {
            JSONArray groups = response.getJSONObject(KEY_REPORT).getJSONArray(KEY_FOODS);
            for(int x = 0; x < groups.length(); ++x){
                JSONObject tmp = groups.getJSONObject(x);
                res.add(new JsonData(tmp.getString("name"), tmp.getString("ndbno")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static ItemData parseNutrients(JSONObject response, float weigth){
        try {
            String nm = response.getJSONObject(KEY_REPORT).getJSONArray(KEY_FOODS).getJSONObject(0).getString("name");
            float pro;
            float car;
            float fat;
            float cal;
            JSONArray nutrients = response.getJSONObject(KEY_REPORT).getJSONArray(KEY_FOODS).getJSONObject(0).getJSONArray("nutrients");
            pro = (weigth/100f)*(float)nutrients.getJSONObject(0).getDouble("gm");
            fat = (weigth/100f)*(float)nutrients.getJSONObject(1).getDouble("gm");
            car = (weigth/100f)*(float)nutrients.getJSONObject(2).getDouble("gm");
            cal = (weigth/100f)*(float)nutrients.getJSONObject(3).getDouble("gm");
            return new ItemData(nm, weigth, car, fat, cal, pro);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ItemData("N/A", -1, -1, -1, -1, -1);
    }
}
