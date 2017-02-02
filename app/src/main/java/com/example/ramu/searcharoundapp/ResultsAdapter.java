package com.example.ramu.searcharoundapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ramu on 1/21/2017.
 */
public class ResultsAdapter extends ArrayAdapter{

    ArrayList<Results> results;
    Context mcontext;

    public ResultsAdapter(Context context, ArrayList<Results> results) {
        super(context, R.layout.single_row,results);
        this.results = results;
        this.mcontext = context;
    }

    class ResultViewHolder{
        TextView title_tv;
        TextView address_tv;
        TextView phone_tv;
        TextView distance_tv;
        ResultViewHolder(View v){
            title_tv = (TextView) v.findViewById(R.id.title_tv_item);
            address_tv = (TextView) v.findViewById(R.id.address_tv_item);
            phone_tv = (TextView) v.findViewById(R.id.phone_tv_item);
            distance_tv = (TextView) v.findViewById(R.id.distance_tv_item);
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ResultViewHolder holder = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(mcontext.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row,parent,false);
            holder = new ResultViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder = (ResultViewHolder) row.getTag();
        }
        Results result = results.get(position);
        holder.title_tv.setText(result.title);
        holder.address_tv.setText(result.address+", "+result.city+", "+result.state);
        holder.phone_tv.setText("Phone "+result.phone);
        holder.distance_tv.setText("Distance "+result.diatance+" miles");
        return row;
    }


}
