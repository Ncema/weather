package com.example.abwn179.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.abwn179.myapplication.R;
import com.example.abwn179.myapplication.model.WeatherModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
        private ArrayList<WeatherModel> list;
        private Context context;
        private int count = 0;

        public WeatherAdapter(ArrayList<WeatherModel> list, Context context) {
            this.list = list;
            this.context = context;
        }

        public WeatherAdapter(ArrayList<WeatherModel> list) {
            this.list = list;
        }

        public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView imageViewweatherIcon;
            public TextView textViewWeatherDay;
            public TextView textViewWeatherDegrees;

            WeatherViewHolder(View view) {
                super(view);
                view.setOnClickListener(this);
                imageViewweatherIcon = view.findViewById(R.id.weather_icon);
                textViewWeatherDay = view.findViewById(R.id.weather_day);
                textViewWeatherDegrees = view.findViewById(R.id.weather_degrees_celcius);
            }

            @Override
            public void onClick(View view) {
                //TODO - MyActivityFragment.activity_List();
                //this.itemClickListener.onItemClick(view,getLayoutPosition());

            }
        }


        @NonNull
        @Override
        public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent, false);
            return new WeatherViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final WeatherViewHolder holder, int position) {
            WeatherModel weatherResponse = list.get(position);
            // holder.textViewWeatherDegrees.setText((CharSequence) weatherResponse.main);
            Date date = new Date();
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
            String dates = (simpleDateformat.format(date));
            holder.textViewWeatherDay.setText(dates);
            holder.textViewWeatherDegrees.setText("30" + "°");
            holder.imageViewweatherIcon.setImageResource(R.drawable.clear);
            //do code to change the whether icon

        }


        @Override
        public int getItemCount() {
            if (list != null) {
                return list.size();
            } else {
                return 0;
            }
        }


    }

