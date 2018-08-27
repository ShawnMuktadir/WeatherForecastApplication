package com.example.mdmuktadir.weatherforecastapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherAdapter extends ArrayAdapter<Weather> {
    public WeatherAdapter(@NonNull Context context, ArrayList<Weather> weatherArrayList) {
        super(context,0, weatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather weather=getItem(position);


        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        TextView tvDate=convertView.findViewById(R.id.tvDate);
        TextView tvHighTemperature=convertView.findViewById(R.id.tvHighTemperature);
        TextView tvLowTemperature=convertView.findViewById(R.id.tvLowTemperature);
        TextView tvLink=convertView.findViewById(R.id.tvLink);
        TextView tvDayCondition=convertView.findViewById(R.id.tvDayCondition);
        TextView tvNightCondition=convertView.findViewById(R.id.tvNightCondition);

        tvDate.setText(weather.getDate());
        tvHighTemperature.setText(weather.getMaxTemp());
        tvLowTemperature.setText(weather.getMinTemp());
        tvLink.setText(weather.getLink());
        tvDayCondition.setText(weather.getDayCondition());
        tvNightCondition.setText(weather.getNightCondition());

        return convertView;
    }
}
