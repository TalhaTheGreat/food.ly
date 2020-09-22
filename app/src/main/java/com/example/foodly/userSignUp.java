package com.example.foodly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class userSignUp extends AppCompatActivity {
    databaseHelper myDB = new databaseHelper(this);
    Button btnSignUp, btnalready;
    TextView fname, lname, email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        getSupportActionBar().hide();
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnalready = (Button)findViewById(R.id.btnhaveaccount);

        fname = (TextView)findViewById(R.id.txtfName);
        lname = (TextView)findViewById(R.id.txtlName);
        email = (TextView)findViewById(R.id.txtEmail);
        password = (TextView)findViewById(R.id.txtPassword);

        btnalready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), userLogin.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sfname, slname, semail, spassword;
                sfname = fname.getText().toString();
                slname= lname.getText().toString();
                semail = email.getText().toString();
                spassword = password.getText().toString();
                if (myDB.checkExistingUser(semail))
                {
                    Toast.makeText(userSignUp.this, "User Already Exisis", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent userType = getIntent();
                    boolean res;
                    if (userType.getIntExtra("type", 0) == 1)
                    {
                         res = myDB.addUser(1, sfname, slname, semail, spassword);
                    }
                    else
                    {
                         res = myDB.addUser(0, sfname, slname, semail, spassword);
                    }

                    if (res == true)
                    {
                        Toast.makeText(userSignUp.this, "Sign Up Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), userLogin.class);
                        startActivity(intent);
                    }

                    else
                        Toast.makeText(userSignUp.this, "Sign Up Failed", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
