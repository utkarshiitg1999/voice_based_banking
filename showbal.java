package com.example.cmbuser.bank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class showbal extends AppCompatActivity {
    TextView tv;
    String str;
    DatabaseHelper a=new DatabaseHelper(this);
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbal);
        tv=findViewById(R.id.textView5);
        Bundle bundle=getIntent().getExtras();
        str=bundle.getString("user");
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        int n=databaseAccess.getsender(str);
        a.first();
        databaseAccess.close();
        n=a.getbalance(n);
        tv.setText("Your account balance is Rs."+n);
    }
}
