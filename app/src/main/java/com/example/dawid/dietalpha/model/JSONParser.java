package com.example.dawid.dietalpha.model;

import android.util.Log;

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
    private static final String KEY_ITEM = "item";

    public static List<GroupJSONData> parseGroups(JSONObject response){
        ArrayList<GroupJSONData> res = new ArrayList<>();
        if(response == null || response.length() == 0) return Collections.emptyList();

        try {
            JSONArray groups = response.getJSONObject(KEY_LIST).getJSONArray(KEY_ITEM);
            for(int x = 0; x < groups.length(); ++x){
                JSONObject tmp = groups.getJSONObject(x);
                res.add(new GroupJSONData(tmp.getString("name"), tmp.getString("id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }
}
