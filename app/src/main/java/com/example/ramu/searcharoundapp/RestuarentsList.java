package com.example.ramu.searcharoundapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class RestuarentsList extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    ArrayList<Results> results_array;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarents_list);
        listView = (ListView) findViewById(R.id.listview);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        String searchTerm = getIntent().getStringExtra("searchTerm");
        String postalCode = getIntent().getStringExtra("postalCode");
        Downloader downloader = new Downloader(this);
        downloader.execute(searchTerm,postalCode);
    }

    public void switchProgressBarVisibility(boolean b){
       if(b == false)
           progressBar.setVisibility(View.GONE);
        else
           progressBar.setVisibility(View.VISIBLE);

    }
    public void setProgressBarProgress(int i){
        progressBar.setProgress(i);
    }


    public void drawList(ArrayList<Results> arrayList){

        results_array = arrayList;
        ResultsAdapter adapter = new ResultsAdapter(this, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Results result = results_array.get(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("singleResult",result);
        startActivity(intent);
    }
}