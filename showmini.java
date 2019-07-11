package com.example.cmbuser.bank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class showmini extends AppCompatActivity {
    TextView head,top;
    EditText text;
    String str;
    int command;
    DatabaseHelper a=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmini);
        text=findViewById(R.id.eta);
        head=findViewById(R.id.tva);
        top=findViewById(R.id.tv7);
        Bundle bundle=getIntent().getExtras();
        str=bundle.getString("user");
        command=bundle.getInt("command");
        display(str);
    }
    void display(String s)
    {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        int n=databaseAccess.getsender(str);
        databaseAccess.close();
        int num=a.getnumber(n);
        String arr[]=new String[num];
        arr=a.getname(n);
        int i=0;
        String z="";
        if(command==1)
        {
            top.setText("MINI STATEMENT");
        if(num>4)
            num=4;

        }
        else
        {
            top.setText("PASSBOOK");
        }
        while(i<num)
        {
           z=z+Integer.toString(i+1)+"\t   "+arr[i]+"\n";
           i++;
        }
    text.setText(z);
    }
}
