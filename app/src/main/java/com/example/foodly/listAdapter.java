package com.example.foodly;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Console;

import static java.lang.Integer.*;

public class listAdapter extends ArrayAdapter<String> {

    String[] i_name, i_price;
    LayoutInflater inflater;
    public listAdapter(Activity activity, int lv_foodlist,  String[] itemname, String[] itemprice) {
        super(activity, R.layout.lv_foodlist, itemname);
        this.i_name = itemname;
        this.i_price = itemprice;
        inflater = activity.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v =convertView;
        if (v == null)
        {
            v =inflater.inflate(R.layout.lv_foodlist,null);
        }
        TextView name, price;
        Button btn;
        name = (TextView)v.findViewById(R.id.itemname);
        price = (TextView)v.findViewById(R.id.itemprice);

        name.setText(i_name[position]);
        price.setText("$"+i_price[position]);
        return v;
    }
}
