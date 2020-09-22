package com.example.foodly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class placeOrder extends AppCompatActivity {
    databaseHelper myDB = new databaseHelper(this);
    Cursor data;
    Spinner list;
    Button btnOrder;
    TextView quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        getSupportActionBar().hide();
        list = (Spinner)findViewById(R.id.spinnerItemNames);
        data = myDB.getItems();
        Integer index = 0;
            final String name[] = new String[data.getCount()], id[] = new String[data.getCount()];
            if (data.moveToFirst())
            {
                do {
                    name[index] = data.getString(0)+". "+data.getString(1)+"    $"+data.getString(2);
                    index++;
                }while (data.moveToNext());
            }

            ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
            list.setAdapter(a);

            btnOrder = (Button)findViewById(R.id.btnPlaceOrder);
            quantity = (TextView)findViewById(R.id.txtQuantity);

            btnOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                        if(data.getCount()>0) {
                            String temp = list.getSelectedItem().toString();
                            Integer q = Integer.parseInt(quantity.getText().toString());
                            String sid = temp.substring(0, temp.indexOf("."));
                            Intent intent = getIntent();
                            Integer iid = Integer.parseInt(sid), cid = intent.getIntExtra("id", 0);
                            //Toast.makeText(placeOrder.this, cid.toString()+iid.toString()+q.toString(), Toast.LENGTH_LONG).show();
                            Boolean res = myDB.placeOrder(cid, iid, q);
                            if (res == true) {
                                Toast.makeText(placeOrder.this, "Order Placed", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(placeOrder.this, "Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{Toast.makeText(placeOrder.this, "Order Can't be placed because There is No Item", Toast.LENGTH_SHORT).show();


                        }
                    }
            });


    }
}
