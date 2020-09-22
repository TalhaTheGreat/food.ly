package com.example.foodly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class userLogin extends AppCompatActivity {
    databaseHelper myDB;
    TextView user, pass;
    Button btn, btndh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getSupportActionBar().hide();
        btn =findViewById(R.id.btnLogin);
        btndh = findViewById(R.id.btndonthaveaccount);
        user = findViewById(R.id.txtLoginName);
        pass=findViewById(R.id.txtloginPass);


            myDB = new databaseHelper(this);
            btndh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), userSignUp.class);
                    startActivity(intent);
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String loginName = user.getText().toString();
                    String loginPass = pass.getText().toString();
                    Cursor res = myDB.doLogin(loginName, loginPass);
                    if (res.getCount() == 0)
                    {
                        Toast.makeText(userLogin.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if (res.moveToFirst())
                        {
                            Integer type = Integer.parseInt(res.getString(1)), id = Integer.parseInt(res.getString(0));
                            if (type == 0)
                            {
                                //simple user
                                Intent user = new Intent(getApplicationContext(), placeOrder.class);
                                user.putExtra("name", res.getString(2)+" "+res.getString(3));
                                user.putExtra("id", id);
                                startActivity(user);
                            }
                            else {
                                //admin
                                Intent admin = new Intent(getApplicationContext(), adminDashboard.class);
                                admin.putExtra("name", res.getString(2)+" "+res.getString(3));
                                admin.putExtra("id", id);
                                startActivity(admin);
                            }
                        }
                    }
                }
            });

    }
}
