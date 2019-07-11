package com.example.cmbuser.bank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Button;

public class language extends AppCompatActivity {

   public Button english,hindi,bengali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        english = (Button) findViewById(R.id.b2);
        hindi = (Button) findViewById(R.id.b3);
        bengali = (Button) findViewById(R.id.b4);
        Bundle bundle=getIntent().getExtras();
        final String message=bundle.getString("user");
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eng(message);
               // check(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void eng(String a)
    {
        Intent intent=new Intent(language.this,english.class);
        intent.putExtra("user",a);
        intent.putExtra("command",2);
        startActivity(intent);
    }
}
