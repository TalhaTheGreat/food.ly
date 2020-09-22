package com.example.foodly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class foodList extends AppCompatActivity {
    ListView lv;
    databaseHelper myDB = new databaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        getSupportActionBar().hide();
        lv = (ListView)findViewById(R.id.lvFoodList);
        Cursor data = myDB.getItems();
        Integer count = data.getCount();
        Integer  index = 0;
        String itemname[] = new String[count], itemprice[] = new String[count];
        if (data.moveToFirst())
        {
            do
                {
                    itemname[index] = data.getString(1);
                    itemprice[index] = data.getString(2);
                    index++;
                }while (data.moveToNext());
        }
        listAdapter adapter = new listAdapter(this, R.layout.lv_foodlist, itemname, itemprice);
        lv.setAdapter(adapter);
    }
}
