package com.example.cmbuser.bank;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class login extends AppCompatActivity {
    public EditText username,password;
    public Button login,exit;
    public TextView warning;
    @Override
    public void onBackPressed() {
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.et1);
        password = (EditText)findViewById(R.id.et2);
        login = (Button)findViewById(R.id.b1);
        exit=findViewById(R.id.ba);
        warning=(TextView)findViewById(R.id.tv1);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(username.getText().toString(),password.getText().toString());
                            }
        });
    }
    public void check(String a,String b)
    {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        int n=databaseAccess.credentials(a,b);
        databaseAccess.close();
        if(n!=-1)
        {
            warning.setVisibility(View.INVISIBLE);
            username.setText("");
            password.setText("");
            Intent intent=new Intent(login.this,language.class);
            intent.putExtra("user",a);
            startActivity(intent);
        }
        else
        {
            warning.setVisibility(View.VISIBLE);
            username.setText("");
            password.setText("");
        }
    }
}
