package com.example.foodly;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class displayOrders extends AppCompatActivity {
    databaseHelper myDB = new databaseHelper(this);
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_orders);
        getSupportActionBar().hide();
        lv = (ListView)findViewById(R.id.lv_orders);
        Cursor data = myDB.getOrders();
        Integer cusID, itemID, index=0, quantity;
        String cusName[] = new String[data.getCount()], itemName[]= new String[data.getCount()], price[] = new String[data.getCount()], quan[] = new String[data.getCount()];

        if (data.moveToFirst())
        {
            do {
                cusID = Integer.parseInt(data.getString(2));
                itemID = Integer.parseInt(data.getString(1));
                quantity = Integer.parseInt(data.getString(3));
                Cursor user = myDB.getUser(cusID);
                Cursor item = myDB.getItem(itemID);
                user.moveToFirst();
                item.moveToFirst();
                cusName[index] = user.getString(2)+" "+user.getString(3);
                itemName[index]=  item.getString(1);
                Integer temp = Integer.parseInt(item.getString(2));
                Integer p = temp * quantity;
                price[index] = p.toString();
                quan[index] = quantity.toString();
                index++;
            } while (data.moveToNext());
        }

        orderAdapter adapter = new orderAdapter(this, R.layout.lv_orders, cusName, itemName, price, quan);
        lv.setAdapter(adapter);
    }
}
