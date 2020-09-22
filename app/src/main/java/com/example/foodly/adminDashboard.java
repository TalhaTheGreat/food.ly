package com.example.foodly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class adminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView welcome;
        welcome = (TextView)findViewById(R.id.welcomemsg);
        welcome.setText("Welcome "+name);

        Button list, add, orders, admin;
        list = (Button)findViewById(R.id.btnItemList);
        add = (Button)findViewById(R.id.btnfoodAdd);
        orders = (Button)findViewById(R.id.btnOrders);
        admin = (Button)findViewById(R.id.btnaddAdmin);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(getApplicationContext(), foodList.class);
                startActivity(l);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), addFood.class);
                startActivity(a);
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o  = new Intent(getApplicationContext(), displayOrders.class);
                startActivity(o);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad = new Intent(getApplicationContext(), userSignUp.class);
                ad.putExtra("type", 1);
                startActivity(ad);
            }
        });
    }
}
