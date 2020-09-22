package com.example.foodly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class addFood extends AppCompatActivity {
    databaseHelper myDB = new databaseHelper(this);
    Button btnadd;
    TextView name, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        getSupportActionBar().hide();
        btnadd = (Button)findViewById(R.id.btnAddFood);
        name = (TextView)findViewById(R.id.txtitemName);
        price = (TextView)findViewById(R.id.txtlPrice);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer p = Integer.parseInt(price.getText().toString());
                String n = name.getText().toString();
                boolean res = myDB.addFood(n,p,1);
                if (res == true)
                    Toast.makeText(addFood.this, "Item Added", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(addFood.this,"Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
