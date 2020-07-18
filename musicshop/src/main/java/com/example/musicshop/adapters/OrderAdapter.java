package com.example.musicshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicshop.R;
import com.example.musicshop.model.Order;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    Context context;

    int resource;

    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }


    //при генерации товара будет вызываться этот getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String goodName = getItem(position).getGoodName();
        int goodQuantity = getItem(position).getGoodQuantity();
        double goodPrice = getItem(position).getGoodPrice();
        String goodUser = getItem(position).getUserName();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView =  layoutInflater.inflate(resource, parent, false);

        TextView tvGoodName = convertView.findViewById(R.id.tvGoodName);
        TextView tvGoodUser = convertView.findViewById(R.id.tvGoodUser);
        TextView tvGoodQuantity = convertView.findViewById(R.id.tvGoodQuantity);
        TextView tvGoodPrice = convertView.findViewById(R.id.tvGoodPrice);
        ImageView tvGoodImage = convertView.findViewById(R.id.tvGoodImage);

        tvGoodName.setText(goodName);
        tvGoodQuantity.setText(String.valueOf(goodQuantity));
        tvGoodPrice.setText(String.valueOf(goodPrice));
        tvGoodUser.setText(goodUser);

        switch (goodName){
            case"guitar":
                tvGoodImage.setImageResource(R.drawable.guitar);
                break;
            case "keyboard":
                tvGoodImage.setImageResource(R.drawable.keyboard);
                break;
            case "drums":
                tvGoodImage.setImageResource(R.drawable.drums);
                break;
            case "rock":
                tvGoodImage.setImageResource(R.drawable.rock);
                break;
        }

        return convertView;

    }

}
