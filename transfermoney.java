package com.example.cmbuser.bank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class transfermoney extends AppCompatActivity {
    DatabaseHelper a=new DatabaseHelper(this);
    EditText text1,text2;
    TextView text3;
    Button but1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfermoney);
        text1= findViewById(R.id.et);
          text2 = findViewById(R.id.et1);
        text3 = findViewById(R.id.tv3);
        but1= findViewById(R.id.b1);
        Bundle bundle=getIntent().getExtras();
        final String message=bundle.getString("user");
        a.first();
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int acc,amount;
                double amoun;

                try {
                    acc = Integer.parseInt(text1.getText().toString());
                    amoun = Double.parseDouble(text2.getText().toString());
                    amount = (int) (amoun);

                    check(acc, amount, message);
                }
                catch(Exception e) {
                }

            }
        });

    }


    public void check(int acc,int amount,String message)
    {

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        int n=databaseAccess.getsender(message);
        text3.setText(Integer.toString(n));
        databaseAccess.close();
        databaseAccess.open();
        int m=databaseAccess.getreceiver(acc);
        databaseAccess.close();
        Log.d("nvalue",Integer.toString(n));
        Log.d("mvalue",Integer.toString(m));
        if(n==-1 || m==-1)
        {
            text3.setText("Wrong credentials, Please try again");
        }
        else if(n==m)
        {
            text3.setText("Can't transfer to same account");
        }

        else
        {

            int ba=a.getbalance(n);
            int ra=a.getbalance(m);
            Log.d("bavalue",Integer.toString(ba));
           // text3.setText(Integer.toString(ba));
            if(ba<amount)
            {
                text3.setText("               Low Balance. \n         Account Balance: Rs."+ba);
            }
            else
            {
                a.updater(ba-amount,n);
                a.updater(ra+amount,m);
                ba=a.getbalance(n);
                text3.setText("Transaction Successful.\n Updated Balance: Rs. "+(ba));
                databaseAccess.open();
                String name=databaseAccess.getname(m);
               long ret= a.inserttransaction(n,"debit",amount,name,ba);
                name=databaseAccess.getname(n);
                ba=a.getbalance(m);
               ret= a.inserttransaction(m,"credit",amount,name,ba);

            }
        }

        databaseAccess.close();
    }
}
