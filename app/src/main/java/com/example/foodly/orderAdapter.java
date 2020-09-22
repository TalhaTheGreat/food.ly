package com.example.foodly;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class orderAdapter extends ArrayAdapter<String> {
    String[] c_name, i_name, i_price, quan;
    LayoutInflater inflater;
    public orderAdapter(Activity activity, int lv_orders, String[] cusName, String[] itemName, String[] price, String[] quantity) {
        super(activity,R.layout.lv_orders, cusName);
        this.c_name = cusName;
        this.i_name = itemName;
        this.i_price = price;
        this.quan = quantity;
        inflater = activity.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null)
        {
            v = inflater.inflate(R.layout.lv_orders, null);
        }

        TextView cusname, itemname, totalprice, quant;
        cusname = (TextView)v.findViewById(R.id.txtcusname);
        itemname = (TextView)v.findViewById(R.id.txtitemname);
        quant = (TextView)v.findViewById(R.id.txtquantity);
        totalprice = (TextView)v.findViewById(R.id.txtprice);
        cusname.setText(c_name[position]);
        itemname.setText(i_name[position]);
        quant.setText(quan[position]);
        totalprice.setText("$ "+i_price[position]);

        return v;

    }
}
