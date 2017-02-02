package com.example.ramu.searcharoundapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ramu on 1/21/2017.
 */

public class Downloader extends AsyncTask<String, Integer, ArrayList> {


    RestuarentsList restuarentsList_activityt;
    Downloader(RestuarentsList activity){
        this.restuarentsList_activityt = activity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        restuarentsList_activityt.switchProgressBarVisibility(true);
    }

    @Override
    protected ArrayList doInBackground(String... params) {


        String yqlURL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20local.search%20where%20zip%3D%27"+params[1]+"%27%20and%20query%3D%27"+params[0]+"%27&format=json&callback=";
        ArrayList<Results> arrayList = new ArrayList<Results>();

        try {
            URL url = new URL(yqlURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(),"UTF-8"));
            String json = reader.readLine();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject queryObject = jsonObject.getJSONObject("query");
            JSONObject resultObject = queryObject.getJSONObject("results");
            JSONArray resultArray = resultObject.getJSONArray("Result");

            for(int i=0;i<resultArray.length();i++){

                publishProgress(i*10);

                JSONObject single_result = resultArray.getJSONObject(i);
                String title = single_result.getString("Title");
                String address  = single_result.getString("Address");
                String city = single_result.getString("City");
                String state = single_result.getString("State");
                String phone = single_result.getString("Phone");
                double latitude = Double.parseDouble(single_result.getString("Latitude"));
                double longitude = Double.parseDouble(single_result.getString("Longitude"));
                String distance = single_result.getString("Distance");
                String businessURL = single_result.getString("Url");
                Results result = new Results(title,address,city,state,phone,latitude,
                        longitude,distance,businessURL);

                arrayList.add(result);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        restuarentsList_activityt.setProgressBarProgress(values[0]);

    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        restuarentsList_activityt.switchProgressBarVisibility(false);
        restuarentsList_activityt.drawList(arrayList);
    }
}
