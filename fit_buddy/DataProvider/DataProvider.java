package com.example.fit_buddy.DataProvider;

import android.content.Context;

import com.example.fit_buddy.DataModel.DetailsListModel;

import java.io.IOException;
import java.io.InputStream;
import com.google.gson.Gson;

public class DataProvider extends Object {

    public static DetailsListModel fetchDetailList(Context context) {
        String jsonString = getJsonFromAssets(context,"exerciseDetails.json");
        Gson gson = new Gson();
        DetailsListModel detailModel = gson.fromJson(jsonString, DetailsListModel.class);
        return detailModel;
    }

    static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream file = context.getAssets().open(fileName);

            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
}
